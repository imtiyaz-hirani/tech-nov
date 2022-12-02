package com.springrest.employeecms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.employeecms.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{

}
