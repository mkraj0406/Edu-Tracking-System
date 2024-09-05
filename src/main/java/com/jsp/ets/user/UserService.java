package com.jsp.ets.user;

import com.jsp.ets.exception.StudentNotFoundByIdException;
import com.jsp.ets.exception.TrainerNotFoundByIdException;
import org.springframework.stereotype.Service;

import com.jsp.ets.btach.BatchMapper;
import com.jsp.ets.btach.BatchRepository;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingMapper;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.stack.Stack;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;

	private RatingRepository ratingRepository;

	private BatchRepository batchRepository;

	private UserMapper userMapper;

	private RatingMapper ratingMapper;

	private BatchMapper batchMapper;

	public UserResponseDto registerUser(RegistrationRequestDTO registrationRequestDto, UserRole role) {
		User user = null;
		switch (role) {
		case ADMIN -> user = new Admin();
		case HR -> user = new HR();
		case STUDENT -> user = new Student();
		case TRAINER -> user = new Trainer();
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		}

		if (user != null) {
			user = userMapper.mapUserToEntity(registrationRequestDto, user);
			user.setRole(role);
			user = userRepository.save(user);
		}

		return userMapper.mapUserToResponce(user);
	}

	public TrainerResponseDTO updateTrainerForSubject(TrainerRequestDTO trainerRequestDTO, String userId) {
		return userRepository.findById(userId).map(user -> {
			Trainer trainer = (Trainer) user;
			trainer = userMapper.mapTrainerToEntity(trainerRequestDTO, trainer);
			trainer = userRepository.save(trainer);
			return userMapper.mapTainerToResponce(trainer);

		}).orElseThrow(() -> new TrainerNotFoundByIdException("Trainer not found by id!!"));

	}

	public StudentResponseDTO updateStudent(StudentRequestDTO studentRequestDTO, String userId) {
		return userRepository.findById(userId).map(user -> {
			userMapper.mapStudentToEntity(studentRequestDTO, (Student) user);
			userRepository.save((Student) user);
			return userMapper.mapStudentToResponse((Student) user);
		}).orElseThrow(() -> new StudentNotFoundByIdException("student not found by id!!"));
	}

	public StudentResponseDTO updatedStudentStack(Stack stack, String studentId) {
		return userRepository.findById(studentId).map(user -> {
			Student student = (Student) user;
			stack.getSubjects().forEach(subject -> {
				Rating rating = new Rating();
				rating.setSubject(subject);
				rating.setStudent(student);
				ratingRepository.save(rating);
			});
			student.setStack(stack);
			userRepository.save(student);
			return userMapper.mapStudentToResponse(student);
		}).orElseThrow(() -> new StudentNotFoundByIdException("student not found by id!!"));
	}


	private  void sendVerificationOtpToUser(String email){

	}


}
