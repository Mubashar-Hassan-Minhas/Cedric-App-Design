package com.techozon.cedricfinalappdesign.Model;

public class PlansDataModel {
    String id,discount,totalPrice,monthlyPrice,duration;

    public PlansDataModel(String id, String discount, String totalPrice, String monthlyPrice, String duration) {
        this.id = id;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.monthlyPrice = monthlyPrice;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(String monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
