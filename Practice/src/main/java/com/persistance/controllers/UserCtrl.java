package com.persistance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.persistance.models.Users;
import com.persistance.service.UserService;
import com.persistance.repository.UserRepository;;

@Controller
public class UserCtrl {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	
	@PreAuthorize("hasAnyRole('ADMIN')") /*method level authorization; enabled 
	in security file through the @EnableGlobalMethodSecurity*/
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Users> getAllUsers() {
 // This returns a JSON or XML with the users
	return userRepository.findAll();
	}
}
