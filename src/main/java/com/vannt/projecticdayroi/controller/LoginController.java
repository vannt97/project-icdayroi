package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.services.TypeProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    TypeProductServices typeProductServices;

    @RequestMapping("/signin")
    public String signin(Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("searchName", "Đăng nhập tài khoản");
        return "pages/sign-in";
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("searchName", "Đăng ký tài khoản");
        return "pages/sign-up";
    }
}
