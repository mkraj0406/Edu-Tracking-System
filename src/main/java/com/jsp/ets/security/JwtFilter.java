package com.jsp.ets.security;

import com.jsp.ets.user.Privilege;
import com.jsp.ets.user.UserRole;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private  final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String token = request.getHeader("authorization");
       if(token!=null){
           token = token.substring(7);
           if(!token.equals("")){
               Claims claims =jwtService.parseJwt(token);
              String email= claims.get("email",String.class);
              String role= claims.get("role",String.class);
               if(email!=null && role!= null){
                  UserRole userRole = UserRole.valueOf(role);

                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,null,userRole
                           .getUserRole()
                           .stream()
                           .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                           .toList()
                   );
                  usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               }
           }
       }
        filterChain.doFilter(request,response);
    }
}
