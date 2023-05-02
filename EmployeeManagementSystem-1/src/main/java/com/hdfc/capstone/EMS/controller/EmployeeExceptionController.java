package com.hdfc.capstone.EMS.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hdfc.capstone.EMS.exception.EmployeeIDException;

@ControllerAdvice
public class EmployeeExceptionController {
	
	@ExceptionHandler(EmployeeIDException.class)
public ResponseEntity<String>exceptionHandler(EmployeeIDException exp){
		return new ResponseEntity<String>(exp.getMessage().toString(),HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<String>exceptionHandler(Exception exp){
		return new ResponseEntity<String>(exp.getMessage().toString(),HttpStatus.EXPECTATION_FAILED);
	}
	

}
