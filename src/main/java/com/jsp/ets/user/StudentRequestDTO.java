package com.jsp.ets.user;

import java.time.Year;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO extends UserRequestDTO {
	
	private String degree;
	
	private String stream;
	
	private Year year_of_passout;
	
	private int degreePercentage;
	
	private int twelthPercentage;
	
	private int tenthPercentage;


}
