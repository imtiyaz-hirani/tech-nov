package com.springrest.employeecms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.model.AccountHolder;
import com.springrest.employeecms.repository.AccountHolderRepository;

@RestController
@RequestMapping("/api/account-holder")
public class AccountHolderController {
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	
	@PostMapping("/add")
	public void postAH(@RequestBody AccountHolder ah) {
		accountHolderRepository.save(ah);
	}
	
}
