package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){ return "login"; }
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String LoginVerify(ModelMap model, @RequestParam String name, @RequestParam String password, @RequestParam String email) { 
		
		
		model.put("name", name);
		model.put("password", password);
		model.put("password", email);
		return "welcome";
	}
	
}
