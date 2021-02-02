package com.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.web.service.DatabaseService;

@Controller
public class NewPasswordController {

	String userName;
	
	@Autowired
	DatabaseService dbService;
	
	@RequestMapping(value="/newPassword", method = RequestMethod.GET)
	public String showNewPasswordPage(ModelMap model) { 
		userName = model.get("username").toString();
		return "newPassword";
	}

	@RequestMapping(value="/newPassword", method = RequestMethod.POST)
	public String showConfirmPage(ModelMap model, @RequestParam String password, @RequestParam String rePassword) { 
		
		userName = model.get("username").toString();
		
		model.put("username", userName);
		
		boolean checkSamePass = false;
		
		if ( !(password.equals(rePassword))) { 
			model.put("repasswordERROR", "Password does not match"); 
			checkSamePass = true; 
		}
		
		
		if (checkSamePass) {
			dbService.overWritePassword(userName, password);
			model.put("special", "New password updated for " + userName);
			model.put("message", "New password is "+ password);
			return "welcome";
		} else {
			model.clear();
		}
		
		model.put("error", "Passwords did not match");
		return "newPassword"; 
	}
}
