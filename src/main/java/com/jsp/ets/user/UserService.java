package com.jsp.ets.user;

import com.jsp.ets.config.RandomGenerator;
import com.jsp.ets.exception.StudentNotFoundByIdException;
import com.jsp.ets.exception.TrainerNotFoundByIdException;
import com.jsp.ets.utility.MailSender;
import com.jsp.ets.utility.MessageModel;
import jakarta.mail.MessagingException;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.jsp.ets.btach.BatchMapper;
import com.jsp.ets.btach.BatchRepository;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingMapper;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.stack.Stack;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;

	private RatingRepository ratingRepository;

	private BatchRepository batchRepository;

	private UserMapper userMapper;

	private RatingMapper ratingMapper;

	private BatchMapper batchMapper;

	private MailSender mailSender;

	private Random random;


	@CachePut(cacheNames = "Non-verified-user" , key = "#registrationRequestDto.email")
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
			Integer otp = random.nextInt(100000,999999);
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


	private  void sendVerificationOtpToUser(User user,int otp) throws MessagingException {
		String text="<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
				"    <title>Document</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <h1>Hi this is edu_app please verify your email</h1>\n" +
				"<h1>Please use this otp given beloe for further verification</h1>"+
				"<h4>" + otp + "</h4>"+
				"</body>\n" +
				"</html>";
		MessageModel messageModel = new MessageModel();
		messageModel.setTo(user.getEmail());
		messageModel.setSubject("Verify your email for to confim registration");
		messageModel.setSendDate(new Date());
		messageModel.setText(text);
		mailSender.sendMail(messageModel);
	}


}
