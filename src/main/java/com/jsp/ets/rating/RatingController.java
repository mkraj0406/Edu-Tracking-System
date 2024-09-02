package com.jsp.ets.rating;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RatingController {

	private RatingService ratingService;

	private AppResponseBuilder responseBuilder;

	@Operation(description = "The API endpoint is used to update Student For Rating", responses = {
			@ApiResponse(responseCode = "201", description = "student updated"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PutMapping("/ratings/{ratingId}/students")
	public ResponseEntity<ResponseStructure<RatingResponseDTO>> updateStudentRating(
			@RequestBody @Valid RatingRequestDTO ratingRequestDTO, @PathVariable String ratingId) {
		RatingResponseDTO ratingResponseDTO = ratingService.updateStudentRating(ratingRequestDTO, ratingId);
		return responseBuilder.success(HttpStatus.OK, "Students is successfully updated with rating",
				ratingResponseDTO);
	}
	
	@Operation(description = "The API endpoint is used to show student ratings", responses = {
			@ApiResponse(responseCode = "201", description = "student data fetched"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@GetMapping("/students/{studentId}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponseDTO>>> getStudentRating(@PathVariable String studentId) {
		List<RatingResponseDTO> ratingResponseDTOs = ratingService.getStudentRating(studentId);
		return responseBuilder.success(HttpStatus.FOUND, "student object found succesfully", ratingResponseDTOs);
	}
}
