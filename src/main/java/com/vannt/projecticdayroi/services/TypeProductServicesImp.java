package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.dto.TypeProductDTO;
import com.vannt.projecticdayroi.dto.TypeSubProductDTO;
import com.vannt.projecticdayroi.entity.TypeProductEntity;
import com.vannt.projecticdayroi.repository.TypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeProductServicesImp implements TypeProductServices{
    @Autowired
    TypeProductRepository typeProductRepository;

    @Override
    public List<TypeProductDTO> getAllTypeProducts() {
        List<TypeProductEntity> products = typeProductRepository.findAll();
        List<TypeProductDTO> typeProductDTOs = new ArrayList<>();
        for (TypeProductEntity productEntity: products) {
            if(productEntity.getParentId() == 0){
                TypeProductDTO typeProductDTO = new TypeProductDTO();
                typeProductDTO.setName(productEntity.getName());
                typeProductDTO.setSlug(productEntity.getSlug());
                List<TypeSubProductDTO> list = new ArrayList<>();
                for(TypeProductEntity productEntitySecond : products){
                    if(productEntity.getId() == productEntitySecond.getParentId()){
                        TypeSubProductDTO typeSubProductDTO = new TypeSubProductDTO();
                        typeSubProductDTO.setName(productEntity.getName());
                        typeSubProductDTO.setSlug(productEntity.getSlug());
                        list.add(typeSubProductDTO);
                    }
                }
                typeProductDTO.setTypeSub(list);
                typeProductDTOs.add(typeProductDTO);
            }
        }
        return typeProductDTOs;
    }
}
