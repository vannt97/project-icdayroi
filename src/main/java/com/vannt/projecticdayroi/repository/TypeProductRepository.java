package com.vannt.projecticdayroi.repository;

import com.vannt.projecticdayroi.entity.TypeProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeProductRepository extends JpaRepository<TypeProductEntity,Integer> {

    public List<TypeProductEntity> findAll();
}
