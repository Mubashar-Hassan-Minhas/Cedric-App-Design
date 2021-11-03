package com.techozon.cedricfinalappdesign.Model;

public class WorkoutDataModel {
    public String imgThumbnail,title,time,url ;

    public WorkoutDataModel() {
    }

    public WorkoutDataModel(String imgThumbnail, String title, String time, String url) {
        this.imgThumbnail = imgThumbnail;
        this.title = title;
        this.time = time;
        this.url = url;
    }

    public String getImgThumbnail() {
        return imgThumbnail;
    }

    public void setImgThumbnail(String imgThumbnail) {
        this.imgThumbnail = imgThumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
