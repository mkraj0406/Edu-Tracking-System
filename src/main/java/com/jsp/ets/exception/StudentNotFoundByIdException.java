package com.jsp.ets.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentNotFoundByIdException extends RuntimeException {

	private String message;

	public String getmessage() {
		return message;
	}

}
