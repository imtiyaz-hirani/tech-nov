package com.springrest.employeecms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountHolder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String panNUmber;
	private float creditScore; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPanNUmber() {
		return panNUmber;
	}

	public void setPanNUmber(String panNUmber) {
		this.panNUmber = panNUmber;
	}

	public float getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(float creditScore) {
		this.creditScore = creditScore;
	}

	
}
