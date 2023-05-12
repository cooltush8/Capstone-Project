package com.hdfc.capstone.EMS.client.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionClassController {
	
	
	
		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> exceptionHandler(Exception exp){
			return new ResponseEntity<String>("Please Enter Valid Employee Id",HttpStatus.EXPECTATION_FAILED);
		}
		
		

}
