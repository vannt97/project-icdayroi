package com.vannt.projecticdayroi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vannt.projecticdayroi.dto.BillDTO;
import com.vannt.projecticdayroi.entity.BillEntity;
import com.vannt.projecticdayroi.entity.BillProductEntity;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.model.BillProductModel;
import com.vannt.projecticdayroi.repository.BillRepository;
import com.vannt.projecticdayroi.repository.ProductRepository;
import com.vannt.projecticdayroi.repository.UserRepository;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BillServicesImp implements BillServices{

    @Autowired
    BillRepository billRepository;

    @Autowired
    ConvertObject convertObject;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Cacheable("all_bill")
    public List<BillEntity> getAllBill() {
        List<BillEntity> list = billRepository.findAll();
        return list;
    }

    @Override
    @Transactional
    public BillEntity saveBill(BillDTO billDTO, int userId) throws Exception {
        try{
            BillEntity billEntity = new BillEntity();
            billEntity.setIdUser(userId);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            billEntity.setCreateAt(timestamp.toString());
            billEntity.setStatus("confirm");
            billEntity.setMessageNote(billDTO.getMessageNote());
            billEntity.setDelivery(billDTO.getDelivery());
            billEntity.setPayment(billDTO.getPayment());
            if(billEntity.getDelivery().equals("giao_hang_tan_noi")){
                billEntity.setDeliveryPrice(40000);
            }else {
                billEntity.setDeliveryPrice(0);
            }

            Set<BillProductEntity> list = new HashSet<>();
        for (BillProductModel billProductModel:billDTO.getBillProductModelSet()) {
            BillProductEntity billProductEntity = new BillProductEntity();
            billProductEntity.setAmount(billProductModel.getAmount());
            billProductEntity.setProductEntity(productRepository.findById(billProductModel.getId()));
            billProductEntity.setBillEntity(billEntity);
            list.add(billProductEntity);
        }

        billEntity.setBillProductEntities(list);
            return billRepository.save(billEntity);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BillEntity get1Bill(UUID uuid) {
        return billRepository.findById(uuid);
    }

    @Override
    public Page<BillEntity> getAllBill(int page, int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<BillEntity> billEntityPage = billRepository.findAll(paging);
        return billEntityPage;
    }

    @Override
    public Slice<BillEntity> getAllBillSlice(int page, int size) {
        Pageable paging = PageRequest.of(page,size);
        Slice<BillEntity> billEntityPage = billRepository.findAll(paging);
        return billEntityPage;
    }

    @Override
    public Page<BillEntity> getAllBillByUser(String email, int page, int size) {
        UserEntity userEntity = userRepository.findByEmail(email);
        Pageable paging = PageRequest.of(page,size, Sort.by("createAt").descending());
        Page<BillEntity> billEntityPage = billRepository.findByIdUser(paging,userEntity.getId());
        return billEntityPage;
    }

    @Override
    @CacheEvict(value = "all_bill", allEntries = true)
    public void clearCacheAllBill() {

    }
}
