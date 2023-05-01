package com.hdfc.midterm.leavemanagemetsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leaves")
public class Leave {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	 @Column(name = "leave_type")
	  private String leaveType;

	 @Column(name = "duration")
	  private Integer duration;

	 @Column(name = "reason")
	  private String reason;

	 @Column(name = "status")
	  @Enumerated(EnumType.STRING)
	  private LeaveStatus status;
	 
	 @Column(name = "comments")
	    private String comments;

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "employee_id", referencedColumnName = "id")
	  private Employee employee;

	public Leave(Long id, String leaveType, Integer duration, String reason, LeaveStatus status, String comments,
			Employee employee) {
		super();
		this.id = id;
		this.leaveType = leaveType;
		this.duration = duration;
		this.reason = reason;
		this.status = status;
		this.comments = comments;
		this.employee = employee;
	}

	public Leave() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

//	public void setComments(String comments) {
//		this.comments = comments;
//	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setComments(String comments2) {
		// TODO Auto-generated method stub
		this.comments = comments;
	}



	  
	  
	  

}


