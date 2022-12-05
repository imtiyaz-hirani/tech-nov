package com.springrest.employeecms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.dto.ManagerResponseDto;
import com.springrest.employeecms.model.Manager;
import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.ManagerRepository;
import com.springrest.employeecms.repository.UserRepository;

@RestController /* @Controller + @ResponseBody */
@RequestMapping("/api/manager")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ManagerController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Path: /api/manager/hello
	@GetMapping("/hello") //permit all
	public String showHello() {
		return "Hello Manager!!";
	}
	
	//Path: /api/manager/auth/hello
	@GetMapping("/auth/hello") //auth
	public String showHelloAuth() {
		return "Hello Manager with Auth!!";
	}
	
	//Path: /api/manager/auth/hello
	@GetMapping("/auth/role/hello") //role
	public String showHelloAuthRole() {
		return "Hello Manager with Auth and Role!!";
	}
	
	
	/*
	 Desc: POst API for Manager
	 PAth: /api/manager/add
	 */
	@PostMapping("/add")
	public Manager postManager(@RequestBody Manager manager){ //Request Body
		
		/* Step 1: Save User info in DB */
		User user = manager.getUser(); 
		user.setRole("MANAGER");
		/* 
		String plainTextPassword = user.getPassword(); //albus@123
		String encodedPassword = passwordEncoder.encode(plainTextPassword); //encoded pass
		user.setPassword(encodedPassword);
		*/
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user  = userRepository.save(user); //id,username,password,role
		
		/* Step 2: Attach User to Manager and Save Manager */
		manager.setUser(user);
		manager.setCreatedAt(LocalDate.now());
		manager =  managerRepository.save(manager);
		manager.getUser().setPassword("-------");
		return manager;
	}
	
	/*
	 Path: /api/manager/all
	 */
	@GetMapping("/all")
	
	public List<ManagerResponseDto> getAllManagers() {
		List<Manager> list =  managerRepository.findAll();
		List<ManagerResponseDto> listDto = new ArrayList<>();
		for(Manager m : list) {
			ManagerResponseDto dto = new ManagerResponseDto(); //id,name -- //200X
			dto.setId(m.getId()); //200X (id)
			dto.setName(m.getName());//200X (id,name)
			listDto.add(dto); //[100X,200X]	
		}
		return listDto;
	}
}


/*
 auto Import: ctrl + shft + o
 */


















