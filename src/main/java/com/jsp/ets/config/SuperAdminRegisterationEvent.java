package com.jsp.ets.config;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jsp.ets.user.Privilege;
import com.jsp.ets.user.SuperAdmin;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserRepository;
import com.jsp.ets.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //logging the  track
@Component
public class SuperAdminRegisterationEvent {

	private UserRepository userRepository;

	@Value("${super_admin.email}")
	private String superAdminEmail;

	private String superAdminPassword = UUID.randomUUID().toString();

	public SuperAdminRegisterationEvent(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void registerSuperAdmin() {
		log.info("Checking If SUPER_ADMIN present");
		List<User> users = userRepository.findByRole(UserRole.SUPER_ADMIN);
		if (users.isEmpty()) {
			log.info("super admin is not present ,Creating one");
			User user = new SuperAdmin();
			user.setEmail(superAdminEmail);
			user.setPassword(superAdminPassword);
			user.setRole(UserRole.SUPER_ADMIN);
			userRepository.save(user);
		}else {
			log.info("super admin is presents with email"+ users.getFirst().getEmail());
		}
	}

}
