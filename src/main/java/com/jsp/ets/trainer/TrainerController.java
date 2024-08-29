package com.jsp.ets.trainer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.user.Subject;
import com.jsp.ets.user.UserResponseDto;
import com.jsp.ets.user.UserService;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class TrainerController {

	private AppResponseBuilder responseBuilder;

	private UserService trainerService;

	

	public TrainerController(AppResponseBuilder responseBuilder, UserService trainerService) {
		super();
		this.responseBuilder = responseBuilder;
		this.trainerService = trainerService;
	}

	@PostMapping("/trainers")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerTainer(
			@org.springframework.web.bind.annotation.RequestBody  RegistrationRequestDTO registrationRequestDTO) {
		UserResponseDto userResponseDto = trainerService.registerTrainer(registrationRequestDTO);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer Created successfully", userResponseDto);
	}

	@PutMapping("/trainers/{userId}")
	public ResponseEntity<ResponseStructure<TrainerResponseDTO>> updateTrainer(
			@RequestBody TrainerRequestDTO trainerRequestDTO,@PathVariable String userId) {
		 TrainerResponseDTO trainerResponseDTO= trainerService.updateTrainer(trainerRequestDTO,userId);
		 return responseBuilder.success(HttpStatus.OK, "Trainer Updated Successfully", trainerResponseDTO);
	}

}
