package com.jsp.ets.rating;

import java.util.List;

import com.jsp.ets.exception.RatingNotFoundByIdException;
import org.springframework.stereotype.Service;

import com.jsp.ets.user.Student;
import com.jsp.ets.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatingService {
	
	private RatingRepository ratingRepository;
	
	private UserRepository userRepository;
	
	private RatingMapper ratingMapper;
	
	
	public RatingResponseDTO updateStudentRating(RatingRequestDTO ratingRequestDTO, String ratingId) {
		return ratingRepository.findById(ratingId).map(rating -> {
			rating = ratingMapper.mapRatingToEntity(ratingRequestDTO, rating);
			rating = ratingRepository.save(rating);
			return ratingMapper.mapRatingToResponse(rating);

		}).orElseThrow(() -> new RatingNotFoundByIdException("rating object not found by id!!"));
	}
	
	
	
	public List<RatingResponseDTO> getStudentRating(String studentId) {
		return userRepository.findById(studentId).map(user -> {
			Student student = (Student) user;
			return student.getRatings().stream().map(rating -> ratingMapper.mapRatingToResponse(rating)).toList();
		}).orElseThrow(() -> new RatingNotFoundByIdException("batch not found by id!!"));

	}
}
