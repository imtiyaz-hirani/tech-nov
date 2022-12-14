package com.springrest.employeecms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.employeecms.model.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long>{

	@Query("select ahl.accountHolder  from AccountHolderLoan ahl where ahl.loan.id=?1")
	List<AccountHolder> getAHByLoanId(Long lid);

	@Query("select ah from AccountHolder ah where ah.id=?1")
	AccountHolder getAHById(Long ahid);

	@Query("select ah.id from AccountHolder ah where ah.user.username=?1")
	Long getAHByUsername(String username);

}
