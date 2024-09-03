package com.jsp.ets.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrainerNotFoundByIdException extends RuntimeException {

	private String message;

}
