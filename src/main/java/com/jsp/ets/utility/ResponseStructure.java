package com.jsp.ets.utility;

import org.springframework.http.HttpStatus;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T>{

	private int status;
	private String message;
	private T data;
	
	
	public static <T> ResponseStructure<T> create(HttpStatus status,String message,T data) {
		ResponseStructure responseStructure = new ResponseStructure();
		responseStructure.setStatus(status.value());
		responseStructure.setMessage(message);
		responseStructure.setData(data);
		
		return responseStructure;
	}
	
}
