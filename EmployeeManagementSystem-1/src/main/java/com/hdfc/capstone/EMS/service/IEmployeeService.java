package com.hdfc.capstone.EMS.service;

import com.hdfc.capstone.EMS.vo.EmployeeVO;

public interface IEmployeeService {

	
	public EmployeeVO getByEmployeeId(int employeeID) throws Exception;
}
