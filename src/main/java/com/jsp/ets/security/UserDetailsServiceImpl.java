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
         UserDetails userDetails  = userRepository.findByEmail(username).map(UserDetailsImpl::new)
                 .orElseThrow(()->
                      new UsernameNotFoundException("user email not found!!")
                 );
        return userDetails;
    }


}
