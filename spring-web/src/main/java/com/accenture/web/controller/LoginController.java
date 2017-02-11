package com.accenture.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private String indexPage = "index";
	
	@GetMapping	
	@RequestMapping(value = {"/"})
	public String login() {
		LOGGER.info("Hello world");

		return indexPage;

	}
	
	@GetMapping
	@RequestMapping(value = {"/showMessage/{msg}"})
	public String showMessageByPathVariable(@PathVariable String msg, ModelMap model){
		LOGGER.info("The message by pathVariable is :"+msg);		
		
		model.addAttribute("newMsg", msg);
		
		return indexPage;
	}
	
	@GetMapping
	@RequestMapping(value = {"/showMessage"})
	public String showMessageByRequestParameter(@RequestParam String msg, ModelMap model){
		LOGGER.info("The message by requesParameter is :"+msg);		
		
		model.addAttribute("newMsg", msg);
		
		return indexPage;
	}
	

}
