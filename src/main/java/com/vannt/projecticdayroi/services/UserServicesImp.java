package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.dto.UserDTO;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.payload.request.RequestSignup;
import com.vannt.projecticdayroi.repository.UserRepository;
import com.zaxxer.hikari.util.SuspendResumeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImp implements UserServices{
    @Autowired
    UserRepository userRepository;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public int saveUser(UserDTO userDTO) {
        try{
            UserEntity userEntityFind = userRepository.findByEmail(userDTO.getEmail());
            return userEntityFind.getId();
        }catch (Exception e){
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setName(userDTO.getName());
            userEntity.setCity(userDTO.getCity());
            userEntity.setPhone(userDTO.getPhoneNumber());
            userEntity.setAddress(userDTO.getAddress());
            userEntity.setActive(false);
            userEntity.setPassword(null);
            userEntity.setProvince(userDTO.getProvince());
            return userRepository.save(userEntity).getId();
        }
    }

    @Override
    public UserEntity saveUser(RequestSignup requestSignup) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(requestSignup.getEmail());
        String newPassword = encoder.encode(requestSignup.getPassword());
        userEntity.setPassword(newPassword);
        userEntity.setName(requestSignup.getName());
        userEntity.setActive(true);
        return userRepository.save(userEntity);
    }


    @Override
    public UserEntity get1User(int id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean checkLogin(String email, String password) {
        List<UserEntity> list = userRepository.findByEmailAndPassword(email,password);

        return list.size() > 0;
    }

    @Override
    public UserEntity checkLogin(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userEntity;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
