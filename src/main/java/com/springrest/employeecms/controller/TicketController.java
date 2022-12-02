package com.springrest.employeecms.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.dto.TicketResponseDto;
import com.springrest.employeecms.enums.TicketStateEnum;
import com.springrest.employeecms.model.Employee;
import com.springrest.employeecms.model.Ticket;
import com.springrest.employeecms.repository.EmployeeRepository;
import com.springrest.employeecms.repository.TicketRepository;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	//Path: /api/ticket/add
	/* 
	 Spring has valid username, take it using Principal interface. this is employee username
	 */
	@PostMapping("/add")
	public ResponseEntity<String> postTicket(Principal principal, @RequestBody Ticket ticket) {
		String username = principal.getName();
		/* Fetch employee details */
		Employee employee = employeeRepository.getEmployeeByUsername(username);
		
		/* Attach this employee to ticket */
		ticket.setEmployee(employee);
		
		TicketStateEnum state = TicketStateEnum.OPEN;
		
		/* Attach this state to the ticket */
		ticket.setTicketState(state);
		
		ticketRepository.save(ticket);
		
		return ResponseEntity.status(HttpStatus.OK).body("Ticket Posted Successfully");
	}
	
	@GetMapping("/all/{state}")
	public List<TicketResponseDto> getOpenTickets(Principal principal, @PathVariable("state") String state) {
		String username= principal.getName(); //Username of Employee 
		TicketStateEnum stateEnum = TicketStateEnum.valueOf(state);
		
		List<Ticket> list = ticketRepository.getTicketsByState(stateEnum,username);
		List<TicketResponseDto> listDto = new ArrayList<>();
		for(Ticket t:list) {
			TicketResponseDto dto = new TicketResponseDto();
			dto.setId(t.getId());
			dto.setIssue(t.getIssue());
			dto.setPriority(t.getPriority());
			dto.setTicketState(t.getTicketState());
			listDto.add(dto);
		}
		return listDto; 
	}
}
