package com.vannt.projecticdayroi.controller;

import com.vannt.projecticdayroi.services.ProductServices;
import com.vannt.projecticdayroi.services.TypeProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {
    @Autowired
    TypeProductServices typeProductServices;
    @Autowired
    ProductServices productServices;
    @RequestMapping("/service-lazer")
    public String serviceLazer(Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("searchName", "Cắt khắc laser");
        return "pages/service-lazer";
    }

    @RequestMapping("/blog")
    public String blog(Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("searchName", "Tin tức");
        return "pages/blog";
    }

    @RequestMapping("/gioi-thieu")
    public String gioiThieu(Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());

        model.addAttribute("searchName","Giới thiệu");
        return "pages/gioi-thieu";
    }

    @RequestMapping("/contact")
    public String contact(Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());

        model.addAttribute("searchName","LIÊN HỆ");
        return "pages/lien-he";
    }

    @RequestMapping("/insurance")
    public String insurance(Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("searchName","Chính sách bảo hành");
        return "pages/insurance";
    }

        @RequestMapping("/infomation-security")
    public String infomationSecurity(Model model){
            model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
            model.addAttribute("searchName","Chính sách bảo mật thông tin");
        return "pages/infomation-security";
    }

        @RequestMapping("/policy")
    public String policy(Model model){
            model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
            model.addAttribute("searchName","Chính sách bảo mật thông tin");
        return "pages/policy";
    }

    @RequestMapping("/guide")
    public String guide(Model model){

        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("searchName","Chính sách bảo mật thông tin");
        return "pages/guide";
    }




//    @RequestMapping("category-product")
//    public  ModelAndView categoryProduct(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/category-product");
//        return modelAndView;
//    }
//
//    @RequestMapping("detail-product")
//    public  ModelAndView detailProduct(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/detail-product");
//        return modelAndView;
//    }
//
//    @RequestMapping("search-product")
//    public ModelAndView searchProduct(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/search-product");
//        return modelAndView;
//    }
//
//    @RequestMapping("service-lazer")
//    public ModelAndView serviceLazer(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/service-lazer");
//        return modelAndView;
//    }
//
//    @RequestMapping("blog")
//    public ModelAndView blog(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/blog");
//        return modelAndView;
//    }
//
//    @RequestMapping("blog-detail")
//    public ModelAndView blogDetail(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/blog-detail");
//        return modelAndView;
//    }
//
//    @RequestMapping("gioi-thieu")
//    public ModelAndView gioiThieu(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/gioi-thieu");
//        return modelAndView;
//    }
//
//    @RequestMapping("lien-he")
//    public ModelAndView lienHe(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/lien-he");
//        return modelAndView;
//    }
//
//    @RequestMapping("cart")
//    public ModelAndView cart(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/page-cart");
//        return modelAndView;
//    }
//
//    @RequestMapping("sign-in")
//    public ModelAndView signIn(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/sign-in");
//        return modelAndView;
//    }
//
//    @RequestMapping("sign-up")
//    public ModelAndView signUp(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/sign-up");
//        return modelAndView;
//    }
//
//    @RequestMapping("insurance")
//    public ModelAndView insurance(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/insurance");
//        return modelAndView;
//    }
//
//    @RequestMapping("infomation-security")
//    public ModelAndView infomationSecurity(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/infomation-security");
//        return modelAndView;
//    }
//
//    @RequestMapping("policy")
//    public ModelAndView policy(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/policy");
//        return modelAndView;
//    }
//
//    @RequestMapping("guide")
//    public ModelAndView guide(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("pages/guide");
//        return modelAndView;
//    }
}
