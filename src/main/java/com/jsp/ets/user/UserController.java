package com.jsp.ets.user;


import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.stack.Stack;

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
public class UserController {

	private UserService userService;

	private AppResponseBuilder responseBuilder;

	@Operation(description = "The API endpoint is used to reagister the admin", responses = {
			@ApiResponse(responseCode = "201", description = "admin Registred"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PostMapping("/register/admins")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerAdmin(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, UserRole.ADMIN);
		return responseBuilder.success(HttpStatus.ACCEPTED, "Admin successfully registered", userResponseDto);
	}

	@Operation(description = "The API endpoint is used to register the HR", responses = {
			@ApiResponse(responseCode = "201", description = "HR Registred"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PostMapping("/hr/register")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerHR(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, UserRole.HR);
		return responseBuilder.success(HttpStatus.CREATED, "HR Created successfully", userResponseDto);
	}

	@Operation(description = "The API endpoint is used to register the Trainer", responses = {
			@ApiResponse(responseCode = "201", description = "Trainer Registred"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PostMapping("/register/trainers")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerTainer(
			@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer Created successfully", userResponseDto);
	}

	@Operation(description = "The API endpoint is used to update Trainer For Subject", responses = {
			@ApiResponse(responseCode = "201", description = "Trainer updated"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PutMapping("/trainers/{userId}/subjects")
	public ResponseEntity<ResponseStructure<TrainerResponseDTO>> updateTrainerForSubject(
			@RequestBody @Valid TrainerRequestDTO trainerRequestDTO, @PathVariable String userId) {
		TrainerResponseDTO trainerResponseDTO = userService.updateTrainerForSubject(trainerRequestDTO, userId);
		return responseBuilder.success(HttpStatus.OK, "Trainer Updated Successfully", trainerResponseDTO);
	}

	@Operation(description = "The API endpoint is used to register the Student", responses = {
			@ApiResponse(responseCode = "201", description = "Student registerd"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PostMapping("/register/students")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerStudent(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
		UserResponseDto userResponseDto = userService.registerUser(registrationRequestDTO, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.CREATED, "Student Created successfully", userResponseDto);
	}

	@Operation(description = "The API endpoint is used to update Students with additinal details", responses = {
			@ApiResponse(responseCode = "201", description = "Student updated successfully"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(
			@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable String userId) {
		StudentResponseDTO studentResponseDTO = userService.updateStudent(studentRequestDTO, userId);

		return responseBuilder.success(HttpStatus.OK, "Student updated successfully", studentResponseDTO);
	}

	@Operation(description = "The API endpoint is used to update Student For Stack", responses = {
			@ApiResponse(responseCode = "201", description = "Student updated"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))

			}) })
	@PatchMapping("/students/{studentId}/stacks")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam Stack stack,
			@PathVariable String studentId) {
		StudentResponseDTO studentResponseDTO = userService.updatedStudentStack(stack, studentId);

		return responseBuilder.success(HttpStatus.OK, "stack successfully updated", studentResponseDTO);
	}

	@PostMapping("/verify/users")
	public ResponseEntity<ResponseStructure<UserResponseDto>> userOtpVerfication(@RequestBody OtpRequestDTO otpRequestDTO){
		 UserResponseDto userResponseDto= userService.userOtpVerification(otpRequestDTO);
		 return responseBuilder.success(HttpStatus.CREATED,"Otp verified successfully",userResponseDto);
	}



	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<UserResponseDto>> login(@RequestBody LoginRequestDTO loginRequestDTO){
		   return   userService.loginUser(loginRequestDTO);
	}

	@PostMapping("/refresh-login")
	public ResponseEntity<ResponseStructure<UserResponseDto>> refreshLogin(){
		return userService.refreshLogin();
	}
}
