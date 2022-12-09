package com.springrest.employeecms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.employeecms.enums.LoanTypeEnum;
import com.springrest.employeecms.model.AccountHolder;
import com.springrest.employeecms.model.Loan;
import com.springrest.employeecms.repository.AccountHolderLoanRepository;
import com.springrest.employeecms.repository.AccountHolderRepository;
import com.springrest.employeecms.repository.AccountRepository;
import com.springrest.employeecms.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	
	@Autowired
	private AccountHolderLoanRepository accountHolderLoanRepository;
	
	@Autowired
	private AccountRepository accountRepository; 
	
	public double computeSanctionLoanAmount(Long ahid, Long lid, Double amountNeeded) {
		Loan loan = loanRepository.findById(lid).get();
		AccountHolder accountHolder =   accountHolderRepository.findById(ahid).get();
		
		float creditScore = accountHolder.getCreditScore();
		LoanTypeEnum loanType =  loan.getType();
		int multiplier = 0; 
		if(creditScore >= 9 ) {
			if(loanType.equals(LoanTypeEnum.BUSINESS)) {
				multiplier = 10;
			}
			else
			if(loanType.equals(LoanTypeEnum.PERSONAL)) {
				multiplier = 4;
			}
		}
		else
		if(creditScore >= 7 ) {
			if(loanType.equals(LoanTypeEnum.BUSINESS)) {
				multiplier = 8;
			}
			else
			if(loanType.equals(LoanTypeEnum.PERSONAL)) {
				multiplier = 3;
			}
		}
		 
		double balance =  accountRepository.getBalance(ahid);
		
		return balance * multiplier;
	}
	
	
	public double checkMaxAmount(double sanctionAmount,LoanTypeEnum loanType ) {
		if(loanType.equals(LoanTypeEnum.BUSINESS)) {
			return (sanctionAmount>500000)?500000:sanctionAmount;
		}
		else
		if(loanType.equals(LoanTypeEnum.PERSONAL)) {
			return (sanctionAmount>200000)?200000:sanctionAmount;
		}
		else {
			return 0;
		}
	}

}
