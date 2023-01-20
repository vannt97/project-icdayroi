package com.vannt.projecticdayroi.dto;

import com.vannt.projecticdayroi.entity.TypeProductEntity;

public class ProductDTO {
    private int id;
    private String image;
    private String name;
    private long price;
    private String slug;
    private TypeProductEntity typeProductEntity;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeProductEntity getTypeProductEntity() {
        return typeProductEntity;
    }

    public void setTypeProductEntity(TypeProductEntity typeProductEntity) {
        this.typeProductEntity = typeProductEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
