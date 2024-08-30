package com.jsp.ets.user;

import java.time.Year;
import java.util.List;

import com.jsp.ets.btach.Batch;
import com.jsp.ets.rating.Rating;
import com.jsp.ets.stack.Stack;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tech_stack")
	private Stack stack;
	
	@OneToMany
	private List<Rating> ratings;
	
	@ManyToMany
	private List<Batch>  batchs;

}
