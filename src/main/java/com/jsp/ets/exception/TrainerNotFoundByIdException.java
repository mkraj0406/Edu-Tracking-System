package com.jsp.ets.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrainerNotFoundByIdException extends RuntimeException {

	private String message;

	public String getmessage() {
		return message;
	}

}
