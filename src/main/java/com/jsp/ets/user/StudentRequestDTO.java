package com.jsp.ets.user;

import java.time.Year;
import java.util.List;

import com.jsp.ets.btach.Batch;
import com.jsp.ets.rating.Rating;

import jakarta.persistence.Column;
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
