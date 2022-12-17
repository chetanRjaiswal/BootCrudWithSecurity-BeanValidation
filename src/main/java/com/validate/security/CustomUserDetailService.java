package com.validate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.validate.entity.User;
import com.validate.exception.ResourceNotFoundException;
import com.validate.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from database by username 
		User user = userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email: "+username,0));

		return user;
	}

}
