package com.jsp.ets.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BatchNotFoundByIdException extends RuntimeException {

	private String message;


}
