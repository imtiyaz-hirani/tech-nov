package com.springrest.employeecms.controller;

import java.time.LocalDate;
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
import com.springrest.employeecms.model.AccountHolderLoan;
import com.springrest.employeecms.model.Loan;
import com.springrest.employeecms.repository.AccountHolderLoanRepository;
import com.springrest.employeecms.repository.AccountHolderRepository;
import com.springrest.employeecms.repository.LoanRepository;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	
	@Autowired
	private AccountHolderLoanRepository accountHolderLoanRepository;
	@PostMapping("/add")
	public void postLoan(@RequestBody Loan loan) {
		loanRepository.save(loan);
	}
	
	@PostMapping("/apply/{ahid}/{lid}/{amount}")
	public void applyLoan(@PathVariable("ahid") Long ahid, 
						  @PathVariable("lid") Long lid,
						  @PathVariable("amount") Double amount) {
		Optional<Loan> optional = loanRepository.findById(lid);
		if(!optional.isPresent()) {
			
		}
		
		Loan loan = optional.get();
		
		AccountHolder ah =   accountHolderRepository.findById(ahid).get();
		
		AccountHolderLoan ahl = new AccountHolderLoan();
		ahl.setLoan(loan);
		ahl.setAccountHolder(ah);
		ahl.setSanctionDate(LocalDate.now());
		long random_number = (long)Math.random()+1000000;
		String loanNumber = "HDAX" + random_number;
		ahl.setLoanNumber(loanNumber);
		ahl.setAmount(amount);
		
		accountHolderLoanRepository.save(ahl);
		
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
