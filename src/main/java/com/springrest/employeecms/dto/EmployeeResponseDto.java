package com.springrest.employeecms.dto;

import java.time.LocalDate;

public class EmployeeResponseDto {
	private Long id;
	private String name;
	private String jobTitle;
	private LocalDate createdAt;
	private Integer totalLeaves;
	private String username;

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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getTotalLeaves() {
		return totalLeaves;
	}

	public void setTotalLeaves(Integer totalLeaves) {
		this.totalLeaves = totalLeaves;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
