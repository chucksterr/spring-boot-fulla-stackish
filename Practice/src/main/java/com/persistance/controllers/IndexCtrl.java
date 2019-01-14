package com.persistance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCtrl {
	
	@GetMapping("/")
	public String showHomePage() {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		
		return "login";
	}

}
