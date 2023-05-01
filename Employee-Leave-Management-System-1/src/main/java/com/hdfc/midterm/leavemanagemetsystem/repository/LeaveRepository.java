package com.hdfc.midterm.leavemanagemetsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.midterm.leavemanagemetsystem.entity.Employee;
import com.hdfc.midterm.leavemanagemetsystem.entity.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

	
List<Leave> findByEmployee(Employee employee);
    
    List<Leave> findByStatus(String status);

    List<Leave> findByEmployeeAndStatus(Employee employee, String status);

    List<Leave> findByEmployeeId(Long employeeId);

    List<Leave> findByEmployeeIdAndStatus(Long employeeId, String status);
}
