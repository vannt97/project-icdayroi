package com.vannt.projecticdayroi.repository;

import com.vannt.projecticdayroi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer>, JpaRepository<UserEntity,Integer> {
    public UserEntity findByEmail(String email);

    public UserEntity findById(int id);

    public List<UserEntity> findByEmailAndPassword(String email, String password);

}
