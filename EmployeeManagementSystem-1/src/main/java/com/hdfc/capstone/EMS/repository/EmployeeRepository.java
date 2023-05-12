package com.hdfc.capstone.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hdfc.capstone.EMS.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByEmployeeId(int employeeId);



}