package com.springrest.employeecms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springrest.employeecms.service.LoanService;

@SpringBootTest
public class LoanServiceTest {

	@Autowired
	LoanService loanService; 
	/* test methods are always voided and they cannot take any arguments*/
	@Test
	void computeSanctionLoanAmountTest() {
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





