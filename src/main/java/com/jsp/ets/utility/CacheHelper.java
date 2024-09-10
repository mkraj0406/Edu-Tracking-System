package com.jsp.ets.utility;


import com.jsp.ets.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheHelper {

    @CachePut(cacheNames = "Non-verified-user",key = "#user.email")
    public User userCache(User user){
        return  user;
    }

    @CachePut(cacheNames = "otps",key = "#email")
    public Integer otpCache(Integer otp,String email){
        return otp;
    }

    @Cacheable(cacheNames = "Non-verified-user" ,key = "#email")
    public  User getRegistrationUser(String email){
        return new User();
    }

    @Cacheable(cacheNames = "otps", key = "#email")
    public Integer getOtpToVerify(String email){
        log.info("retrieving otp");
        return null;
    }





}
