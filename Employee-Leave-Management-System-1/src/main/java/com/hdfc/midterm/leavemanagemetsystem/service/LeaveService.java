package com.hdfc.midterm.leavemanagemetsystem.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.leavemanagemetsystem.entity.Employee;
import com.hdfc.midterm.leavemanagemetsystem.entity.Leave;
import com.hdfc.midterm.leavemanagemetsystem.entity.LeaveStatus;
import com.hdfc.midterm.leavemanagemetsystem.repository.EmployeeRepository;
import com.hdfc.midterm.leavemanagemetsystem.repository.LeaveRepository;

@Service
public class LeaveService {
	
	 @Autowired
	 LeaveRepository leaveRepository;
	 
	 @Autowired
	  EmployeeRepository employeeRepository;
	 
	 @Transactional
	  public void approveLeave(Long leaveId, String comment) {
	    Leave leave = leaveRepository.findById(leaveId)
	        .orElseThrow(() -> new RuntimeException("Leave application not found"));
	    leave.setStatus(LeaveStatus.APPROVED);
	    leave.setComments(comment);
	    leaveRepository.save(leave);

	    // Add leave duration back to employee leave balance
	    Employee employee = leave.getEmployee();
	    int leaveDuration = leave.getDuration();
	    int leaveBalance = employee.getLeaveBalance();
	    employee.setLeaveBalance(leaveBalance + leaveDuration);
	    employeeRepository.save(employee);
	  }
	 
	 
	 @Transactional
	  public void rejectLeave(Long leaveId, String comment) {
	    Leave leave = leaveRepository.findById(leaveId)
	        .orElseThrow(() -> new RuntimeException("Leave application not found"));
	    leave.setStatus(LeaveStatus.REJECTED);
	    leave.setComments(comment);
	    leaveRepository.save(leave);

	    // Add leave duration back to employee leave balance
	    Employee employee = leave.getEmployee();
	    int leaveDuration = leave.getDuration();
	    int leaveBalance = employee.getLeaveBalance();
	    employee.setLeaveBalance(leaveBalance + leaveDuration);
	    employeeRepository.save(employee);
	  }

	 public void updateLeaveBalance(Leave leave) {
		    Employee employee = leave.getEmployee();
		    int newLeaveBalance = employee.getLeaveBalance() - leave.getDuration();
		    employee.setLeaveBalance(newLeaveBalance);
		    employeeRepository.save(employee);
		  }

		  public void updateLeaveStatus(Long leaveId, LeaveStatus status, String comments) throws Exception {
		    Leave leave = leaveRepository.findById(leaveId).orElse(null);
		    if (leave == null) {
		      throw new EntityNotFoundException("Leave not found with id " + leaveId);
		    }
		    if (leave.getStatus() != LeaveStatus.PENDING) {
		      throw new Exception("Cannot update leave status because the leave is already " + leave.getStatus());
		    }
		    leave.setStatus(status);
		    leave.setComments(comments);
		    leaveRepository.save(leave);
		  }
		  
		  
		  public int calculateLeaveBalance(Employee employee) {
			    List<Leave> leaves = leaveRepository.findByEmployee(employee);
			    int totalLeavesTaken = 0;
			    for (Leave leave : leaves) {
			        if (leave.getStatus() == LeaveStatus.APPROVED) {
			            totalLeavesTaken -= leave.getDuration();
			        }
			    }
			    int leaveBalance = employee.getLeaveBalance() - totalLeavesTaken;
			    return leaveBalance;
			}

		  
		  public List<Leave> getLeaveHistoryForEmployee(Employee employee) {
			    return leaveRepository.findByEmployeeId(employee.getId());
			}
		
	
}
