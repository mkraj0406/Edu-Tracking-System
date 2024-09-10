package com.jsp.ets.user;

import java.time.LocalDateTime;

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
