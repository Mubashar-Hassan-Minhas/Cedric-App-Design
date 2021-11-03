package com.techozon.cedricfinalappdesign.Model;

public class NutritionMethodModel {
    String serialNo,step;


    NutritionMethodModel(){

    }

    public NutritionMethodModel(String serialNo, String step) {
        this.serialNo = serialNo;
        this.step = step;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
