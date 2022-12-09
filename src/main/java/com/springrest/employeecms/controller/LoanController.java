package com.springrest.employeecms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.model.AccountHolder;
import com.springrest.employeecms.model.Loan;
import com.springrest.employeecms.repository.AccountHolderLoanRepository;
import com.springrest.employeecms.repository.AccountHolderRepository;
import com.springrest.employeecms.repository.LoanRepository;
import com.springrest.employeecms.service.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	
	@Autowired
	private AccountHolderLoanRepository accountHolderLoanRepository;
	
	@Autowired
	private LoanService loanService; 
	
	@PostMapping("/add")
	public void postLoan(@RequestBody Loan loan) {
		loanRepository.save(loan);
	}
	
	@GetMapping("/apply/{ahid}/{lid}/{amount}")
	public Double applyLoan(@PathVariable("ahid") Long ahid, 
						  @PathVariable("lid") Long lid,
						  @PathVariable("amount") Double amountNeeded) {
		
		//Compute sanctionAmount
		double sanctionAmount = loanService.computeSanctionLoanAmount(ahid,lid,amountNeeded);
		return sanctionAmount;
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
}
