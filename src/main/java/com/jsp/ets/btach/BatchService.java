package com.jsp.ets.btach;

import com.jsp.ets.exception.BatchNotFoundByIdException;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatchService {

	private BatchRepository batchRepository;

	private BatchMapper batchMapper;

	public BatchResponseDTO createBatch(BatchRequestDTO batchRequestDto) {
		 Batch batch = batchMapper.mapBatchToEntity(batchRequestDto, new Batch());
		 batch.setBatchStatus(BatchStatus.CREATED);
		 batch = batchRepository.save(batch);
		 return batchMapper.mapBatchToResponse(batch);
	}
	
	
	public BatchResponseDTO updateBatch(BatchRequestDTO batchRequestDTO, String batchId) {
		return batchRepository.findById(batchId).map(batch -> {
			batch = batchMapper.mapBatchToEntity(batchRequestDTO, batch);
			batch = batchRepository.save(batch);
			return batchMapper.mapBatchToResponse(batch);
		}).orElseThrow(() -> new BatchNotFoundByIdException("batch not found by id!!"));
	}

	public BatchResponseDTO updateBatchStatus(BatchStatus batchStatus, String batchId) {
		return batchRepository.findById(batchId).map(batch -> {
			batch.setBatchStatus(batchStatus);
			batchRepository.save(batch);
			return batchMapper.mapBatchToResponse(batch);
		}).orElseThrow(() -> new BatchNotFoundByIdException("batch not found by id!!"));
	}

}
