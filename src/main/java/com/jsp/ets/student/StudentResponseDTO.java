package com.jsp.ets.student;

import java.time.Year;

import com.jsp.ets.stack.Stack;
import com.jsp.ets.user.UserResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO extends UserResponseDto {

	private String degree;

	private String stream;

	private Year year_of_passout;

	private int degreePercentage;

	private int twelthPercentage;

	private int tenthPercentage;
	
	private Stack stack;

}
