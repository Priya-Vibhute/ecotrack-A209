package com.learn.ecotrack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.exceptions.NotFoundException;
import com.learn.ecotrack.repositories.UserRepository;

@Component
public class UserDetailsImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username)
		.orElseThrow(()->new NotFoundException("user not found with given email"));
		
		return user;
	}

}
