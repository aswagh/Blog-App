package com.project.blogApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.project.blogApp.entities.User;
import com.project.blogApp.exceptions.ResourceNotFoundException;
import com.project.blogApp.repositories.UserRepository;

@Controller
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// to load User Details
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from database by user name
		User user = this.userRepository.findByEmail(username)
		.orElseThrow(()-> new ResourceNotFoundException(username, "email"+username, 0));
		
		return user;
	}

}
