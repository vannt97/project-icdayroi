package com.vannt.projecticdayroi.payload.request;

import com.vannt.projecticdayroi.model.BillProductModel;

import java.util.Set;

public class RequestBill {
    private String email;
    private String name;
    private String phoneNumber;
    private String address;

    private String district;
    private String messageNote;
    private String delivery;
    private String payment;
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private Set<BillProductModel> listProduct;

    public Set<BillProductModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Set<BillProductModel> listProduct) {
        this.listProduct = listProduct;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
