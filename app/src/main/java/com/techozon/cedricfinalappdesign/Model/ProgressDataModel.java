package com.techozon.cedricfinalappdesign.Model;

public class ProgressDataModel {
    String weekNo;
    Integer progress_img, days, id_,goForward;
    String day1;
    String day2;
    String day3;
    String day4;
    String day5;
    String day6;
    String day7;

    public ProgressDataModel(String weekNo, Integer progress_img, Integer id_,
                             String day1, String day2, String day3, String day4, String day5, String day6, String day7,Integer goForward) {
        this.weekNo = weekNo;
        this.progress_img = progress_img;
        this.days = days;
        this.id_ = id_;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.day7 = day7;
        this.goForward=goForward;
    }

    public Integer getGoForward() {
        return goForward;
    }

    public void setGoForward(Integer goForward) {
        this.goForward = goForward;
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    public Integer getProgress_img() {
        return progress_img;
    }

    public void setProgress_img(Integer progress_img) {
        this.progress_img = progress_img;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getId_() {
        return id_;
    }

    public void setId_(Integer id_) {
        this.id_ = id_;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }
}
