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
public class ResetPassword {

	private static Logger logger = LoggerFactory.getLogger(DatabaseService.class);
	
	@Autowired
	DatabaseService dbService;

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String showResetPage(ModelMap model) {
		logger.info("RESETPASSWORD page -showPage- ");
		return "reset";
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String getReset(ModelMap model, @ModelAttribute UserEntity user, @RequestParam String rePassword) {
		logger.info("RESETPASSWORD page -Submit Clicked- extracting information..... ");
		
		boolean inputError = false;
		
		logger.info("RESETPASSWORD page -input check begins- ");
		if (!(dbService.checkUserNameExists(user.getUsername()))) {
			model.put("usernameERROR", "Username does not exist");
			inputError = true;
		}
		if (dbService.checkForBlanks(user.getUsername())) {
			model.put("usernameERROR", "Username can not be blank, empty, or contain empty spaces");
			inputError = true;
		}
		if (dbService.checkForBlanks(user.getEmail())) {
			model.put("emailERROR", "Email can not be blank, empty, or contain empty spaces");
			inputError = true;
		}
		if (dbService.checkForBlanks(user.getPassword())) {
			model.put("passwordERROR", "Password can not be blank, empty, or contain empty spaces");
			inputError = true;
		}

		if (inputError) {
			logger.info("RESETPASSWORD page -Input Checked- ISSUE found... RESHOW RESET page....... ");
			return "reset";
		} else {
			logger.info("RESETPASSWORD page -Input Checked- NO ISSUE found.. CONTINUE.... ");
			model.clear();
		}
		

		if (!(dbService.checkUserEmailexists(user.getUsername(), user.getEmail()))) {
			model.put("emailERROR", "Email did not match");
			return "reset";
		}

		logger.info("RESETPASSWORD page -Passwords Check- Check if password and reentered password are the same....  ");
		if (!(user.getPassword().equals(rePassword))) {
			logger.info("RESETPASSWORD page -Passwords Check- FAILED password are NOT the same....  ");
			model.put("rePasswordERROR", "Password and Reconfirm did not match");
			return "reset";
		}

		logger.info("RESETPASSWORD page -Passwords Check- - SUCCESS -  password are the same....  ");
		try {
			if (dbService.overWritePassword(user.getUsername(), user.getPassword())) {
				model.put("special", "New Password was updated for user - " + user.getUsername());
				model.put("message", "Password updated to " + user.getPassword());
				return "welcome";
			}
		} catch (Exception e) {
			model.put("error", "Unable to Save New Password");
			return "reset";
		}

		logger.info("RESETPASSWORD page -SAVE FAILED- RESETTING PAGE....  ");
		return "redirect:reset";

	}
	
	@RequestMapping(value="/resetWelcome",params="welcomeBtn",method=RequestMethod.POST)
	public String resetToWelcomeAction(ModelMap model) { return "redirect:welcome"; }

}
