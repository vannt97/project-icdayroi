package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.dto.UserDTO;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.payload.request.RequestSignup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserServices {

    public int saveUser(UserDTO userDTO);

    public UserEntity saveUser(RequestSignup requestSignup);

    public UserEntity get1User(int id);

    public boolean checkLogin(String email,String password);

    public UserEntity checkLogin(String email);

    public UserEntity getUserByEmail(String email);

}
