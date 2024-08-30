package com.jsp.ets.btach;

import org.springframework.stereotype.Service;

import com.jsp.ets.user.Student;
import com.jsp.ets.user.UserService;

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

}
