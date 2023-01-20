package com.vannt.projecticdayroi.entity.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class BillProductId implements Serializable {
    @Column(name = "id_bill", nullable = false)
    private int idBill;

    @Column(name = "id_product", nullable = false)
    private int idProduct;

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
