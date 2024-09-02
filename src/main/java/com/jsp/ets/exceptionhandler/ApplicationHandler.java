package com.jsp.ets.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ets.exception.BatchNotFoundByIdException;
import com.jsp.ets.exception.ObjectNotFoundByIdException;
import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.exception.StudentNotFoundByIdException;
import com.jsp.ets.exception.TrainerNotFoundByIdException;
import com.jsp.ets.user.Trainer;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationHandler {

	private AppResponseBuilder errorResponseBuilder;

	@ExceptionHandler(ObjectNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundById(ObjectNotFoundByIdException ex) {
		return errorResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "user not found by given id!!");
	}
	
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
}
