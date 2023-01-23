package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.dto.UserDTO;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImp implements UserServices{
    @Autowired
    UserRepository userRepository;

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
    public UserEntity get1User(int id) {
        return userRepository.findById(id);
    }
}
