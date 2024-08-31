package com.jsp.ets.trainer;

import java.util.List;

import com.jsp.ets.user.Subject;
import com.jsp.ets.user.UserResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerResponseDTO extends UserResponseDto {

	private List<Subject> subjects;

}
