package com.techozon.cedricfinalappdesign;

public class SignupResponse {
    String status,id;
    Boolean isProfileActivated;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isProfileActivated() {
        return isProfileActivated;
    }

    public void setProfileActivated(boolean profileActivated) {
        isProfileActivated = profileActivated;
    }
}
