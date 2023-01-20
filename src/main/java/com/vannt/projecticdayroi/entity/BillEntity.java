package com.vannt.projecticdayroi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "create_at")
    private String createAt;

    private String status;

    @Column(name = "message_note")
    private String messageNote;

    private String delivery;

    private String payment;

    @Column(name = "delivery_price")
    private long deliveryPrice;

    public long getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(long deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @OneToMany(mappedBy = "billEntity", cascade = CascadeType.PERSIST)
    private Set<BillProductEntity> billProductEntities = new HashSet<>();

    public Set<BillProductEntity> getBillProductEntities() {
        return billProductEntities;
    }

    public void setBillProductEntities(Set<BillProductEntity> billProductEntities) {
        this.billProductEntities = billProductEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageNote() {
        return messageNote;
    }

    public void setMessageNote(String messageNote) {
        this.messageNote = messageNote;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
