package com.jsp.ets.btach;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.user.Subject;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BatchController {
	
	private BatchService batchService;
	
	private AppResponseBuilder appResponseBuilder;
	
	@PostMapping("/batchs")
	private ResponseEntity<ResponseStructure<BatchResponseDTO>> createBatch(@RequestBody @Valid BatchRequestDTO batchRequestDTO){
		  BatchResponseDTO batchResponseDTO = batchService.createBatch(batchRequestDTO);
		  
		  return appResponseBuilder.success(HttpStatus.CREATED, "Batch succsessfully created", batchResponseDTO);
		
	}
	
}
