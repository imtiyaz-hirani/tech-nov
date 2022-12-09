package com.springrest.employeecms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.employeecms.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query("select a.balance from Account a where a.accountHolder.id=?1")
	double getBalance(Long ahid);

}
