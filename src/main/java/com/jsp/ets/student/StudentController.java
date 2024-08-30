package com.jsp.ets.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.stack.Stack;
import com.jsp.ets.user.UserResponseDto;
import com.jsp.ets.user.UserService;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.val;

@RestController
public class StudentController {
	
private AppResponseBuilder responseBuilder;
	
	private UserService studentService;

	
	
	public StudentController(AppResponseBuilder responseBuilder, UserService studentService) {
		super();
		this.responseBuilder = responseBuilder;
		this.studentService = studentService;
	}



	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerStudent(
			@org.springframework.web.bind.annotation.RequestBody  @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponseDto userResponseDto = studentService.registerStudent(registrationRequestDTO);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer Created successfully", userResponseDto);
	}
	
	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(@RequestBody StudentRequestDTO studentRequestDTO,@PathVariable String userId){
		StudentResponseDTO studentResponseDTO= studentService.updateStudent(studentRequestDTO,userId);
		
		return responseBuilder.success(HttpStatus.OK , "Student updated succesfully", studentResponseDTO);
	}
	
	@PatchMapping("/students/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam Stack stack,@PathVariable String studentId){
		 StudentResponseDTO studentResponseDTO = studentService.updatedStudentStack(stack,studentId);
		 
		 return responseBuilder.success(HttpStatus.OK, "stack successfully updated", studentResponseDTO);
	}
}
