package com.vannt.projecticdayroi.dto;

import com.vannt.projecticdayroi.model.BillProductModel;

import java.util.List;
import java.util.Set;

public class BillDTO {
    private String messageNote;
    private String delivery;
    private String payment;
    private Set<BillProductModel> billProductModelSet;

    public Set<BillProductModel> getBillProductModelSet() {
        return billProductModelSet;
    }

    public void setBillProductModelSet(Set<BillProductModel> billProductModelSet) {
        this.billProductModelSet = billProductModelSet;
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
