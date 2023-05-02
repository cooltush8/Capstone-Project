package com.hdfc.capstone.EMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.capstone.EMS.exception.EmployeeIDException;
import com.hdfc.capstone.EMS.service.IEmployeeService;
import com.hdfc.capstone.EMS.vo.EmployeeVO;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService service;

	@GetMapping("/{employeeID}")
	public EmployeeVO getByEmployeeId(@PathVariable int employeeID) throws Exception  {
		logger.info("Record of EmployeeID("+employeeID+")is called");
		
		return service.getByEmployeeId(employeeID);
	}
}
	
//	
//	 @GetMapping("/employees/{id}")
//	    public ResponseEntity<Employee> getByEmployeeId(@PathVariable Long id) {
//	        Optional<Employee> employee = EmployeeService.getByEmployeeId(id);
//
//	        if (employee.isPresent()) {
//	            return ResponseEntity.ok(employee.get());
//	        } else {
//	            throw new ResourceNotFoundException("Employee not found with id: " + id);
//	        }
//	    }

