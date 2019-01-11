//package com.persistance.controllers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.persistance.models.Role;
//import com.persistance.models.Users;
//import com.persistance.repository.UserRepository;
//
//@Controller
//public class FormController {
//// Consult FormController21 to consult detailed explanations	
//@Autowired 
//private UserRepository userRepository;
//	
////	@GetMapping("/register")
////    public String registerForm(Model model) {
////        model.addAttribute("users", new Users());
////        model.addAttribute("role", new Role());
////        return "register";
////    }
//	
////	@PostMapping(path="/register") // Map ONLY POST Requests... @GetMapping does the opposite
//	public String addNewUser (@RequestParam String username, @RequestParam String email,
//	@RequestParam String password,
// // @RequestParam means it is a parameter from the GET or POST request
//	@Valid @ModelAttribute Users user, BindingResult bindingResult) {
// //	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
// // Create ROLE_USER through role class
//	Role role1 = new Role("USER");
//	List<Role> roles = new ArrayList<>();
//	roles.add(role1);
//
// // Save info received in user1 object
//	Users user1 = new Users();
//	user1.setActive(1);
//	user1.setEmail(email);
//	user1.setUsername(username);
//	user1.setPassword(password);
//	// user1.setPassword(encoder.encode(password));
// // Set role in user... will be persisted in Role Table in DB
//	user1.setRoles(roles);
//	// Persisting user and role... see FormController21
//	userRepository.save(user1);
//	return "saved";
//	}
//
//	@GetMapping(path="/all")
//	public @ResponseBody Iterable<Users> getAllUsers() {
// // This returns a JSON or XML with the users
//	return userRepository.findAll();
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN')") /*method level authorization; enabled 
//	in security file through the @EnableGlobalMethodSecurity*/
//	@GetMapping(path="/secured/all")
//	public @ResponseBody String getAllUsers2() {
//		
//	return "ONLY ADMINS CAN SEE THIS PAGE... YOU ARE AN ADMIN!!";
//	}
//
//}
