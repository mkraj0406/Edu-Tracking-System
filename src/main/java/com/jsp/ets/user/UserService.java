package com.jsp.ets.user;


import com.jsp.ets.exception.RegistrationSessionExpired;
import com.jsp.ets.exception.StudentNotFoundByIdException;
import com.jsp.ets.exception.TrainerNotFoundByIdException;
import com.jsp.ets.security.JwtService;
import com.jsp.ets.utility.CacheHelper;
import com.jsp.ets.utility.MailSenderService;
import com.jsp.ets.utility.MessageModel;
import com.jsp.ets.utility.ResponseStructure;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.ets.btach.BatchMapper;
import com.jsp.ets.btach.BatchRepository;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingMapper;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.security.RegistrationRequestDTO;
import com.jsp.ets.stack.Stack;



import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RatingRepository ratingRepository;

    private final UserMapper userMapper;

    private final MailSenderService mailSenderService;

    private final Random random;

    private final CacheHelper cacheHelper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Value("${myapp.jwt.access_expiry}")
    private long accessExpiry;

    @Value("${myapp.jwt.refreshExpiry}")
    private long refreshExpiry;

    public UserService(UserRepository userRepository, RatingRepository ratingRepository, UserMapper userMapper, MailSenderService mailSenderService, Random random, CacheHelper cacheHelper, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.userMapper = userMapper;
        this.mailSenderService = mailSenderService;
        this.random = random;
        this.cacheHelper = cacheHelper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserResponseDto registerUser(RegistrationRequestDTO registrationRequestDto, UserRole role) throws MessagingException {
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
            int otp = random.nextInt(100000, 999999);
            cacheHelper.userCache(user);
            cacheHelper.otpCache(otp, user.getEmail());
            try {
                sendVerificationOtpToUser(user, otp);
            } catch (MessagingException e) {
                log.info("Messaging exception occurred");
            }
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


    private void sendVerificationOtpToUser(User user, int otp) throws MessagingException {
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Hi this is edu_app please verify your email</h1>\n" +
                "<h1>Please use this otp given below for further verification</h1>" +
                "<h4>" + otp + "</h4>" +
                "</body>\n" +
                "</html>";
        MessageModel messageModel = new MessageModel();
        messageModel.setTo(user.getEmail());
        messageModel.setSubject("Verify your email for to confirm registration");
        messageModel.setSendDate(new Date());
        messageModel.setText(text);
        mailSenderService.sendMail(messageModel);
    }


    public UserResponseDto userOtpVerification(OtpRequestDTO otpRequestDTO) {
        Integer otp = cacheHelper.getOtpToVerify(otpRequestDTO.getEmail());
        User user = cacheHelper.getRegistrationUser(otpRequestDTO.getEmail());

        if ((otp != null && otp.equals(otpRequestDTO.getOtp())) && (user != null && user.getEmail().equals(otpRequestDTO.getEmail()))) {
            user = userRepository.save(user);
            UserResponseDto userResponseDto = userMapper.mapUserToResponce(user);
            return userResponseDto;
        } else {
            throw new RegistrationSessionExpired("otp is incorrect or otp may expired, please try again");
        }
    }


    public ResponseEntity<ResponseStructure<UserResponseDto>> loginUser(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication.isAuthenticated()) {
            return userRepository.findByEmail(loginRequestDTO.getEmail()).map(user -> {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders = grantAccessAccessToken(user,httpHeaders);
                httpHeaders = grantAccessResfreshToken(user,httpHeaders);
                return ResponseEntity.ok().headers(httpHeaders)
                        .body(ResponseStructure.create(HttpStatus.OK,"cookies is created", userMapper.mapUserToResponce(user)));
                            }
                    )
                    .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

        }else {
            throw new UsernameNotFoundException("user not found!!");
        }
    }

    private HttpHeaders grantAccessAccessToken(User user,HttpHeaders httpHeaders) {
        String access_token = jwtService.createAccessToken(user.getUserId(), user.getEmail(), user.getRole().name());
        httpHeaders.add(HttpHeaders.SET_COOKIE, createCookie("at", access_token, accessExpiry*60));
        return httpHeaders;
    }
    private  HttpHeaders grantAccessResfreshToken(User user,HttpHeaders httpHeaders){
        String refresh_token = jwtService.createRefreshToken(user.getUserId(), user.getEmail(), user.getRole().name());
        httpHeaders.add(HttpHeaders.SET_COOKIE, createCookie("rt", refresh_token, refreshExpiry*60));
        return httpHeaders;
    }

    private String createCookie(String name, String value, long maxAge) {
        return ResponseCookie.from(name, value)
                .domain("localhost")
                .path("/")
                .secure(false)
                .httpOnly(true)
                .sameSite("Lax")
                .maxAge(maxAge).build().toString();
    }

    public  ResponseEntity<ResponseStructure<UserResponseDto>> refreshLogin(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
         return userRepository.findByEmail(email).map(user -> {
              HttpHeaders httpHeaders = new HttpHeaders();
              httpHeaders = grantAccessAccessToken(user,httpHeaders);
              return ResponseEntity.ok().headers(httpHeaders)
                      .body(ResponseStructure
                              .create(HttpStatus.OK,"cookies is created", userMapper.mapUserToResponce(user)));
          }).orElseThrow(() -> new UsernameNotFoundException("failed to find  email"));

    }
}
