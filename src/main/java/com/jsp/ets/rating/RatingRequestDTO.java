package com.jsp.ets.rating;

import com.jsp.ets.user.Subject;

import lombok.Getter;

@Getter
public class RatingRequestDTO {

	private Subject subject;

	private int rating;

	private String feedback;

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
	
}
