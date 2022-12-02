package com.springrest.employeecms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.employeecms.enums.TicketStateEnum;
import com.springrest.employeecms.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("select t from Ticket t where t.employee.user.username=?2 AND t.ticketState=?1")
	List<Ticket> getTicketsByState(TicketStateEnum stateEnum, String username);

}
