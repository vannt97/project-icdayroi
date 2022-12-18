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

    @RequestMapping("service-lazer")
    public ModelAndView serviceLazer(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/service-lazer");
        return modelAndView;
    }

    @RequestMapping("blog")
    public ModelAndView blog(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/blog");
        return modelAndView;
    }

    @RequestMapping("blog-detail")
    public ModelAndView blogDetail(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/blog-detail");
        return modelAndView;
    }

    @RequestMapping("gioi-thieu")
    public ModelAndView gioiThieu(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/gioi-thieu");
        return modelAndView;
    }

    @RequestMapping("lien-he")
    public ModelAndView lienHe(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/lien-he");
        return modelAndView;
    }

    @RequestMapping("cart")
    public ModelAndView cart(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/page-cart");
        return modelAndView;
    }

    @RequestMapping("sign-in")
    public ModelAndView signIn(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/sign-in");
        return modelAndView;
    }

    @RequestMapping("sign-up")
    public ModelAndView signUp(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/sign-up");
        return modelAndView;
    }

    @RequestMapping("insurance")
    public ModelAndView insurance(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/insurance");
        return modelAndView;
    }
}
