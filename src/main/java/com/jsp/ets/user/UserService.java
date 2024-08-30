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

	// ------------------------------------------------------------------------------------------

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
			userMapper.mapTrainerToEntity(trainerRequestDTO, (Trainer) user);
			userRepository.save((Trainer) user);
			return userMapper.mapTainerToResponce((Trainer) user);

		}).orElseThrow(() -> new ObjectNotFoundByIdException("Trainer not found by id!!"));

	}

	// ----------------------------------------------------------------------------------------------
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
			userMapper.mapStudentToEntity(studentRequestDTO, (Student) user);
			userRepository.save((Student) user);
			return userMapper.mapStudentToResponse((Student) user);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("student not found by id!!"));
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

	// --------------------------------------------------------------------------------------------------------
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
		return	batchMapper.mapBatchToResponse(batch);
		}).orElseThrow(() -> new ObjectNotFoundByIdException("batch not found by id!!"));
	}

}
