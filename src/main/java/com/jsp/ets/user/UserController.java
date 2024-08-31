package com.jsp.ets.user;

import java.util.List;

import org.springframework.context.support.BeanDefinitionDsl.Role;
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

	@PostMapping("/admins/register")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerAdmin(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO,
			@RequestParam UserRole role) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "Admin successfully registered", userResponseDto);
	}

	@PostMapping("/hr/register")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerHR(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, UserRole.HR);
		return responseBuilder.success(HttpStatus.CREATED, "HR Created successfully", userResponseDto);
	}

	@PostMapping("/trainers/register")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerTainer(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO ) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer Created successfully", userResponseDto);
	}

	@PutMapping("/trainers/{userId}/subjects")
	public ResponseEntity<ResponseStructure<TrainerResponseDTO>> updateTrainerForSubject(
			@RequestBody @Valid TrainerRequestDTO trainerRequestDTO, @PathVariable String userId) {
		TrainerResponseDTO trainerResponseDTO = userService.updateTrainerForSubject(trainerRequestDTO, userId);
		return responseBuilder.success(HttpStatus.OK, "Trainer Updated Successfully", trainerResponseDTO);
	}

	@PutMapping("/ratings/{ratingId}/students")
	public ResponseEntity<ResponseStructure<RatingResponseDTO>> updateStudentRating(
			@RequestBody @Valid RatingRequestDTO ratingRequestDTO, @PathVariable String ratingId) {
		RatingResponseDTO ratingResponseDTO = userService.updateStudentRating(ratingRequestDTO, ratingId);
		return responseBuilder.success(HttpStatus.OK, "Students is successfully updated with rating",
				ratingResponseDTO);
	}

	@PostMapping("/students/register")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerStudent(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.CREATED, "Stduent Created successfully", userResponseDto);
	}

	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(
			@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable String userId) {
		StudentResponseDTO studentResponseDTO = userService.updateStudent(studentRequestDTO, userId);

		return responseBuilder.success(HttpStatus.OK, "Student updated succesfully", studentResponseDTO);
	}

	@PatchMapping("/students/{studentId}/stacks")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam Stack stack,
			@PathVariable String studentId) {
		StudentResponseDTO studentResponseDTO = userService.updatedStudentStack(stack, studentId);

		return responseBuilder.success(HttpStatus.OK, "stack successfully updated", studentResponseDTO);
	}

	@GetMapping("/students/{studentId}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponseDTO>>> getStudentRating(@PathVariable String studentId) {
		List<RatingResponseDTO> ratingResponseDTOs = userService.getStudentRating(studentId);
		return responseBuilder.success(HttpStatus.FOUND, "student object found succesfully", ratingResponseDTOs);
	}

}
