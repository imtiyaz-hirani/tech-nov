package com.springrest.employeecms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		username = username.toLowerCase();
		// Go to DB and fetch User details by username
		User myUser = userRepository.findByUsername(username);
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(myUser.getRole());
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(authority);
		
		org.springframework.security.core.userdetails.User springUser = 
				new org.springframework.security.core.userdetails.User
				(myUser.getUsername(), myUser.getPassword(), list);
		
		return springUser;
	}
	
	
}
