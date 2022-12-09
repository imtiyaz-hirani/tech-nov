package com.springrest.employeecms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.employeecms.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

	@Query("select l from Loan l where l.id=?1")
	Loan getLoanById(Long lid);

}
