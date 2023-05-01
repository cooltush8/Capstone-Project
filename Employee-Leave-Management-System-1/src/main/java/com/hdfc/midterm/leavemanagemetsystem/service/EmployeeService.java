package com.hdfc.midterm.leavemanagemetsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.leavemanagemetsystem.entity.Employee;
import com.hdfc.midterm.leavemanagemetsystem.entity.Leave;
import com.hdfc.midterm.leavemanagemetsystem.entity.LeaveStatus;
import com.hdfc.midterm.leavemanagemetsystem.repository.EmployeeRepository;
import com.hdfc.midterm.leavemanagemetsystem.repository.LeaveRepository;

@Service
public class EmployeeService {
	
	
	 @Autowired
	  private EmployeeRepository employeeRepository;

	  @Autowired
	  private LeaveRepository leaveRepository;
	  
	  public Employee addEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }
	  
	  @Transactional
	  public void applyLeave(Leave leave) {
	    Employee employee = leave.getEmployee();
	    int leaveBalance = employee.getLeaveBalance();
	    int leaveDuration = leave.getDuration();

	    if (leaveBalance >= leaveDuration) {
	      // Deduct leave duration from employee leave balance
	      employee.setLeaveBalance(leaveBalance - leaveDuration);
	      employeeRepository.save(employee);

	      // Save leave application
	      leave.setStatus(LeaveStatus.PENDING);
	      leaveRepository.save(leave);
	    } else {
	      throw new RuntimeException("Insufficient leave balance");
	    }
	  }
	  
	  public List<Leave> getLeaveHistory(Long employeeId) {
		    return leaveRepository.findByEmployeeId(employeeId);
		  }
	  
	  
	

	    public EmployeeService(EmployeeRepository employeeRepository) {
	        this.employeeRepository = employeeRepository;
	    }

	    public Integer getLeaveBalance(Long id) {
	        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
	        if (optionalEmployee.isPresent()) {
	            Employee employee = optionalEmployee.get();
	            return employee.getLeaveBalance();
	        } else {
	            return null;
	        }
	    }

	  
	  
		

}
