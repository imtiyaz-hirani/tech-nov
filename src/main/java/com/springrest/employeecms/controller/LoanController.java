package com.springrest.employeecms.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.dto.AmountDto;
import com.springrest.employeecms.model.AccountHolder;
import com.springrest.employeecms.model.Loan;
import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.AccountHolderLoanRepository;
import com.springrest.employeecms.repository.AccountHolderRepository;
import com.springrest.employeecms.repository.LoanRepository;
import com.springrest.employeecms.repository.UserRepository;
import com.springrest.employeecms.service.LoanService;

@RestController
@RequestMapping("/api/loan")
@CrossOrigin(origins = "*")
public class LoanController {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	
	@Autowired
	private AccountHolderLoanRepository accountHolderLoanRepository;
	
	@Autowired
	private LoanService loanService; 
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/add")
	public void postLoan(@RequestBody Loan loan) {
		loanRepository.save(loan);
	}
	
	@GetMapping("/apply/{lid}/{amount}")
	public AmountDto applyLoan(Principal principal, 
						  @PathVariable("lid") Long lid,
						  @PathVariable("amount") Double amountNeeded) {
		
		String username = principal.getName();
		Long ahid = accountHolderRepository.getAHByUsername(username);
		//Compute sanctionAmount
		double sanctionAmount = loanService.computeSanctionLoanAmount(ahid,lid,amountNeeded);
		AmountDto dto = new AmountDto();
		dto.setSanctionAmount(sanctionAmount);
		return dto;
	}
	
	@GetMapping("/ah/loan/{lid}")
	public List<AccountHolder> getAHByLoan(@PathVariable("lid") Long lid) {
		Optional<Loan> optional = loanRepository.findById(lid);
		if(!optional.isPresent()) {
			
		}
		
		Loan loan = optional.get();
		
		List<AccountHolder> list =  accountHolderRepository.getAHByLoanId(lid);
		return list; 
	}
	
	@GetMapping("/all")
	public List<Loan> getAllLoans(){
		return loanRepository.findAll();
	}
}
