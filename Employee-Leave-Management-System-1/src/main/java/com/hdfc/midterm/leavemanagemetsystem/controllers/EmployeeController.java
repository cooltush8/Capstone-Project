package com.hdfc.midterm.leavemanagemetsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.midterm.leavemanagemetsystem.entity.Employee;
import com.hdfc.midterm.leavemanagemetsystem.entity.Leave;
import com.hdfc.midterm.leavemanagemetsystem.entity.LeaveStatus;
import com.hdfc.midterm.leavemanagemetsystem.repository.EmployeeRepository;
import com.hdfc.midterm.leavemanagemetsystem.repository.LeaveRepository;
import com.hdfc.midterm.leavemanagemetsystem.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	 
	  @Autowired
	  private EmployeeRepository employeeRepository;

	  @Autowired
	  private LeaveRepository leaveRepository;

	
	@Autowired
	  private EmployeeService employeeService;
	
	   @PostMapping
	    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
	        Employee addedEmployee = employeeService.addEmployee(employee);
	        return ResponseEntity.ok(addedEmployee);
	    }
	
	 @PostMapping("/apply-leave")
	  public ResponseEntity<?> applyLeave(@RequestBody Leave leave) {
	    employeeService.applyLeave(leave);
	    return ResponseEntity.ok("Leave application submitted successfully");
	  }
	 
	 @PostMapping("/{id}/apply-leave")
	  public ResponseEntity<String> applyLeave(@PathVariable("id") Long employeeId, @RequestBody Leave leave) {
	    Employee employee = employeeRepository.findById(employeeId).orElse(null);
	    if (employee == null) {
	      return ResponseEntity.notFound().build();
	    }
	    if (leave.getDuration() > employee.getLeaveBalance()) {
	      return ResponseEntity.badRequest().body("Insufficient leave balance");
	    }
	    leave.setEmployee(employee);
	    leave.setStatus(LeaveStatus.PENDING);
	    leaveRepository.save(leave);
	    return ResponseEntity.ok("Leave application submitted successfully");
	  }

	  @GetMapping("/leave-history/{employeeId}")
	  public ResponseEntity<?> getLeaveHistory(@PathVariable Long employeeId) {
	    List<Leave> leaveHistory = employeeService.getLeaveHistory(employeeId);
	    return ResponseEntity.ok(leaveHistory);
	  }
	  
	  
	  @GetMapping("/{id}/leave-balance")
	    public ResponseEntity<Integer> getLeaveBalance(@PathVariable Long id) {
	        Integer leaveBalance = employeeService.getLeaveBalance(id);
	        if (leaveBalance == null) {
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(leaveBalance);
	        }
	    }
	  
	

}
