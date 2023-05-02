package com.hdfc.capstone.EMS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class EmployeeIDException extends Exception {
	
	public EmployeeIDException(String message) {
		super(message);
		
	}
	
	

}
