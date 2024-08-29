package com.jsp.ets.user;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.stack.Stack;
import com.jsp.ets.student.StudentRequestDTO;
import com.jsp.ets.student.StudentResponseDTO;
import com.jsp.ets.trainer.TrainerRequestDTO;
import com.jsp.ets.trainer.TrainerResponseDTO;
import com.jsp.ets.user.Admin;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserMapper;
import com.jsp.ets.user.UserRepository;

import com.jsp.ets.user.UserResponseDto;

import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Service
public class UserService {

	private UserRepository userRepository;
	
	private RatingRepository ratingRepository;

	private UserMapper userMapper;

	

	public UserService(UserRepository userRepository, RatingRepository ratingRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.ratingRepository = ratingRepository;
		this.userMapper = userMapper;
	}

	public UserResponseDto registerAdmin(RegistrationRequestDTO registrationRequestDTO) {
		User user = userMapper.mapUserToEntity(registrationRequestDTO, new Admin());
		user = userRepository.save(user);
		UserResponseDto userResponseDto = userMapper.mapUserToResponce(user);

		return userResponseDto;
	}

	public UserResponseDto registerHR(RegistrationRequestDTO registrationRequestDTO) {
		User user = userMapper.mapUserToEntity(registrationRequestDTO, new HR());
		user = userRepository.save(user);
		UserResponseDto userResponseDto = userMapper.mapUserToResponce(user);
		return userResponseDto;
	}

	public UserResponseDto registerTrainer(RegistrationRequestDTO registrationRequestDTO) {
		User user = userMapper.mapUserToEntity(registrationRequestDTO, new Trainer());
		user = userRepository.save(user);
		UserResponseDto userResponseDto = userMapper.mapUserToResponce(user);
		return userResponseDto;
	}

	public UserResponseDto registerStudent(RegistrationRequestDTO registrationRequestDTO) {
		User user = userMapper.mapUserToEntity(registrationRequestDTO, new Student());
		user = userRepository.save(user);
		UserResponseDto userResponseDto = userMapper.mapUserToResponce(user);
		return userResponseDto;
	}
	
	//------------------------------------------------------------------------------------------

	public TrainerResponseDTO updateTrainer(TrainerRequestDTO trainerRequestDTO, String userId) {
//		Optional<User> optional = userRepository.findById(userId);
//		if (optional.isPresent()) {
//			User user = optional.get();
//			Trainer trainer = (Trainer) user;
//			trainer = userMapper.mapTrainerToEntity(trainerRequestDTO, trainer);
//			trainer = userRepository.save(trainer);
//			TrainerResponseDTO trainerResponseDTO = userMapper.mapTainerToResponce(trainer);
//			return trainerResponseDTO;
//		} else {
//			return null;
//		}

		return userRepository.findById(userId).map(user -> {
			userMapper.mapTrainerToEntity(trainerRequestDTO,(Trainer)user);
			userRepository.save((Trainer)user);
			return userMapper.mapTainerToResponce((Trainer)user);
			
		}).orElse(null);
		
	}

	public StudentResponseDTO updateStudent(StudentRequestDTO studentRequestDTO, String userId) {
//		Optional<User> optional = userRepository.findById(userId);
//		if (optional.isPresent()) {
//			User user = optional.get();
//			Student student = (Student) user;
//			student = userMapper.mapStudentToEntity(studentRequestDTO,student);
//			userRepository.save(student);
//			StudentResponseDTO studentResponseDTO = userMapper.mapStudentToResponse(student);
//			return studentResponseDTO;
//		}else {
//			return null;
//		}
		
		
		
		return userRepository.findById(userId).map(user -> {
			userMapper.mapStudentToEntity(studentRequestDTO,(Student) user);
			userRepository.save((Student)user);
			return userMapper.mapStudentToResponse((Student)user);
		}).orElse(null);
	}

	public StudentResponseDTO updatedStudentStack(Stack stack, String studentId) {
//		Optional<User> optional = userRepository.findById(studentId);
//		if (optional.isPresent()) {
//			User user = optional.get();
//			Student student = (Student) user;
//			List<Subject> subjects= stack.getSubjects();
//			for (Subject subject : subjects) {
//				Rating rating = new Rating();
//				rating.setSubject(subject);
//			}
//			student.setStack(stack);
//			student = userRepository.save(student);
//			StudentResponseDTO studentResponseDTO = userMapper.mapStudentToResponse(student);
//			return studentResponseDTO;
//		}else {
//			return null;
//		}
		
		
		return userRepository.findById(studentId).map(user -> {
			Student student = (Student) user;
			stack.getSubjects().forEach(subject ->{
				Rating rating = new Rating();
				rating.setSubject(subject);
				ratingRepository.save(rating);
			});
			student.setStack(stack);
			userRepository.save(student);
			return userMapper.mapStudentToResponse(student);
		}).orElse(null);
	}

}
