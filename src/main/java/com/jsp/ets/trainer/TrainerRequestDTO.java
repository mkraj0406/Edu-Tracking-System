package com.jsp.ets.trainer;

import java.util.List;

import com.jsp.ets.user.Subject;
import com.jsp.ets.user.UserRequestDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerRequestDTO extends UserRequestDTO {


	private List<Subject> subjects;
}
