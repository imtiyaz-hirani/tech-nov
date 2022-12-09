package com.springrest.employeecms.model;

 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;  //findById(id) : already present

	private String username; // findByUsername(username): User
	private String password; // findByPassword(password): User
	private String role; //findByRole(role): User
	
	//findByUsernameAndPassword(username,password) : User
	//findByUsernameAndRole(username,role): User
	//getters setters toString
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}


}
