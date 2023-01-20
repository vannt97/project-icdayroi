package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.dto.TypeProductDTO;
import com.vannt.projecticdayroi.entity.TypeProductEntity;

import java.util.List;

public interface TypeProductServices {

    public List<TypeProductDTO> getAllTypeProducts();

    public TypeProductEntity getTypeProduct(String slug);
}
