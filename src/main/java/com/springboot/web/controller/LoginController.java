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
public class LoginController {

	@Autowired
	DatabaseService dbService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginVerify(ModelMap model, @ModelAttribute UserEntity user) {

		boolean blank = false;

		if (!(dbService.checkUserNameExists(user.getUsername()))) {
			model.put("usernameERROR", "Username does not exist");
			blank = true;
		}
		if (dbService.checkForBlanks(user.getUsername())) {
			model.put("usernameERROR", "Username can not be blank, empty, or contain empty spaces");
			blank = true;
		}
		if (dbService.checkForBlanks(user.getPassword())) {
			model.put("passwordERROR", "Password can not be blank, empty, or contain empty spaces");
			blank = true;
		}
	
		


		if (blank) {
			return "login";
		} else {
			model.clear();
		}

		if (dbService.validateUserEntry(user.getUsername(), user.getPassword())) {
			// if user and password is correct
			model.put("name", user.getUsername());
			model.put("message", "You have been Logged In Successfully");
			return "welcome";
		}

		model.put("error", "Incorrect password");
		return "login";
	}
	
	@RequestMapping(value="/retButton",params="welcomeBtn",method=RequestMethod.POST)
	public String logToWelcomeAction(ModelMap model) { return "redirect:welcome"; }

}
