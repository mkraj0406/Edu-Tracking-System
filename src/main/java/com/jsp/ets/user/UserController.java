package com.jsp.ets.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.btach.BatchRequestDTO;
import com.jsp.ets.btach.BatchResponseDTO;
import com.jsp.ets.btach.BatchStatus;
import com.jsp.ets.rating.RatingRequestDTO;
import com.jsp.ets.rating.RatingResponseDTO;
import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.stack.Stack;
import com.jsp.ets.student.StudentRequestDTO;
import com.jsp.ets.student.StudentResponseDTO;
import com.jsp.ets.trainer.TrainerRequestDTO;
import com.jsp.ets.trainer.TrainerResponseDTO;
import com.jsp.ets.user.UserResponseDto;
import com.jsp.ets.utility.AppResponseBuilder;

import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;

	private AppResponseBuilder responseBuilder;

	@PostMapping("/users/admins")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerAdmin(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO,
			@RequestParam UserRole role) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "Admin successfully registered", userResponseDto);
	}

	@PutMapping("/users/admins/batchs/{batch-id}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatch(
			@RequestBody @Valid BatchRequestDTO batchRequestDTO, String batchId) {
		BatchResponseDTO batchResponseDTO = userService.updateBatch(batchRequestDTO, batchId);
		return responseBuilder.success(HttpStatus.OK, "Batch updated successfully", batchResponseDTO);

	}

	@PatchMapping("/users/admins/batchs/closed/{batch-id}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatus(@PathVariable String batchId) {
		BatchResponseDTO batchResponseDTO = userService.updateBatchStatus(BatchStatus.CANCELLED, batchId);
		return responseBuilder.success(HttpStatus.OK, "batchstatus updated successfully", batchResponseDTO);
	}

	@PatchMapping("/users/admins/batchs/cancel/{batch-id}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToClosed(
			@RequestParam BatchStatus batchStatus, @PathVariable String batchId) {
		BatchResponseDTO batchResponseDTO = userService.updateBatchStatus(BatchStatus.CLOSED, batchId);
		return responseBuilder.success(HttpStatus.OK, "batchstatus updated successfully", batchResponseDTO);
	}

	@PostMapping("/users/hr")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerHR(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO,
			@RequestParam UserRole role) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "HR Created successfully", userResponseDto);
	}

	@PostMapping("/users/trainers")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerTainer(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO, @RequestParam UserRole role) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer Created successfully", userResponseDto);
	}

	@PutMapping("/users/trainers/{userId}")
	public ResponseEntity<ResponseStructure<TrainerResponseDTO>> updateTrainerForSubject(
			@RequestBody TrainerRequestDTO trainerRequestDTO, @PathVariable String userId) {
		TrainerResponseDTO trainerResponseDTO = userService.updateTrainerForSubject(trainerRequestDTO, userId);
		return responseBuilder.success(HttpStatus.OK, "Trainer Updated Successfully", trainerResponseDTO);
	}

	@PutMapping("/users/trainers/ratings/{rating-id}")
	public ResponseEntity<ResponseStructure<RatingResponseDTO>> updateStudentRating(
			@RequestBody @Valid RatingRequestDTO ratingRequestDTO, @PathVariable String ratingId) {
		RatingResponseDTO ratingResponseDTO = userService.updateStudentRating(ratingRequestDTO, ratingId);
		return responseBuilder.success(HttpStatus.OK, "Students is successfully updated with rating",
				ratingResponseDTO);
	}

	@PostMapping("/users/students")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerStudent(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO,
			@RequestParam UserRole role) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "Stduent Created successfully", userResponseDto);
	}

	@PutMapping("/users/students/{user-id}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(
			@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable String userId) {
		StudentResponseDTO studentResponseDTO = userService.updateStudent(studentRequestDTO, userId);

		return responseBuilder.success(HttpStatus.OK, "Student updated succesfully", studentResponseDTO);
	}

	@PatchMapping("/users/students/{student-Id}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam Stack stack,
			@PathVariable String studentId) {
		StudentResponseDTO studentResponseDTO = userService.updatedStudentStack(stack, studentId);

		return responseBuilder.success(HttpStatus.OK, "stack successfully updated", studentResponseDTO);
	}

	@GetMapping("/users/students/ratings/{student-id}")
	public ResponseEntity<ResponseStructure<List<RatingResponseDTO>>> getStudentRating(String studentId) {
		List<RatingResponseDTO> ratingResponseDTOs = userService.getStudentRating(studentId);
		return responseBuilder.success(HttpStatus.FOUND, "student object found succesfully", ratingResponseDTOs);
	}

}
