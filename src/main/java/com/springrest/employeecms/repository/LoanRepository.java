package com.springrest.employeecms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.employeecms.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
