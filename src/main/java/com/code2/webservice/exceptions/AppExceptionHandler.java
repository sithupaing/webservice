package com.code2.webservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.code2.webservice.payload.response.ApiResponse;

import javassist.NotFoundException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException e){
		ApiResponse response = new ApiResponse(false, e.getMessage());
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingRequiredFieldException.class)
	public ResponseEntity<ApiResponse> handleMissingRequiredFieldException(MissingRequiredFieldException e){
		ApiResponse response = new ApiResponse(false, e.getMessage());
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	
	

}
