package com.jsp.ets.utility;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ErrorStructure<T> {

	private int status;
	private String message;
	private String rootCouse;

	public  static <T> ErrorStructure<T> create(HttpStatus status, String message, String rootCouse) {
		ErrorStructure errorStructure = new ErrorStructure<T>();
		errorStructure.setStatus(status.value());
		errorStructure.setMessage(message);
		errorStructure.setRootCouse(rootCouse);
		return errorStructure;
	}
}
