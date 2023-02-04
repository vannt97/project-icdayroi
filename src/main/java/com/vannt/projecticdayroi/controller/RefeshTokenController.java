package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.jwt.JwtTokenHelper;
import com.vannt.projecticdayroi.jwt.TypeToken;
import com.vannt.projecticdayroi.model.SubjectDataModel;
import com.vannt.projecticdayroi.payload.response.DataTokenResponse;
import com.vannt.projecticdayroi.payload.response.ResponseData;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class RefeshTokenController {

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Autowired
    ConvertObject convertObject;

    @PostMapping(value = "/refesh-token",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refeshToken(@RequestBody Map<String,String> requestData){
        ResponseData responseData = new ResponseData();
        if(jwtTokenHelper.validateToken(requestData.get("token"))){
            String tokenDecode = jwtTokenHelper.decodeToken(requestData.get("token"));
            SubjectDataModel subject = (SubjectDataModel) convertObject.convertJsonToObject(tokenDecode,SubjectDataModel.class);
            if(StringUtils.hasText(subject.getTypeToken().toString()) && subject.getTypeToken().equals(TypeToken.RESFESH_TOKEN)){
                long expiredDateToken = 8*60*60*1000;
                long expiredDateRefeshToken = 80*60*60*1000;
                SubjectDataModel subjectDataToken = new SubjectDataModel(subject.getEmail(), TypeToken.TOKEN);
                SubjectDataModel subjectDataRefeshToken = new SubjectDataModel(subject.getEmail(), TypeToken.RESFESH_TOKEN);
                String newToken = jwtTokenHelper.generateToken(convertObject.convertJson(subjectDataToken),expiredDateToken);
                String refeshToken = jwtTokenHelper.generateToken(convertObject.convertJson(subjectDataRefeshToken),expiredDateRefeshToken);
                DataTokenResponse dataTokenResponse = new DataTokenResponse(newToken,refeshToken);
                responseData.setData(dataTokenResponse);
                responseData.setSucces(true);
                responseData.setStatus(HttpStatus.OK.value());
            }
            return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
        }else {
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setSucces(false);
            responseData.setData("JWT token failed");
            return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
        }

    }
}
