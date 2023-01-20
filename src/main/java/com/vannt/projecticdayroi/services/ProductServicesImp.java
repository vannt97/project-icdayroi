package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.dto.ProductDTO;
import com.vannt.projecticdayroi.entity.ProductEntity;
import com.vannt.projecticdayroi.repository.ProductRepository;
import com.vannt.projecticdayroi.uliti.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServicesImp implements ProductServices{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageUtil imageUtil;


    @Override
    public List<ProductDTO> getListProduct() {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductEntity productEntity: productRepository.findTop10ByOrderByCreateAtDesc()) {
            setProduct(list, productEntity);
        }
        return list;
    }

    @Override
    public List<ProductDTO> getListFeaturedProduct() {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductEntity productEntity: productRepository.getListFeatureProdcut()) {
            setProduct(list, productEntity);
            if(list.size() == 10){
                return list;
            }
        }
        return list;
    }

    @Override
    public List<ProductDTO> getListProductByName(String name) {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findByNameContaining(name)){
            setProduct(list, productEntity);
            if(list.size() == 20){
                return list;
            }
        }
        return list;
    }

    @Override
    public ProductDTO getProductBySlug(String slug) {
        ProductDTO productDTO = new ProductDTO();
        ProductEntity productEntity = productRepository.findBySlug(slug);
        if(productEntity != null){
            productDTO.setId(productEntity.getId());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setImage(productEntity.getImg());
            productDTO.setName(productEntity.getName());
            productDTO.setSlug(productEntity.getSlug());
            productDTO.setDescription(productEntity.getDescription());
            productDTO.setTypeProductEntity(productEntity.getTypeProductEntity());
            return productDTO;
        }
        return null;
    }

    @Override
    public List<ProductDTO> getProductsByTypeProduct(String slug) {
        List<ProductEntity> productEntities = productRepository.findAllBySlugTypeProductQueryNative(slug);
        if(productEntities.size() == 0){
            return null;
        }
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductEntity productEntity: productEntities){
            setProduct(productDTOList,productEntity);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> get10ProductsByTypeProduct(int id) {
        List<ProductEntity> productEntities = productRepository.findTop10ByTypeProductId(id);
        if(productEntities.size() == 0){
            return null;
        }
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductEntity productEntity: productEntities){
            setProduct(productDTOList,productEntity);
        }
        return productDTOList;
    }

    private void setProduct(List<ProductDTO> list, ProductEntity productEntity) {
        ProductDTO product = new ProductDTO();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setImage(productEntity.getImg());
        product.setPrice(productEntity.getPrice());
        product.setSlug(productEntity.getSlug());
        product.setDescription(productEntity.getDescription());
        product.setTypeProductEntity(productEntity.getTypeProductEntity());
        list.add(product);
    }
}
