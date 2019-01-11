package com.persistance.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.persistance.models.Role;
import com.persistance.models.Users;
import com.persistance.service.UserService;

@Controller
public class RegisterCtrl {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("users", new Users());
//      model.addAttribute("role", new Role());
        return "register";
    }
	
	@PostMapping(path="/register")
	public String registerUser(@Valid Users user, BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {	
			return "register";
		}
		
		if(userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist", true);
			return "register";
		}
		userService.createUser(user);
		return "saved";
	}
}
