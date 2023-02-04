package com.vannt.projecticdayroi.repository;

import com.vannt.projecticdayroi.entity.BillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<BillEntity,Integer>, CrudRepository<BillEntity,Integer> {
    public List<BillEntity> findAll();

    public BillEntity findById(UUID uuid);

    public Page<BillEntity> findAll(Pageable pageable);

    public Page<BillEntity> findByIdUser(Pageable pageable,int idUser);

}
