package com.vannt.projecticdayroi.repository;

import com.vannt.projecticdayroi.entity.ContactEntity;
import com.vannt.projecticdayroi.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Integer> {

}
