package com.hdfc.midterm.leavemanagemetsystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "employees")
public class Employee {
	
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String name;
	 
	  @Email
	  private String email;

	  private Integer leaveBalance;

	  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	  private List<Leave> leaveApplications = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(Integer leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public List<Leave> getLeaveApplications() {
		return leaveApplications;
	}

	public void setLeaveApplications(List<Leave> leaveApplications) {
		this.leaveApplications = leaveApplications;
	}

	public Employee(Long id, String name, String email, Integer leaveBalance, List<Leave> leaveApplications) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.leaveBalance = leaveBalance;
		this.leaveApplications = leaveApplications;
	}

	public Employee() {
		super();
	}

	  
	  
}
