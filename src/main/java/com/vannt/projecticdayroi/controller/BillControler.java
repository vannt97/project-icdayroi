package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.dto.BillDTO;
import com.vannt.projecticdayroi.dto.UserDTO;
import com.vannt.projecticdayroi.entity.BillEntity;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.payload.request.RequestBill;
import com.vannt.projecticdayroi.payload.response.ResponseData;
import com.vannt.projecticdayroi.services.BillServices;
import com.vannt.projecticdayroi.services.UserServices;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BillControler {
    @Autowired
    BillServices billServices;

    @Autowired
    UserServices userServices;

    @Autowired
    ConvertObject convertObject;

    @GetMapping(value = "/bills")
    public ResponseEntity<?> findALlBill(){
        try{
            return new ResponseEntity<List<BillEntity>>(billServices.getAllBill(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/checkout-bills")
    public ResponseEntity<?> checkOutBill(@RequestBody(required = true)RequestBill requestBill) throws Exception{
        ResponseData responseData = new ResponseData();
        try{
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(requestBill.getEmail());
            userDTO.setName(requestBill.getName());
            userDTO.setPhoneNumber(requestBill.getPhoneNumber());
            userDTO.setAddress(requestBill.getAddress());
            userDTO.setDistrict(requestBill.getDistrict());
            userDTO.setProvince(requestBill.getProvince());
            int id =  userServices.saveUser(userDTO);

            BillDTO billDTO = new BillDTO();
            billDTO.setDelivery(requestBill.getDelivery());
            billDTO.setMessageNote(requestBill.getMessageNote());
            billDTO.setPayment(requestBill.getPayment());
            billDTO.setBillProductModelSet(requestBill.getListProduct());
            BillEntity billEntity  = billServices.saveBill(billDTO,id);
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setSucces(true);
            responseData.setData("checkout/" + billEntity.getId());

        }catch (Exception e){
            responseData.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            responseData.setSucces(false);
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
    }
}
