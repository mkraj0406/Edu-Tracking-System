package com.jsp.ets.btach;

import org.springframework.stereotype.Component;

@Component
public class BatchMapper {
	
	
	public Batch mapBatchToEntity(BatchRequestDTO batchRequestDTO,Batch batch) {
		batch.setTitle(batchRequestDTO.getTitle());
		batch.setBeginsAt(batchRequestDTO.getBeginsAt());
		batch.setEndsAt(batchRequestDTO.getEndsAt());
		batch.setSubjects(batchRequestDTO.getSubjects());
		
		return batch;
	}
	
	
	public BatchResponseDTO mapBatchToResponse(Batch batch) {
		BatchResponseDTO batchResponseDTO = new BatchResponseDTO();
		batchResponseDTO.setBatchId(batch.getBatchId());
		batchResponseDTO.setTitle(batch.getTitle());
		batchResponseDTO.setBeginsAt(batch.getBeginsAt());
		batchResponseDTO.setEndsAt(batch.getEndsAt());
		batchResponseDTO.setStartingDate(batch.getStartingDate());
		batchResponseDTO.setSubjects(batch.getSubjects());
		
		return batchResponseDTO;
	}
}
