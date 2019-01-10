package com.persistance.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends Users implements UserDetails {
	
	public CustomUserDetails(final Users users) {
		
		super(users);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/* Spring security uses this particular authority to determine
		 * what kind of access someone will have like views and methods*/
		List<SimpleGrantedAuthority> list = 
		 getRoles() // returns a set of roles
		.stream() // with this we can output the returned set of roles
		.map(role -> /*iterates through the set of role objects and finds the one connected to the
		 				the current user and retrieves it*/ 
				  new SimpleGrantedAuthority("ROLE_" + role.getRole())
				  /*creates a new authority object based on retrieved role
				   * that takes a String argument of format "ROLE_" + string with roleName
				   * to match the spring security naming pattern OR we can enter them 
				   * as ROLE_ADMIN or ROLE_USER in the DB */)
		.collect(Collectors.toList()); /* we can turn this into a list to output the result
		in a String format that will be used*/
		return list; //we have converted a DB authority into a format that spring security understands
	}

	@Override
	public String getPassword() {
		
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
