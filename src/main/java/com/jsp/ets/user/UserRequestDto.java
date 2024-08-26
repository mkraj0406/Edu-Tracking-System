package com.jsp.ets.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

	@NotNull(message = "username can't be null")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$")
	private String username;

	@NotNull(message = "email can't be null")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com$", message = "Invalid Email, The email should ends with @gamil.com")
	private String email;

	@Pattern(regexp = "^[A-Z][a-z]{6}\\d{2}[~!@#$%^&*]$", message = "Minimum length of 8 characters. At least one uppercase letter.At least one lowercase letter.At least one digit.At least one special character (e.g., !@#$%^&*())")
	private String password;

	private String role;

}
