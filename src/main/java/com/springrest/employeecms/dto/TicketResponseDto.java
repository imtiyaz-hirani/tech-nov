package com.springrest.employeecms.dto;

import com.springrest.employeecms.enums.TicketPriorityEnum;
import com.springrest.employeecms.enums.TicketStateEnum;

public class TicketResponseDto {

	private Long id;
	private String issue;
	private TicketPriorityEnum priority;
	private TicketStateEnum ticketState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public TicketPriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(TicketPriorityEnum priority) {
		this.priority = priority;
	}

	public TicketStateEnum getTicketState() {
		return ticketState;
	}

	public void setTicketState(TicketStateEnum ticketState) {
		this.ticketState = ticketState;
	}

}
