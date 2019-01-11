/* service classes are the ones that make use of the repositories to implement 
 * business logic... it aims to separate the UI logic and the business one*/
package com.persistance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.persistance.models.Role;
import com.persistance.models.Users;
import com.persistance.repository.UserRepository;

@Service /*this class will be created as a bean and placed in the application context*/
public class UserService {
	@Autowired // autowiring injects the object dependency implicitly... internally uses setter 
	//or constructor injection so no need for objectType variableName = new objectType();
	private UserRepository userRepository; 
	
	// method to insert user for registration...
	public void createUser(Users user) {
		
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
	
		Role userRole = new Role("USER"); 
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		user.setActive(1);
		userRepository.save(user);		
	}
	// only the method above is documented... it's the same code different method name and string argument
	public void createAdmin(Users user) {
		
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
	
		Role userRole = new Role("Admin"); 
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles); 
		user.setActive(1);
		userRepository.save(user);	
	}
	public boolean isUserPresent(String email) {
		
		Users u= userRepository.findByEmail(email);
		if(u != null)
			return true;
		return false;
	}
	
	public Users findOne(String email) {
		return userRepository.findByEmail(email);
	}
	
}
