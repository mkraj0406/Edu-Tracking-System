package com.jsp.ets.rating;

import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
	
	public Rating mapRatingToEntity(RatingRequestDTO ratingRequestDTO,Rating rating) {
		rating.setRating(ratingRequestDTO.getRating());
		rating.setFeedback(ratingRequestDTO.getFeedback());
		return rating;
	}
	
	
	public RatingResponseDTO mapRatingToResponse(Rating rating) {
		RatingResponseDTO ratingResponseDTO = new RatingResponseDTO();
		ratingResponseDTO.setRating(rating.getRating());
		ratingResponseDTO.setFeedback(rating.getFeedback());
		ratingResponseDTO.setSubject(rating.getSubject());
		return ratingResponseDTO;
	}
}
