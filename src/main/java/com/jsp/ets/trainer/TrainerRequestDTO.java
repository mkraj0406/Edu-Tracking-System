package com.jsp.ets.trainer;

import java.util.List;

import com.jsp.ets.user.Subject;
import com.jsp.ets.user.UserRequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerRequestDTO extends UserRequestDTO {

//	@NotNull(message = "username can't be null")
//	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$")
//	private String username;
//
//	@NotNull(message = "email can't be null")
//	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com$", message = "Invalid Email, The email should ends with @gamil.com")
//	private String email;
	
	List<Subject> subjects;
}
