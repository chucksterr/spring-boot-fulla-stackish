package com.persistance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.persistance.service.UserService;

@Controller
public class UserCtrl {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String listOfUsers(Model model) {
		
		model.addAttribute("users", userService.findAll());
		
		return "list";
	}
	

	@PostMapping("/users")
	public String listOfUsers(Model model, @RequestParam(defaultValue="") String username) {
		
		model.addAttribute("users", userService.findByName("%"+username+"%"));
		
		return "list";
	}
}
