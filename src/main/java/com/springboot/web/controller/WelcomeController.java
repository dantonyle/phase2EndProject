package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String showTheWelcomePage(ModelMap model) { return "redirect:welcome"; }
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) { return "welcome"; }
	
	@RequestMapping(value="/processButton",params="loginBtn",method=RequestMethod.POST)
	public String loginAction(ModelMap model) { return "redirect:login"; }
	
	@RequestMapping(value="/processButton",params="registerBtn",method=RequestMethod.POST)
	public String regAction(ModelMap model) { return "redirect:register"; }
	
	@RequestMapping(value="/processButton",params="forgotBtn",method=RequestMethod.POST)
	public String forgotAction(ModelMap model) { return "redirect:forgotPassword"; }
}
