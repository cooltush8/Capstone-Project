package com.hdfc.midterm.leavemanagemetsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.leavemanagemetsystem.entity.Employee;
import com.hdfc.midterm.leavemanagemetsystem.entity.Leave;
import com.hdfc.midterm.leavemanagemetsystem.repository.EmployeeRepository;
import com.hdfc.midterm.leavemanagemetsystem.repository.LeaveRepository;

@Service
public class AdminService {
	
	
	@Autowired
	  private EmployeeRepository employeeRepository;

	  @Autowired
	  private LeaveRepository leaveRepository;
	  
	  
	  public List<Employee> getAllEmployees() {
		    return employeeRepository.findAll();
		  }

		  public Employee getEmployeeById(Long employeeId) {
		    return employeeRepository.findById(employeeId)
		        .orElseThrow(() -> new RuntimeException("Employee not found"));
		  }

		  public List<Leave> getAllLeaveApplications() {
		    return leaveRepository.findAll();
		  }

		  public List<Leave> getLeaveApplicationsByStatus(String status) {
		    return leaveRepository.findByStatus(status);
		  }

		  public List<Leave> getLeaveApplicationsByEmployeeId(Long employeeId) {
		    return leaveRepository.findByEmployeeId(employeeId);
		  }


}
