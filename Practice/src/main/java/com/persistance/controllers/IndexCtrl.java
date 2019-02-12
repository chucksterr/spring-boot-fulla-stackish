package com.persistance.controllers;

import java.security.Principal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;



import com.persistance.service.UserService;

@Controller
public class IndexCtrl {
	
	@Autowired
	UserService userServ;
	
	@GetMapping("/")
	public String showHomePage() {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		
		return "login";
	}
	
	
	@GetMapping("/home")
	public String showHomePageWhenLoggedIn(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "home";
	}
	
	@GetMapping("/chat")
	public String showChatRoom(Principal principal, Model model) {
		model.addAttribute("name", principal.getName());
		return "chat";
	}
}
