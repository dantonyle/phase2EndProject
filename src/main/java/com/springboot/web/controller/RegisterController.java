package com.springboot.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.web.model.UserEntity;
import com.springboot.web.service.DatabaseService;

@Controller
public class RegisterController {

	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	DatabaseService regService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegPage(ModelMap model) {
		logger.info("UPDATE PAGE -- SHOWING new Register Page");
		return "register";
		
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST )
	public String RegVerify(ModelMap model, @ModelAttribute UserEntity user, @RequestParam String repassword) {
		
		logger.info("UPDATE PAGE -- IN the Register Page");
		// Blank, Whitespace, or Empty Check
		boolean inputError = false;
		
		logger.info("REGISTER page -- textbox check start... ");
		if ( regService.checkUserNameExists(user.getUsername())) { model.put("usernameERROR", "Username already exists" ); inputError = true; }
		if ( regService.checkForBlanks(user.getUsername())) { model.put("usernameERROR", "Username can not be blank, empty, or contain empty spaces" ); inputError = true; }
		if ( regService.checkForBlanks(user.getPassword())) { model.put("passwordERROR", "Password can not be blank, empty, or contain empty spaces" ); inputError = true; }
		if ( regService.checkForBlanks(user.getEmail())) { model.put("emailERROR", "Email can not be be blank, empty, or contain empty spaces" ); inputError = true; }
		if ( !(user.getPassword().equals(repassword))) { model.put("repasswordERROR", "Password does not match"); inputError = true; }
		// Email must have a domain
		if ( !regService.checkEmaildomain(user.getEmail())) { model.put("emailERROR", "Email must contain a domain name" ); inputError = true; }
		
		if (inputError) { 
			
			logger.info("REGISTER page -errorFOUND- textbox or issue found...");
			return "register"; } 
		else { 
			logger.info("REGISTER page -COMPLETE-  Attempt to SAVE to DB");
			model.clear(); }
		
		// Save if possible
		int outcome = regService.saveNewUser(user.getUsername(), user.getPassword(), user.getEmail());

		switch (outcome) {
		case 1:
			model.put("message", "New User - <font color='green'>" + user.getUsername() + "</font> - Registered");
			logger.info("REGISTER page -success- GOING to Welcome Page - SAVED to DB");
			return "welcome";
		case 2:
			model.put("error", "An entry is either blank or contains whitespace");
			logger.info("REGISTER page -error SOMEHOW BLANK- SHOWING new Register Page");
			return "register";
		default:
			model.put("error", "Unknown Error");
			logger.info("REGISTER page -error UNABLE TO SAVE- SHOWING new Register Page");
			return "redirect:register";

		}
		
	}
	
	@RequestMapping(value="/returnWelcomeButton",params="welcomeBtn",method=RequestMethod.POST)
	public String registerToWelcomeAction(ModelMap model) { return "redirect:welcome"; }

}
