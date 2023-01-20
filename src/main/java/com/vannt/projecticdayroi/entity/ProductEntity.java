package com.vannt.projecticdayroi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_product_id")
    private int typeProductId;

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String description;

    private long price;

    private String name;

    private String available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_product_id" , insertable = false, updatable = false)
    private TypeProductEntity typeProductEntity;

    public TypeProductEntity getTypeProductEntity() {
        return typeProductEntity;
    }

    public void setTypeProductEntity(TypeProductEntity typeProductEntity) {
        this.typeProductEntity = typeProductEntity;
    }

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;

    private String slug;

    @Column(name = "is_featured_product")
    private boolean isFeaturedProduct;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BillProductEntity> billProductEntitySet = new HashSet<>();

    public Set<BillProductEntity> getBillProductEntitySet() {
        return billProductEntitySet;
    }

    public void setBillProductEntitySet(Set<BillProductEntity> billProductEntitySet) {
        this.billProductEntitySet = billProductEntitySet;
    }

    public boolean isFeaturedProduct() {
        return isFeaturedProduct;
    }

    public void setFeaturedProduct(boolean featuredProduct) {
        isFeaturedProduct = featuredProduct;
    }

    public int getTypeProductId() {
        return typeProductId;
    }

    public void setTypeProductId(int typeProductId) {
        this.typeProductId = typeProductId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
