package com.springrest.employeecms;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springrest.employeecms.enums.LoanTypeEnum;
import com.springrest.employeecms.model.AccountHolder;
import com.springrest.employeecms.model.Loan;
import com.springrest.employeecms.repository.AccountHolderRepository;
import com.springrest.employeecms.repository.AccountRepository;
import com.springrest.employeecms.repository.LoanRepository;
import com.springrest.employeecms.service.LoanService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoanServiceTest {

	@Autowired
	@InjectMocks
	LoanService loanService; 
	
	@Mock
	AccountRepository accountRepository; 
	@Mock
	AccountHolderRepository accountHolderRepository; 
	@Mock
	LoanRepository loanRepository; 
	/* test methods are always voided and they cannot take any arguments*/
	@Test
	void computeSanctionLoanAmountTest() {
		
		/* Generate Mock Data */
		Loan loan = new Loan(); 
		loan.setInterestRate(7.5);
		loan.setType(LoanTypeEnum.BUSINESS);
		loan.setId(29L);
		
		Loan loan1 = new Loan(); 
		loan1.setInterestRate(12);
		loan1.setType(LoanTypeEnum.PERSONAL);
		loan1.setId(30L);
		
		AccountHolder ah1 = new AccountHolder();
		AccountHolder ah2 = new AccountHolder();
		AccountHolder ah3 = new AccountHolder();
		ah1.setId(32L);
		ah1.setName("frodo baggins");
		ah1.setPanNUmber("AEMPH3334J");
		ah1.setCreditScore(7.5F);
		
		ah2.setId(37L);
		ah2.setName("bilbo baggins");
		ah2.setPanNUmber("AEMPH33341");
		ah2.setCreditScore(9.5F);
		
		ah3.setId(39L);
		ah3.setName("samwise gamajee");
		ah3.setPanNUmber("AEMPH33355");
		ah3.setCreditScore(6F);
		
		when(loanRepository.getLoanById(29L)).thenReturn(loan);
		when(loanRepository.getLoanById(30L)).thenReturn(loan1);
		
		when(accountHolderRepository.getAHById(32L)).thenReturn(ah1);
		when(accountHolderRepository.getAHById(37L)).thenReturn(ah2);
		when(accountHolderRepository.getAHById(39L)).thenReturn(ah3);
		
		when(accountRepository.getBalance(32L)).thenReturn(50000D);
		when(accountRepository.getBalance(37L)).thenReturn(200000D);
		when(accountRepository.getBalance(39L)).thenReturn(100000D);
		
		//call the method of service(LoanService) class 
		
		//Use Case: Apply for Business loan with credit score>=7 
		double expected = 400000; 
		double actual = loanService.computeSanctionLoanAmount(32L,29L,100000D);
		Assertions.assertEquals(expected, actual);
		
		//Use Case: Apply for Personal loan with credit score>=7 
		expected = 150000;
		actual = loanService.computeSanctionLoanAmount(32L,30L,100000D);
		Assertions.assertEquals(expected, actual);
		
		//Use Case: Apply for Business loan with credit score>=9 
		expected = 2000000;
		actual = loanService.computeSanctionLoanAmount(37L,29L,100000D);
		Assertions.assertEquals(expected, actual);
		
		//Use Case: Apply for Personal loan with credit score>=9 
		expected = 800000;
		actual = loanService.computeSanctionLoanAmount(37L,30L,100000D);
		Assertions.assertEquals(expected, actual);
		
		//Use Case: Apply for Business Loan with credit score<7
		expected = 0;
		actual = loanService.computeSanctionLoanAmount(39L,29L,100000D);
		Assertions.assertEquals(expected, actual);
		
		//Use Case: Apply for Personal Loan with credit score<7
		expected = 1;
		actual = loanService.computeSanctionLoanAmount(39L,30L,100000D);
		Assertions.assertNotEquals(expected, actual);
	}
}





