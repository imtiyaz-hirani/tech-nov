package com.springrest.employeecms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.employeecms.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
