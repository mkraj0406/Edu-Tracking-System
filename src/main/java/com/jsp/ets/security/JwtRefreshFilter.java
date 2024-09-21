package com.jsp.ets.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtRefreshFilter extends OncePerRequestFilter {

    private  JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       Cookie[] cookies = request.getCookies();
        String token=null;
        if(cookies != null){
            for(Cookie cookie:cookies){
                if("rt".equals(cookie.getName())){
                    token = cookie.getValue();
                    break;
                }
            }
        }

        jwtService.authenticationToken(token,request);
        filterChain.doFilter(request, response);
    }
}
