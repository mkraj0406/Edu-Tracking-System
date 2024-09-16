package com.jsp.ets.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${myapp.jwt.secret}")
    private  String jwsSecret;

    @Value("${myapp.jwt.access_expiry}")
    private  int access_expiry;

}
