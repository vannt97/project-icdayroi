package com.vannt.projecticdayroi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vannt.projecticdayroi.dto.ProductDTO;
import com.vannt.projecticdayroi.services.ProductServices;
import com.vannt.projecticdayroi.services.TypeProductServices;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailProductController {
    @Autowired
    ProductServices productServices;

    @Autowired
    TypeProductServices typeProductServices;

    @Autowired
    ConvertObject convertObject;


    @RequestMapping("/{slug}")
    public String detailProduct(Model model, @PathVariable(required = false) String slug) throws JsonProcessingException {
        if(typeProductServices.getTypeProduct(slug) == null){
            ProductDTO productDTO = productServices.getProductBySlug(slug);
            if(productDTO == null) return "pages/error404";
            List<ProductDTO> productDTOList = productServices.get10ProductsByTypeProduct(productDTO.getTypeProductEntity().getId());
            int index = getIndexFromArray(productDTOList,productDTO);
            productDTOList = removeItemFromArray(productDTOList,index);

            model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
            model.addAttribute("searchName", productDTO.getName());
            model.addAttribute("detailProduct",productDTO);
            model.addAttribute("typeProductLink", productDTO.getTypeProductEntity() == null ? null : productDTO.getTypeProductEntity() );
            model.addAttribute("relatedProducts",productDTOList);
            return  "pages/detail-product";
        }else {
            List<ProductDTO> list = productServices.getProductsByTypeProduct(slug);
            model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
            model.addAttribute("searchName", list.get(0).getTypeProductEntity().getName());
            model.addAttribute("categoryProducts",list);
            return  "pages/category-product";
        }
    }

    @RequestMapping("/search-product")
    public String searchProduct(@RequestParam(name = "name", defaultValue = "") String name, Model model){
        model.addAttribute("typeProducts",typeProductServices.getAllTypeProducts());
        model.addAttribute("searchProducts",productServices.getListProductByName(name));
        model.addAttribute("searchName", name);
        return "pages/search-product";
    }

    public int getIndexFromArray(List<ProductDTO> dtoArrayList, ProductDTO productDTO){
        int i;
        if(dtoArrayList.size() == 0) return -1;
        for(i = 0; i < dtoArrayList.size(); i++) {
            if (dtoArrayList.get(i).getId() == productDTO.getId()) {
                return i;
            }
        }
        return -1;
    }

    public List<ProductDTO> removeItemFromArray(List<ProductDTO> dtoArrayList, int index){
        dtoArrayList.remove(index);
        return dtoArrayList;
    }
}

