package com.jsp.ets.user;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.jsp.ets.btach.BatchMapper;
import com.jsp.ets.btach.BatchRepository;
import com.jsp.ets.btach.BatchRequestDTO;
import com.jsp.ets.btach.BatchResponseDTO;
import com.jsp.ets.btach.BatchStatus;
import com.jsp.ets.exception.ObjectNotFoundByIdException;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingMapper;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.rating.RatingRequestDTO;
import com.jsp.ets.rating.RatingResponseDTO;
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

		}).orElseThrow(() -> new ObjectNotFoundByIdException("Trainer not found by id!!"));

	}

	public StudentResponseDTO updateStudent(StudentRequestDTO studentRequestDTO, String userId) {
		return userRepository.findById(userId).map(user -> {
			userMapper.mapStudentToEntity(studentRequestDTO, (Student) user);
			userRepository.save((Student) user);
			return userMapper.mapStudentToResponse((Student) user);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("student not found by id!!"));
	}

	public StudentResponseDTO updatedStudentStack(Stack stack, String studentId) {
		return userRepository.findById(studentId).map(user -> {
			Student student = (Student) user;
			stack.getSubjects().forEach(subject -> {
				Rating rating = new Rating();
				rating.setSubject(subject);
				ratingRepository.save(rating);
			});
			student.setStack(stack);
			userRepository.save(student);
			return userMapper.mapStudentToResponse(student);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("student not found by id!!"));
	}

	public RatingResponseDTO updateStudentRating(RatingRequestDTO ratingRequestDTO, String ratingId) {
		return ratingRepository.findById(ratingId).map(rating -> {
			rating = ratingMapper.mapRatingToEntity(ratingRequestDTO, rating);
			rating = ratingRepository.save(rating);
			return ratingMapper.mapRatingToResponse(rating);

		}).orElseThrow(() -> new ObjectNotFoundByIdException("rating object not found by id!!"));

	}

	public BatchResponseDTO updateBatch(BatchRequestDTO batchRequestDTO, String batchId) {
		return batchRepository.findById(batchId).map(batch -> {
			batch = batchMapper.mapBatchToEntity(batchRequestDTO, batch);
			batch = batchRepository.save(batch);
			return batchMapper.mapBatchToResponse(batch);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("batch not found by id!!"));
	}

	public BatchResponseDTO updateBatchStatus(BatchStatus batchStatus, String batchId) {
		return batchRepository.findById(batchId).map(batch -> {
			batch.setBatchStatus(batchStatus);
			batchRepository.save(batch);
			return batchMapper.mapBatchToResponse(batch);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("batch not found by id!!"));
	}

	public List<RatingResponseDTO> getStudentRating(String studentId) {
		return userRepository.findById(studentId).map(user -> {
			Student student = (Student) user;
			return student.getRatings().stream().map(rating -> ratingMapper.mapRatingToResponse(rating)).toList();
		}).orElseThrow(() -> new ObjectNotFoundByIdException("batch not found by id!!"));

	}
}
