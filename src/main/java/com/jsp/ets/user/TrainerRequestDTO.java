package com.jsp.ets.user;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerRequestDTO extends UserRequestDTO {

	private List<Subject> subjects;
}
