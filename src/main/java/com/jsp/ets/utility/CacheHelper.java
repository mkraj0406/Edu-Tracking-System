package com.jsp.ets.utility;

import com.jsp.ets.user.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


public class CacheHelper {

    @CachePut(cacheNames = "Non-verified-user",key = "#user.email")
    public User userCache(User user){
        return  user;
    }

    @Cacheable(cacheNames = "otps")
    public Integer otpCache(Integer otp){
        return otp;
    }
}
