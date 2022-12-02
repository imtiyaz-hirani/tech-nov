package com.springrest.employeecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class EmployeeCmsBackendMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeCmsBackendMainApplication.class, args);
	}

}
