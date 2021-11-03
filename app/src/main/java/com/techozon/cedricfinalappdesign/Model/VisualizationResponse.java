package com.techozon.cedricfinalappdesign.Model;

public class VisualizationResponse {
    private String time;
    private String musicURL;
    private String date;
    private String name;
    private String description;
    private String duration;
    private String imageURL;


    // Getter Methods

    public String getTime() {
        return time;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public String getImageURL() {
        return imageURL;
    }

    // Setter Methods

    public void setTime(String time) {
        this.time = time;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
