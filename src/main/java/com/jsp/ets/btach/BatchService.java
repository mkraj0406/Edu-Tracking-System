package com.jsp.ets.btach;

import org.springframework.stereotype.Service;

import com.jsp.ets.exception.ObjectNotFoundByIdException;


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
		 BatchResponseDTO batchResponseDTO = batchMapper.mapBatchToResponse(batch);
		 return batchResponseDTO;
	}
	
	
	public BatchResponseDTO updateBatch(BatchRequestDTO batchRequestDTO, String batchId) {
		return batchRepository.findById(batchId).map(batch -> {
			batch = batchMapper.mapBatchToEntity(batchRequestDTO, batch);
			batch = batchRepository.save(batch);
			return batchMapper.mapBatchToResponse(batch);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("batch not found by id!!"));
	}

	public BatchResponseDTO updateBatchStatus(BatchStatus batchStatus, String batchId) {
		return batchRepository.findById(batchId).map(batch -> {
			batch.setBatchStatus(batchStatus);
			batchRepository.save(batch);
			return batchMapper.mapBatchToResponse(batch);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("batch not found by id!!"));
	}

}
