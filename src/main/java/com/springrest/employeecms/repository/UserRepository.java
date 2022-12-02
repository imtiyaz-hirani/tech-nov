package com.springrest.employeecms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.employeecms.model.User;

public interface UserRepository extends JpaRepository<User,  Long>{

	User findByUsername(String username);

}
