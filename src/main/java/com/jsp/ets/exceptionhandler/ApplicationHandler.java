package com.jsp.ets.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ets.exception.ObjectNotFoundByIdException;

import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;

@RestControllerAdvice
public class ApplicationHandler {
	
private AppResponseBuilder errorResponseBuilder;
	

	public ApplicationHandler(AppResponseBuilder errorResponseBuilder) {
	super();
	this.errorResponseBuilder = errorResponseBuilder;
}

	@ExceptionHandler(ObjectNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundById(ObjectNotFoundByIdException ex) {
		return errorResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "user not found by given id!!");
	}
	
}
