package com.jsp.ets.btach;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.jsp.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BatchResponseDTO {

	private String batchId;
	
	private String title;
	
	
	private LocalTime beginsAt;

	private LocalTime endsAt;

	
	private LocalDateTime startingDate;
	
	private BatchStatus batchStatus;
	
	private List<Subject> subjects;
}
