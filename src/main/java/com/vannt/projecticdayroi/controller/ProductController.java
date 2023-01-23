package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.dto.ProductDTO;
import com.vannt.projecticdayroi.payload.response.ResponseData;
import com.vannt.projecticdayroi.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @GetMapping(value = "/products")
    public ResponseEntity<?> filterProducts(@RequestParam(name = "search", required = false) String name){
        List<ProductDTO> list =  productServices.getListProductByName(name);
        ResponseData responseData = new ResponseData();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSucces(true);
        responseData.setData(list);
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
    }
}
