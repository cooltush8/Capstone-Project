package com.hdfc.midterm.leavemanagemetsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.midterm.leavemanagemetsystem.entity.Employee;
import com.hdfc.midterm.leavemanagemetsystem.entity.Leave;
import com.hdfc.midterm.leavemanagemetsystem.entity.LeaveStatus;
import com.hdfc.midterm.leavemanagemetsystem.repository.EmployeeRepository;
import com.hdfc.midterm.leavemanagemetsystem.repository.LeaveRepository;
import com.hdfc.midterm.leavemanagemetsystem.service.AdminService;
import com.hdfc.midterm.leavemanagemetsystem.service.EmployeeService;
import com.hdfc.midterm.leavemanagemetsystem.service.LeaveService;

@RestController
@RequestMapping("/leaves")
public class LeaveController {
	
	 @Autowired
	   LeaveService leaveService;
	 @Autowired
	   LeaveRepository leaveRepository;
	 
	 @Autowired
	 EmployeeRepository employeeRepository;
	 
	 @Autowired
	 EmployeeService employeeService;
	  @Autowired
	 AdminService adminService;
	
	 @PutMapping("/approve-leave/{leaveId}")
	  public ResponseEntity<?> approveLeave(@PathVariable Long leaveId, @RequestBody String comments) {
	    leaveService.approveLeave(leaveId, comments);
	    return ResponseEntity.ok("Leave application approved");
	  }

	  @PutMapping("/reject-leave/{leaveId}")
	  public ResponseEntity<?> rejectLeave(@PathVariable Long leaveId, @RequestBody String comment) {
	    leaveService.rejectLeave(leaveId, comment);
	    return ResponseEntity.ok("Leave application rejected");
	  }
	  
	  @PostMapping("/{id}/approve")
	  public ResponseEntity<String> approveLeave(@PathVariable("id") Long leaveId, @RequestBody LeaveApprovalRequest request) {
	    Leave leave = leaveRepository.findById(leaveId).orElse(null);
	    if (leave == null) {
	      return ResponseEntity.notFound().build();
	    }
	    if (!request.isValidAction()) {
	      return ResponseEntity.badRequest().body("Invalid action");
	    }
	    leave.setStatus(request.getAction());
	    leave.setComments(request.getComments());
	    leaveRepository.save(leave);
	    return ResponseEntity.ok("Leave request processed successfully");
	  }
	  
	  
	  @GetMapping("/employees/{employeeId}/balance")
	  public ResponseEntity<Integer> getLeaveBalance(@PathVariable Long employeeId) {
	      Employee employee = employeeRepository.findById(employeeId).orElse(null);
	      if (employee == null) {
	          return ResponseEntity.notFound().build();
	      }
	      int leaveBalance = leaveService.calculateLeaveBalance(employee);
	      return ResponseEntity.ok(leaveBalance);
	  }
	
	  
	  @GetMapping("/employees/{employeeId}/leave-history")
	  public ResponseEntity<List<Leave>> getLeaveHistory(@PathVariable Long employeeId) {
	      Employee employee = employeeRepository.findById(employeeId).orElse(null);
	      if (employee == null) {
	          return ResponseEntity.notFound().build();
	      }
	      List<Leave> leaveHistory = leaveService.getLeaveHistoryForEmployee(employee);
	      return ResponseEntity.ok(leaveHistory);
	  }

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  public class LeaveApprovalRequest {

			 private LeaveStatus action;

			  private String comments;

			  public boolean isValidAction() {
			    return action == LeaveStatus.APPROVED || action == LeaveStatus.REJECTED;
			  }

			public LeaveApprovalRequest(LeaveStatus action, String comments) {
				super();
				this.action = action;
				this.comments = comments;
			}

			public LeaveApprovalRequest() {
				super();
			}

			public LeaveStatus getAction() {
				return action;
			}

			public void setAction(LeaveStatus action) {
				this.action = action;
			}

			public String getComments() {
				return comments;
			}

			public void setComments(String comments) {
				this.comments = comments;
			}
	  }
			  

}
