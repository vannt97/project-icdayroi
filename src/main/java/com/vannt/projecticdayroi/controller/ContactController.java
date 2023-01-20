package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.dto.ProductDTO;
import com.vannt.projecticdayroi.entity.ContactEntity;
import com.vannt.projecticdayroi.model.ContactModel;
import com.vannt.projecticdayroi.payload.response.ResponseData;
import com.vannt.projecticdayroi.services.ContactServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class ContactController {

    @Autowired
    ContactServices contactServices;


    @PostMapping(value = "/contact", consumes = "application/json")
    public ResponseEntity<?> contactPost(@RequestBody(required = true) ContactModel contactModel){
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setEmail(contactModel.getEmail());
        contactEntity.setName(contactModel.getName());
        contactEntity.setMessage(contactModel.getMessage());
        ResponseData responseData = new ResponseData();
        try{
            contactServices.saveItem(contactEntity);
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setSucces(true);
            return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
        }catch (Exception e){
            responseData.setStatus(HttpStatus.FAILED_DEPENDENCY.value());
            responseData.setSucces(false);
            return new ResponseEntity<ResponseData>(responseData, HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
