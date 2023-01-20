package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.services.TypeProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {
    @Autowired
    TypeProductServices typeProductServices;
    @RequestMapping("/cart")
    public String cart(Model model){
        model.addAttribute("searchName", "Giỏ hàng");
        return "pages/cart";
    }
    @RequestMapping("/checkout")
    public String checkout(Model model)  {
//                convertObject.convertListObjectToJsonString(productServices.getListProduct());
        return  "pages/checkout";
    }
}
