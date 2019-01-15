package com.persistance.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormController {
// Consult FormController21 to consult detailed explanations	

	
	
	@PreAuthorize("hasAnyRole('ADMIN')") /*method level authorization; enabled 
	in security file through the @EnableGlobalMethodSecurity*/
	@GetMapping(path="/secured/all")
	public @ResponseBody String adminTest() {//@Responsebody returns the result of the query inside blank html
		
	return "ONLY ADMINS CAN SEE THIS PAGE... YOU ARE AN ADMIN!!";
	}

}
