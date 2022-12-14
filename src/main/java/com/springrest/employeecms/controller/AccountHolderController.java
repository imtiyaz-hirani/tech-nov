package com.springrest.employeecms.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.model.Account;
import com.springrest.employeecms.model.AccountHolder;
import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.AccountHolderRepository;
import com.springrest.employeecms.repository.AccountRepository;
import com.springrest.employeecms.repository.UserRepository;

@RestController
@RequestMapping("/api/account-holder")
public class AccountHolderController {
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/add")
	public void postAH(@RequestBody AccountHolder ah) {
		User user = ah.getUser();
		
		user.setRole("AH");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUsername(user.getUsername().toLowerCase());
		user  = userRepository.save(user); //id,username,password,role
		
		ah.setUser(user);
		
		accountHolderRepository.save(ah);
	}
	
	@PostMapping("/account/{ahid}")
	public void createAccount(@PathVariable("ahid") Long ahid, 
							  @RequestBody Account account) {
		
		AccountHolder ah = accountHolderRepository.findById(ahid).get();
		account.setAccountHolder(ah);
		
		String accountNumber = "IBX" + new Random().nextLong();
		account.setAccountNumber(accountNumber);
		accountRepository.save(account);
		
	}
}
