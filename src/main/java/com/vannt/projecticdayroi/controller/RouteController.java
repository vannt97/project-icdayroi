package com.vannt.projecticdayroi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {

    @RequestMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/home");
        return  modelAndView;
    }

    @RequestMapping("category-product")
    public  ModelAndView categoryProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/category-product");
        return modelAndView;
    }

    @RequestMapping("detail-product")
    public  ModelAndView detailProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/detail-product");
        return modelAndView;
    }

    @RequestMapping("search-product")
    public ModelAndView searchProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/search-product");
        return modelAndView;
    }
}
