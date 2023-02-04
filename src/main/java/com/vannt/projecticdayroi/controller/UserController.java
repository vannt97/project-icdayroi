package com.vannt.projecticdayroi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String infoUser(HttpServletRequest httpServletRequest){
        return "pages/info-user";
    }

    @GetMapping(value = "/user/bills/**")
    public String billInfo(){
        return "pages/bill-detail";
    }
}
