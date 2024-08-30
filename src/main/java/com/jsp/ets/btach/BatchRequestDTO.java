package com.jsp.ets.btach;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.jsp.ets.user.Subject;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRequestDTO {

	@NotNull(message = "username can't be null")
	@NotBlank(message = "Name is mandatory")
	private String title;

	private LocalTime beginsAt;

	private LocalTime endsAt;

	private LocalDateTime startingDate;
	
	private List<Subject> subjects;
}
