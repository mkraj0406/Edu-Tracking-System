package com.jsp.ets.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.security.RegistrationRequestDTO;

import com.jsp.ets.user.UserResponseDto;
import com.jsp.ets.utility.AppResponseBuilder;

import com.jsp.ets.utility.ResponseStructure;


import jakarta.validation.Valid;

@RestController
public class UserController {

	private UserService adminService;
	
	private AppResponseBuilder responseBuilder;

	




	public UserController(UserService adminService, AppResponseBuilder responseBuilder) {
		super();
		this.adminService = adminService;
		this.responseBuilder = responseBuilder;
	}






	@PostMapping("/users/admins")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerAdmin(@org.springframework.web.bind.annotation.RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
		UserResponseDto  userResponseDto= adminService.registerAdmin(registrationRequestDTO);
		return responseBuilder.success(HttpStatus.CREATED, "Admin successfully registered", userResponseDto);
	}
	
	
	

	

}
