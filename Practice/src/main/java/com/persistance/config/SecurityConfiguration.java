package com.persistance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.persistance.repository.UserRepository;
import com.persistance.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true) // allows method level authorization (for roles in this case getAllUsers2())
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses= UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService; /**/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService) /*retrieve the user object with all the info to be used by spring security*/
		.passwordEncoder(getPasswordEncoder()); /*spring boot will request a password to be
		 encoded... this can be bypassed... check the getPasswordEncoder() below*/ 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		
		http.csrf().disable(); // cross site reference
		http.authorizeRequests()
			.antMatchers("**/secured/**").authenticated()/* whenever there is a URL request with
			secured in it, spring security will have to make sure that the user is authenticated
			by checking the UserDetailsService and will move to the login page if that
			is not the case*/
			.anyRequest().permitAll()/*for any other request, no need for authentication*/
			.and().formLogin().permitAll();/*if we do not specify a login page it will direct
			to the default login modal provided by spring security, if we wish to redirect
			to a custom login page : formLogin().loginPage("/loginpage").permitAll*/
					
	}
	
	private PasswordEncoder getPasswordEncoder(){
		return new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return true;
			}
		};
	}
}
