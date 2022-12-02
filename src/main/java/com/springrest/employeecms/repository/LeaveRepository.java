package com.springrest.employeecms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.employeecms.enums.LeaveStatusEnum;
import com.springrest.employeecms.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long>{

	@Query("select l from Leave l where l.employee.user.username=?1 AND l.leaveStatus=?2")
	List<Leave> getAllLeavesByStatus(String username, LeaveStatusEnum statusEnum);

}
