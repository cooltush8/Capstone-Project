package com.hdfc.midterm.leavemanagemetsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.midterm.leavemanagemetsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLeaveBalanceLessThan(int leaveBalanceThreshold);
}
