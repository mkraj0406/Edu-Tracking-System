package com.jsp.ets.user;

import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {

	@Column(name = "degree")
	private String degree;

	@Column(name = "stream")
	private String stream;

	@Column(name = "year_of_passout")
	private Year year_of_passout;

	@Column(name = "degree_percentage")
	private int degreePercentage;
	
	@Column(name = "twelth_percentage")
	private int twelthPercentage;
	
	@Column(name = "tenth_percentage")
	private int tenthPercentage;

}
