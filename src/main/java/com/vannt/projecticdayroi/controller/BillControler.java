package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.dto.BillDTO;
import com.vannt.projecticdayroi.dto.UserDTO;
import com.vannt.projecticdayroi.entity.BillEntity;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.jwt.JwtTokenHelper;
import com.vannt.projecticdayroi.model.SubjectDataModel;
import com.vannt.projecticdayroi.payload.request.RequestBill;
import com.vannt.projecticdayroi.payload.response.ResponseData;
import com.vannt.projecticdayroi.services.BillServices;
import com.vannt.projecticdayroi.services.UserServices;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api")
public class BillControler {
    @Autowired
    BillServices billServices;

    @Autowired
    UserServices userServices;

    @Autowired
    ConvertObject convertObject;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @GetMapping(value = "/bills")
    public ResponseEntity<?> findALlBill(){
        try{
            return new ResponseEntity<List<BillEntity>>(billServices.getAllBill(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/clear-cache-all-bill")
    public String clearCacheAllBill(){
        billServices.clearCacheAllBill();
        return "da clear";
    }

    private String getTokenFromHeader(HttpServletRequest request){
        String strToken = request.getHeader("Authorization");
        if(StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")){
            return strToken.substring(7);
        }else {
            return null;
        }
    }

    @GetMapping(value = "/history-transaction")
    public ResponseEntity<?> findAllBillPagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "sortBy", required = false) String sort,
                                                   @RequestParam(value = "size", defaultValue = "5") int size,
                                                   HttpServletRequest request){
        String token = getTokenFromHeader(request);
        String tokenDecode = jwtTokenHelper.decodeToken(token);
        SubjectDataModel subjectToken = (SubjectDataModel) convertObject.convertJsonToObject(tokenDecode,SubjectDataModel.class);
        Page<BillEntity> list = billServices.getAllBillByUser(subjectToken.getEmail(),page,size);

        Map<String,Object> respone = new HashMap<>();

        respone.put("bills", list.getContent());
        respone.put("currentPage", list.getNumber());
        respone.put("totalItems",list.getTotalElements());
        respone.put("totalPages",list.getTotalPages());

        ResponseData responseData = new ResponseData();
        responseData.setSucces(true);
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setData(respone);
        return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
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

    @GetMapping(value = "/bill/{uuid}")
    public ResponseEntity<?> billDetail(@PathVariable UUID uuid){
        ResponseData responseData = new ResponseData();
        try{
            responseData.setData(billServices.get1Bill(uuid));
            responseData.setSucces(true);
            responseData.setStatus(HttpStatus.OK.value());
        }catch (Exception e){
            responseData.setData("");
            responseData.setSucces(false);
            responseData.setStatus(HttpStatus.FORBIDDEN.value());
        }
        return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
    }
}
