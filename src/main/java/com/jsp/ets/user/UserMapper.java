package com.jsp.ets.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
	
	
	public User mapUserToEntity(UserRequestDto userRequestDto,User user) {
		user.setUsername(userRequestDto.getUsername());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(userRequestDto.getPassword());
		user.setRole(userRequestDto.getRole());
		
		return user;
	}
	
}
