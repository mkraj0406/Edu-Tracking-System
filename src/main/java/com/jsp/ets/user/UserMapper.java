package com.jsp.ets.user;

import org.springframework.stereotype.Component;

import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.student.StudentRequestDTO;
import com.jsp.ets.student.StudentResponseDTO;
import com.jsp.ets.trainer.TrainerRequestDTO;
import com.jsp.ets.trainer.TrainerResponseDTO;

@Component
public class UserMapper {

	
	public User mapUserToEntity(RegistrationRequestDTO registrationRequestDTO, User user) {
		user.setUsername(registrationRequestDTO.getUsername());
		user.setEmail(registrationRequestDTO.getEmail());
		if (user instanceof Admin) {
			user.setRole(UserRole.ADMIN);
		} else if (user instanceof HR) {
			user.setRole(UserRole.HR);
		} else if (user instanceof Trainer) {
			user.setRole(UserRole.TRAINER);
		} else if (user instanceof Student) {
			user.setRole(UserRole.STUDENT);
		}

		return user;
	}
	

	public UserResponseDto mapUserToResponce(User user) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserId(user.getUserId());
		userResponseDto.setUsername(user.getUsername());
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setCreated_date(user.getCreated_date());
		userResponseDto.setModified_date(user.getModified_date());

		return userResponseDto;
	}
	
	
	//-------------------------------------------------------------------------------------------
	public Trainer mapTrainerToEntity(TrainerRequestDTO trainerRequestDTO, Trainer trainer) {
		trainer.setUsername(trainerRequestDTO.getUsername());
		trainer.setEmail(trainerRequestDTO.getEmail());
		trainer.setSubjects(trainerRequestDTO.getSubjects());

		return trainer;
	}
	
	public TrainerResponseDTO mapTainerToResponce(Trainer trainer) {
		TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO();

		trainerResponseDTO.setUsername(trainer.getUsername());
		trainerResponseDTO.setEmail(trainer.getEmail());
		trainerResponseDTO.setCreated_date(trainer.getCreated_date());
		trainerResponseDTO.setModified_date(trainer.getModified_date());
		trainerResponseDTO.setSubjects(trainer.getSubjects());

		return trainerResponseDTO;
	}
	
	//-------------------------------------------------------------------------------------------
	
	public Student mapStudentToEntity(StudentRequestDTO studentRequestDTO, Student student) {
		student.setUsername(studentRequestDTO.getUsername());
		student.setEmail(studentRequestDTO.getEmail());
		student.setDegree(studentRequestDTO.getDegree());
		student.setYear_of_passout(studentRequestDTO.getYear_of_passout());
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
		studentResponseDTO.setYear_of_passout(student.getYear_of_passout());
		studentResponseDTO.setStream(student.getStream());
		studentResponseDTO.setDegreePercentage(student.getDegreePercentage());
		studentResponseDTO.setTwelthPercentage(student.getTwelthPercentage());
		studentResponseDTO.setTenthPercentage(student.getTenthPercentage());
		studentResponseDTO.setStack(student.getStack());
		
		return studentResponseDTO;

	}
	
	//-------------------------------------------------------------------------------------------
}
