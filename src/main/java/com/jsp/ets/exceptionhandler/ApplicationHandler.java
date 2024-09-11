package com.jsp.ets.exceptionhandler;

import com.jsp.ets.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationHandler {

	private AppResponseBuilder errorResponseBuilder;

	@ExceptionHandler(StudentNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundById(StudentNotFoundByIdException ex) {
		return errorResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "student not found by given id!!");
	}
	
	@ExceptionHandler(TrainerNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundById(TrainerNotFoundByIdException ex) {
		return errorResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "trainer not found by given id!!");
	}
	
	@ExceptionHandler(RatingNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundById(RatingNotFoundByIdException ex) {
		return errorResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "rating not found by given id!!");
	}
	

	@ExceptionHandler(BatchNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundById(BatchNotFoundByIdException ex) {
		return errorResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "batch not found by given id!!");
	}

	@ExceptionHandler(RegistrationSessionExpired.class)
	public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundById(RegistrationSessionExpired ex) {
		return errorResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Registration  session got expired!!");
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public  ResponseEntity<ErrorStructure<String>> handlerUsernameNotFoundException(UsernameNotFoundException ex){
		return  errorResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"User email not found!!");
	}
}
