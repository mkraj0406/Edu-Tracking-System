package com.jsp.ets.btach;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BatchController {

	private BatchService batchService;

	private AppResponseBuilder responseBuilder;

	@PostMapping("/batchs")
	private ResponseEntity<ResponseStructure<BatchResponseDTO>> createBatch(
			@RequestBody @Valid BatchRequestDTO batchRequestDTO) {
		BatchResponseDTO batchResponseDTO = batchService.createBatch(batchRequestDTO);

		return responseBuilder.success(HttpStatus.CREATED, "Batch successfully created", batchResponseDTO);

	}

	@PutMapping("batchs/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatch(
			@RequestBody @Valid BatchRequestDTO batchRequestDTO, String batchId) {
		BatchResponseDTO batchResponseDTO = batchService.updateBatch(batchRequestDTO, batchId);
		return responseBuilder.success(HttpStatus.OK, "Batch updated successfully", batchResponseDTO);

	}

	@PatchMapping("batchs/cancel/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToCancel(@PathVariable String batchId) {
		BatchResponseDTO batchResponseDTO = batchService.updateBatchStatus(BatchStatus.CANCELLED, batchId);
		return responseBuilder.success(HttpStatus.OK, "batchstatus updated successfully", batchResponseDTO);
	}

	@PatchMapping("/batchs/closed/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToClosed(
			@RequestParam BatchStatus batchStatus, @PathVariable String batchId) {
		BatchResponseDTO batchResponseDTO = batchService.updateBatchStatus(BatchStatus.CLOSED, batchId);
		return responseBuilder.success(HttpStatus.OK, "batchstatus updated successfully", batchResponseDTO);
	}

}
