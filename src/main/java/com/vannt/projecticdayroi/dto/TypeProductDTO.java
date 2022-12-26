package com.vannt.projecticdayroi.dto;

import java.util.List;

public class TypeProductDTO {
    private String name;
    private String slug;
    private List<TypeSubProductDTO> typeSub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<TypeSubProductDTO> getTypeSub() {
        return typeSub;
    }

    public void setTypeSub(List<TypeSubProductDTO> typeSub) {
        this.typeSub = typeSub;
    }
}
