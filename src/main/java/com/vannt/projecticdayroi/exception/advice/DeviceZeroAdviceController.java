package com.vannt.projecticdayroi.exception.advice;

import com.vannt.projecticdayroi.exception.DeviceZeroException;
import com.vannt.projecticdayroi.payload.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DeviceZeroAdviceController {

    @ExceptionHandler(DeviceZeroException.class)
    public ResponseEntity<?> handleException(Exception e){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseData.setData("Error : " + e.getMessage());
        responseData.setSucces(false);
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }
}
