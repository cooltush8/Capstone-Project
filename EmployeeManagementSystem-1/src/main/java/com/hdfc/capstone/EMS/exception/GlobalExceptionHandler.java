package com.hdfc.capstone.EMS.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	

//	
//	@ExceptionHandler(EmployeeIDException.class)
//	public ResponseEntity<String> exceptionHandler(EmployeeIDException exp){
//		return new ResponseEntity<String>(exp.getMessage().toString(),HttpStatus.EXPECTATION_FAILED);
//	}
	 @ExceptionHandler(EmployeeIDException.class)
	    public ResponseEntity<String> handleEmployeeIDException(EmployeeIDException ex) {
		 
	        return new ResponseEntity<>("Invalid Employee ID ", HttpStatus.BAD_REQUEST);
	    }

@ExceptionHandler(Exception.class)
public ResponseEntity<String> handleException(Exception ex) {
	return new ResponseEntity<>("Please Enter Integer Valid Employee ID Only ", HttpStatus.EXPECTATION_FAILED);
}
}


