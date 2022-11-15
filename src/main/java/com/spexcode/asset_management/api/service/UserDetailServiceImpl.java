package com.spexcode.asset_management.api.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        if(username.equals("api_user")){
            return User.builder()
            .username("api_user")
		    .password(passwordEncoder().encode("123456"))
		    .roles("USER")
		    .build();
        }else if(username.equals("api_admin")){
            return User.builder()
            .username("api_admin")
            .password(passwordEncoder().encode("123456"))
            .roles("USER", "ADMIN")
            .build();
        }

        throw new UsernameNotFoundException("USER NOT FOUND");
    }
    
}
