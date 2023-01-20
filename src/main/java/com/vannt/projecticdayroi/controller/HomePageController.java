package com.vannt.projecticdayroi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vannt.projecticdayroi.services.ProductServices;
import com.vannt.projecticdayroi.services.TypeProductServices;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @Autowired
    TypeProductServices typeProductServices;

    @Autowired
    ConvertObject convertObject;

    @Autowired
    ProductServices productServices;

    @RequestMapping("/")
    public String home(Model model) throws JsonProcessingException {
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("newProducts",productServices.getListProduct());
        model.addAttribute("bestSellerProducts",productServices.getListProduct());
        model.addAttribute("featureProducts",productServices.getListFeaturedProduct());
//                convertObject.convertListObjectToJsonString(productServices.getListProduct());
        return  "pages/home";
    }
}
