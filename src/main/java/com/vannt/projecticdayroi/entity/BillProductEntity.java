package com.vannt.projecticdayroi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vannt.projecticdayroi.entity.id.BillProductId;

import javax.persistence.*;

@Entity(name = "bill_product")
public class BillProductEntity {
    @EmbeddedId
    private BillProductId billProductId = new BillProductId();

    private int amount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_bill")
    @MapsId("idBill")
     BillEntity billEntity;

    @JsonIgnore
    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_product")
     ProductEntity productEntity;

    public BillProductId getBillProductId() {
        return billProductId;
    }

    public void setBillProductId(BillProductId billProductId) {
        this.billProductId = billProductId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BillEntity getBillEntity() {
        return billEntity;
    }

    public void setBillEntity(BillEntity billEntity) {
        this.billEntity = billEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
