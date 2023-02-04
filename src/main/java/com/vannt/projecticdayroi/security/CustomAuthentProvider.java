package com.vannt.projecticdayroi.security;

import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.services.UserServices;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;

@Component
public class CustomAuthentProvider implements AuthenticationProvider {


    @Autowired
    UserServices userServices;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity userEntity = userServices.checkLogin(email);
        if(userEntity != null){
            if(passwordEncoder.matches(password,userEntity.getPassword())){
                return new UsernamePasswordAuthenticationToken(email,password,new ArrayList<>());
            }else {
                return null;
            }
        }else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
