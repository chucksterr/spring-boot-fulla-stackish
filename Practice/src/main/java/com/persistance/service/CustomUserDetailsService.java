package com.persistance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.persistance.models.CustomUserDetails;
import com.persistance.models.Users;
import com.persistance.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	/*this class will generate an object that will iterate through
	 * the current list of users (accessed through the custom repository)
	 * and try find a match with the info entered by the user.
	 * Which is why it is important to have a column called username and
	 * another one called password. The UserDetailsService is an object
	 * that will hold the information for SPRING security*/
	
	@Autowired
	private UserRepository userRep; /*access the repository that is connected
	to the DB*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional <Users> byUname= userRep.findByUsername(username);/*the username
		entered during login will be searched for in the DB through the repository object*/
		
		byUname.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		/*if not found*/
		
		return  byUname /*if it is found, create a user object that will return the use from the database...
		since user names are unique, the mapping will return 1 user and use the UserDetailsService to 
		create a UserDetails object to be used by SPRING security*/
				.map(users -> {
					return new CustomUserDetails(users);
				}).get();
	}
}
