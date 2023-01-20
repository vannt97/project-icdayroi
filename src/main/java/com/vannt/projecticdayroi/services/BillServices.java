package com.vannt.projecticdayroi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vannt.projecticdayroi.dto.BillDTO;
import com.vannt.projecticdayroi.entity.BillEntity;
import com.vannt.projecticdayroi.entity.ContactEntity;
import com.vannt.projecticdayroi.entity.UserEntity;

import java.util.List;

public interface BillServices {
    public List<BillEntity> getAllBill();

    public void saveBill(BillDTO billDTO, int  id) throws Exception;
}
