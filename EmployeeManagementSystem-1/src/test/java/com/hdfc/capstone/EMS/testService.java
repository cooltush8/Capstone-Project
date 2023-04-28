package com.hdfc.capstone.EMS;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import com.hdfc.capstone.EMS.service.IEmployeeService;
import com.hdfc.capstone.EMS.vo.EmployeeVO;

@SpringBootTest
class EmployeeServiceTest {

	@Autowired
	private IEmployeeService service;
	
	@Test
	void testGetEmployeeByEmployeeId() throws Exception{
		EmployeeVO employee = service.getByEmployeeId(1002);
		assertNotNull(employee);
		assertEquals(1002, employee.getEmployeeId());
	}

}
