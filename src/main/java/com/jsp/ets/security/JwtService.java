package com.jsp.ets.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${myapp.jwt.secret}")
    private  String jwsSecret;

    @Value("${myapp.jwt.access_expiry}")
    private  long accessExpiry;

    @Value("${myapp.jwt.refresh_expiry}")
    private long refreshExpiry;



    public  String createAccessToken(String userId,String email,String role){
       return createJwtToken(userId,email,role,accessExpiry);
    }

    public String createRefreshToken(String userId,String email,String role){
        return createJwtToken(userId,email,role,refreshExpiry);
    }

    private  String createJwtToken(String userId,String email,String role,long expiry){
       return Jwts.builder()
                .setClaims(Map.of("userId",userId,"email",email,"role",role))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiry*60*1000))
                .signWith(getSignupKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignupKey(){
      return  Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwsSecret));
    }

    public Claims parseJwt(String token){
     JwtParser jwtParser =  Jwts.parserBuilder()
                .setSigningKey(getSignupKey()).build();
     return jwtParser.parseClaimsJws(token).getBody();
    }
}
