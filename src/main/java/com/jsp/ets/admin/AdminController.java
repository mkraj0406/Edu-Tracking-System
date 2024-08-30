package com.jsp.ets.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.btach.BatchRepository;
import com.jsp.ets.btach.BatchRequestDTO;
import com.jsp.ets.btach.BatchResponseDTO;
import com.jsp.ets.btach.BatchStatus;
import com.jsp.ets.user.UserService;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AdminController {
	
	
	private UserService adminService;
	
	private AppResponseBuilder responseBuilder;
	
	@PutMapping("/admins/batchs/{batch-id}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatch(@RequestBody @Valid BatchRequestDTO batchRequestDTO,String batchId){
		   BatchResponseDTO batchResponseDTO= adminService.updateBatch(batchRequestDTO,batchId);
		   return  responseBuilder.success(HttpStatus.OK, "Batch updated successfully", batchResponseDTO);
		
	}
	
	@PatchMapping("/admins/batchs/closed/{batch-id}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatus(@PathVariable String batchId){
		 BatchResponseDTO batchResponseDTO= adminService.updateBatchStatus(BatchStatus.CANCELLED, batchId);
		 return responseBuilder.success(HttpStatus.OK, "batchstatus updated successfully", batchResponseDTO);
	}
	
	@PatchMapping("/admins/batchs/cancel/{batch-id}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToClosed(@RequestParam BatchStatus batchStatus,@PathVariable String batchId){
		 BatchResponseDTO batchResponseDTO= adminService.updateBatchStatus(BatchStatus.CLOSED, batchId);
		 return responseBuilder.success(HttpStatus.OK, "batchstatus updated successfully", batchResponseDTO);
	}
	
	
}
