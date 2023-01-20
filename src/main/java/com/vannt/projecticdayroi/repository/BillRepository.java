package com.vannt.projecticdayroi.repository;

import com.vannt.projecticdayroi.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity,Integer>, CrudRepository<BillEntity,Integer> {
    public List<BillEntity> findAll();
}
