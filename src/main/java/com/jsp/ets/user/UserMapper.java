package com.jsp.ets.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.ets.security.RegistrationRequestDTO;

@Component
@AllArgsConstructor
public class UserMapper {

	private final PasswordEncoder passwordEncoder;

	public User mapUserToEntity(RegistrationRequestDTO registrationRequestDTO, User user) {
		user.setUsername(registrationRequestDTO.getUsername());
		user.setEmail(registrationRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
		return user;
	}

	public UserResponseDto mapUserToResponce(User user) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserId(user.getUserId());
		userResponseDto.setUsername(user.getUsername());
		userResponseDto.setRole(user.getRole());
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setCreated_date(user.getCreated_date());
		userResponseDto.setModified_date(user.getModified_date());

		return userResponseDto;
	}

	public Trainer mapTrainerToEntity(TrainerRequestDTO trainerRequestDTO, Trainer trainer) {
		trainer.setUsername(trainerRequestDTO.getUsername());
		trainer.setEmail(trainerRequestDTO.getEmail());
		trainer.setSubjects(trainerRequestDTO.getSubjects());

		return trainer;
	}

	public TrainerResponseDTO mapTainerToResponce(Trainer trainer) {
		TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO();
		trainerResponseDTO.setUserId(trainer.getUserId());
		trainerResponseDTO.setUsername(trainer.getUsername());
		trainerResponseDTO.setEmail(trainer.getEmail());
		trainerResponseDTO.setRole(trainer.getRole());
		trainerResponseDTO.setCreated_date(trainer.getCreated_date());
		trainerResponseDTO.setModified_date(trainer.getModified_date());
		trainerResponseDTO.setSubjects(trainer.getSubjects());

		return trainerResponseDTO;
	}


	public Student mapStudentToEntity(StudentRequestDTO studentRequestDTO, Student student) {
		student.setUsername(studentRequestDTO.getUsername());
		student.setEmail(studentRequestDTO.getEmail());
		student.setDegree(studentRequestDTO.getDegree());
		student.setYop(studentRequestDTO.getYop());
		student.setStream(studentRequestDTO.getStream());
		student.setDegreePercentage(studentRequestDTO.getDegreePercentage());
		student.setTwelthPercentage(studentRequestDTO.getTwelthPercentage());
		student.setTenthPercentage(studentRequestDTO.getTenthPercentage());

		return student;
	}

	public StudentResponseDTO mapStudentToResponse(Student student) {
		StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
		studentResponseDTO.setUserId(student.getUserId());
		studentResponseDTO.setEmail(student.getEmail());
		studentResponseDTO.setDegree(student.getDegree());
		studentResponseDTO.setYop(student.getYop());
		studentResponseDTO.setStream(student.getStream());
		studentResponseDTO.setDegreePercentage(student.getDegreePercentage());
		studentResponseDTO.setTwelthPercentage(student.getTwelthPercentage());
		studentResponseDTO.setTenthPercentage(student.getTenthPercentage());
		studentResponseDTO.setStack(student.getStack());

		return studentResponseDTO;

	}

}
