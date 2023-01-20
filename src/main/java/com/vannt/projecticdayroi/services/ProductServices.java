package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.dto.ProductDTO;
import com.vannt.projecticdayroi.entity.ProductEntity;

import java.util.List;

public interface ProductServices {

    public List<ProductDTO> getListProduct();

    public List<ProductDTO> getListFeaturedProduct();

    public List<ProductDTO> getListProductByName(String name);

    public ProductDTO getProductBySlug(String slug);

    public List<ProductDTO> getProductsByTypeProduct(String slug);

    public List<ProductDTO> get10ProductsByTypeProduct(int id);

}
