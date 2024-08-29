package com.jsp.ets.hr;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.user.UserRepository;

import com.jsp.ets.user.UserResponseDto;
import com.jsp.ets.user.UserService;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class HRController {

	private UserService adminService;

	private AppResponseBuilder responseBuilder;

	public HRController(UserService adminService, AppResponseBuilder responseBuilder) {
		super();
		this.adminService = adminService;
		this.responseBuilder = responseBuilder;
	}

	@PostMapping("/hr")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerHR(
			@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponseDto userResponseDto = adminService.registerHR(registrationRequestDTO);
		return responseBuilder.success(HttpStatus.CREATED, "HR Created successfully", userResponseDto);
	}

}
