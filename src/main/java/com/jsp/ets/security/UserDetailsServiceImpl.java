package com.jsp.ets.security;

import com.jsp.ets.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Finding user from repository...");
         UserDetails userDetails  = userRepository.findByEmail(username).map( user -> {
                     System.out.println("user email: " + user.getEmail());
                     return new UserDetailsImpl(user);
                 })
                 .orElseThrow(()-> {
                     System.out.println("user not found");
                     return new UsernameNotFoundException("user email not found!!");
                 });
        System.out.println(userDetails);
        return userDetails;
    }


}
