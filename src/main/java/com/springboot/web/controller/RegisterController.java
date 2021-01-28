package com.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.web.service.RegisterService;

@Controller
public class RegisterController {
	
	
	@Autowired
	RegisterService regService;

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String showRegPage(ModelMap model){ return "register"; }
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String RegVerify(ModelMap model, @RequestParam String name, @RequestParam String password, @RequestParam String email) { 
		
		
		//email does not contain a domain
		/*
		if (!regService.checkEmailHasADomain(email)) {
			model.put("emailERROR", "Please enter an email");
			return "register";
		}
		*/
		
		model.put("name", name);
		model.put("password", password);
		model.put("email", email);
		return "welcome";
	}
	
}
