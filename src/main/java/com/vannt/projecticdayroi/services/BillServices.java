package com.vannt.projecticdayroi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vannt.projecticdayroi.dto.BillDTO;
import com.vannt.projecticdayroi.entity.BillEntity;
import com.vannt.projecticdayroi.entity.ContactEntity;
import com.vannt.projecticdayroi.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface BillServices {
    public List<BillEntity> getAllBill();

    public BillEntity saveBill(BillDTO billDTO, int  id) throws Exception;

    public BillEntity get1Bill(UUID uuid);
}
