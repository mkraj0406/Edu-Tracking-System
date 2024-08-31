package com.jsp.ets.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResponseDto {

	private String userId;
	private String username;
	private String email;
	private UserRole role;
	private LocalDateTime created_date;
	private LocalDateTime modified_date;

}
