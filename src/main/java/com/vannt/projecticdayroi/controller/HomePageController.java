package com.vannt.projecticdayroi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vannt.projecticdayroi.services.TypeProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @Autowired
    TypeProductServices typeProductServices;
    @RequestMapping("/")
    public String home(Model model) throws JsonProcessingException {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/home");
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(typeProductServices.getAllTypeProducts());
//        System.out.println(json);
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        return  "pages/home";
    }
}
