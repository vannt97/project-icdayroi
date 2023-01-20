package com.vannt.projecticdayroi.repository;

import com.vannt.projecticdayroi.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    public List<ProductEntity> findAll();

    public List<ProductEntity> findByOrderByCreateAtDesc();

    public List<ProductEntity> findTop10ByOrderByCreateAtDesc();

    @Query("SELECT p FROM product p WHERE p.isFeaturedProduct = true")
    public List<ProductEntity> getListFeatureProdcut();

    public List<ProductEntity> findByNameContaining(String name);

    public ProductEntity findBySlug(String slug);

    @Query(value = "select * from product as p left join type_product as t on p.type_product_id = t.id where t.slug = ?1", nativeQuery = true)
    public List<ProductEntity> findAllBySlugTypeProductQueryNative(String slug);

    public List<ProductEntity> findTop10ByTypeProductId(int id);

    public ProductEntity findById(int id);

}
