package com.vannt.projecticdayroi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vannt.projecticdayroi.dto.UserDTO;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.jwt.JwtTokenHelper;
import com.vannt.projecticdayroi.jwt.TypeToken;
import com.vannt.projecticdayroi.model.SubjectDataModel;
import com.vannt.projecticdayroi.payload.request.RequestSignup;
import com.vannt.projecticdayroi.payload.request.SigninRequest;
import com.vannt.projecticdayroi.payload.response.DataTokenResponse;
import com.vannt.projecticdayroi.payload.response.ResponseData;
import com.vannt.projecticdayroi.services.UserServices;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping(value = "/api")
public class LoginApiController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenHelper tokenHelper;

    @Autowired
    ConvertObject convertObject;

    @Autowired
    UserServices userServices;

    private long expiredDateToken = 8*60*60*1000;

    private long expiredDateRefeshToken = 80*60*60*1000;


    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn1( @RequestBody SigninRequest signinRequest) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        SubjectDataModel subjectDataToken = new SubjectDataModel(signinRequest.getEmail(), TypeToken.TOKEN);
        SubjectDataModel subjectDataRefeshToken = new SubjectDataModel(signinRequest.getEmail(), TypeToken.RESFESH_TOKEN);

        String token = tokenHelper.generateToken(convertObject.convertJson(subjectDataToken),expiredDateToken);
        String refeshToken = tokenHelper.generateToken(convertObject.convertJson(subjectDataRefeshToken),expiredDateRefeshToken);
        DataTokenResponse dataTokenResponse = new DataTokenResponse(token,refeshToken);
        UserEntity userEntity = userServices.getUserByEmail(signinRequest.getEmail());
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());

        Map<Object,Object> map = new HashMap<>();
        map.put("dataToken", dataTokenResponse);
        map.put("user",userDTO);

        ResponseData responseData = new ResponseData();
        responseData.setData(map);
        responseData.setSucces(true);
        responseData.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
    }

    @GetMapping("/signin/test")
    public String test1(){
        return "Thanh cong";
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUpApi(@RequestBody RequestSignup requestSignup){
        ResponseData responseData = new ResponseData();
        if(userServices.checkLogin(requestSignup.getEmail()) == null){

            UserEntity userEntity = userServices.saveUser(requestSignup);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(requestSignup.getEmail(),requestSignup.getPassword());
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(auth);


            SubjectDataModel subjectDataToken = new SubjectDataModel(requestSignup.getEmail(), TypeToken.TOKEN);
            SubjectDataModel subjectDataRefeshToken = new SubjectDataModel(requestSignup.getEmail(), TypeToken.RESFESH_TOKEN);

            String token = tokenHelper.generateToken(convertObject.convertJson(subjectDataToken),expiredDateToken);
            String refeshToken = tokenHelper.generateToken(convertObject.convertJson(subjectDataRefeshToken),expiredDateRefeshToken);

            DataTokenResponse dataTokenResponse = new DataTokenResponse(token,refeshToken);
            UserDTO userDTO = new UserDTO();
            userDTO.setName(userEntity.getName());
            userDTO.setEmail(userEntity.getEmail());

            Map<Object,Object> map = new HashMap<>();
            map.put("dataToken", dataTokenResponse);
            map.put("user",userDTO);

            responseData.setStatus(HttpStatus.OK.value());
            responseData.setSucces(true);
            responseData.setData(map);
            return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
        }else {
            responseData.setSucces(false);
            responseData.setData("Email đã tồn tại!");
            responseData.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<ResponseData>(responseData, HttpStatus.UNAUTHORIZED);
        }
    }

}
