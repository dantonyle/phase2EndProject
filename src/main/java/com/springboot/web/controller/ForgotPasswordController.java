package com.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.web.model.UserEntity;
import com.springboot.web.service.DatabaseService;

@Controller
public class ForgotPasswordController {

	@Autowired
	DatabaseService dbService;

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String showForgotPage(ModelMap model) {
		return "forgotPassword";
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String retrievePassword(ModelMap model, @ModelAttribute UserEntity user) {

		// Get username and email -- > normally user will get password reset in email
		// (we have not covered this)
		boolean blank = false;

		if (!(dbService.checkUserNameExists(user.getUsername()))) {
			model.put("usernameERROR", "Username does not exist");
			blank = true;
		}
		if (dbService.checkForBlanks(user.getUsername())) {
			model.put("usernameERROR", "Username can not be blank, empty, or contain empty spaces");
			blank = true;
		}
		if (dbService.checkForBlanks(user.getEmail())) {
			model.put("emailERROR", "Email can not be blank, empty, or contain empty spaces");
			blank = true;
		}
		
	

		if (blank) {
			return "forgotPassword";
		} else {
			model.clear();
		}

		
		String rtnMsg;
		
		rtnMsg = dbService.returnPassword(user.getUsername(), user.getEmail());
		
		switch (rtnMsg) {
		case "wp":
			model.put("error", "Email entered was incorrect, please enter full email and domain");
			return "forgotPassword";
		case "udne":
			model.put("usernameERROR", "Username does not exist");
			return "forgotPassword";
		default:
			model.put("special", "Password for the username and email previously entered was: ");
			model.put("message", rtnMsg);
			return "welcome";

		}
	}
	
	@RequestMapping(value="/processButton",params="welcomeBtn",method=RequestMethod.POST)
	public String returnWelcomeAction(ModelMap model) { return "redirect:welcome"; }

}
