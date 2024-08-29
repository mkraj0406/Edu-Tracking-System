package com.jsp.ets.rating;

import com.jsp.ets.config.GenerateSequenceId;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ratings")
public class Rating {
	
	@Column(name = "ratingId")
	@GenerateSequenceId
	@Id
	private String ratingId;
	
	@Column(name = "subject")
	@Enumerated(EnumType.STRING)
	private Subject subject;
	
	
	@Column(name = "rating")
	private int rating;
	
	@Column(name = "feedback")
	private String feedback;
	
	@ManyToOne
	private Student student;
	
	
	
}
