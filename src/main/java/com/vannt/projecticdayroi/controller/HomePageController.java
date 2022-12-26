package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.services.TypeProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("")
public class HomePageController {
//    @Autowired
//    TypeProductServices typeProductServices;

    public String home(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/home");
//        System.out.println(typeProductServices.getAllTypeProducts());
        return  "demo";
    }
}
