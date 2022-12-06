package com.springrest.employeecms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

	@Autowired
	private UserRepository userRepository; 
	/* 
	 Path: /api/user/login
	 Desc: 
	 If this method is called, it means that, Spring has already authenticated the User. 
	 It means that, Spring already has username and password. 
	 Spring will never give the password, however, we can ask for username using Principal interface. 
	 */
	@GetMapping("/login") 
	public User login(Principal principal) { //Dependency Injection: DI: IoC: Inversion Of Control
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		user.setPassword("----------");
		return user; 
	}
}
