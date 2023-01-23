package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.entity.BillEntity;
import com.vannt.projecticdayroi.entity.UserEntity;
import com.vannt.projecticdayroi.services.BillServices;
import com.vannt.projecticdayroi.services.TypeProductServices;
import com.vannt.projecticdayroi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class CheckoutController {
    @Autowired
    TypeProductServices typeProductServices;

    @Autowired
    BillServices billServices;

    @Autowired
    UserServices userServices;

    @RequestMapping("/cart")
    public String cart(Model model){
        model.addAttribute("searchName", "Giỏ hàng");
        return "pages/cart";
    }
    @RequestMapping("/checkout")
    public String checkout(Model model)  {
        return  "pages/checkout";
    }

    @RequestMapping("/checkout/{uuid}")
    public String checkOutSuccess(Model model, @PathVariable(required = false)UUID uuid){
        BillEntity billEntity = billServices.get1Bill(uuid);
        UserEntity userEntity = userServices.get1User(billEntity.getIdUser());

        model.addAttribute("bill",billEntity);
        model.addAttribute("infoUser",userEntity);
        String delivery = "";
        switch (billEntity.getDelivery()){
            case "giao_hang_tan_noi":{
                delivery = "Giao hàng tận nơi";
                break;
            }
            case "nhan_tai_cua_hang":{
                delivery = "Nhận tại cửa hàng";
                break;
            }
        }
        model.addAttribute("delivery",delivery);
        String payment = "";
        switch (billEntity.getPayment()){
            case "ship_cod":{
                payment = "Ship COD";
                break;
            }
            case "thanh_toan_truc_tiep":{
                payment = "Thanh toán trực tiếp";
                break;
            }
            case "thanh_toan_dien_tu":{
                payment = "Thanh toán điện tử";
                break;
            }
            case "thanh_toan_ngan_hang":{
                payment = "Thanh toán ngân hàng";
                break;
            }
        }
        model.addAttribute("payment",payment);
        return "pages/checkout-success";
    }
}
