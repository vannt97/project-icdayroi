package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.services.TypeProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller()
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

//    @Autowired
//    TypeProductServices typeProductServices;
//
//    @RequestMapping("/error")
//    public String error404(Model model, HttpServletRequest request){
//        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
//        model.addAttribute("searchName","404");
//        return "pages/error404";
//    }
}
