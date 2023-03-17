package com.coderscamp.AssignemetSubmission.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderscamp.AssignemetSubmission.domain.User;
import com.coderscamp.AssignemetSubmission.repository.UserRepository;
import com.coderscamp.AssignemetSubmission.util.CustomPasswordEncoder;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private CustomPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		return userOpt.orElseThrow(()-> new UsernameNotFoundException("Invalid credentials"));
	}

}
