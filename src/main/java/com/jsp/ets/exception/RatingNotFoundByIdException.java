package com.jsp.ets.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RatingNotFoundByIdException extends RuntimeException {

	private String message;

	public String getmessage() {
		return message;
	}

}
