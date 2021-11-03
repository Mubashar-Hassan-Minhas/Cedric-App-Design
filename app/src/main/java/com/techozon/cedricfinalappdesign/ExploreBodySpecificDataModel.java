package com.techozon.cedricfinalappdesign;

public class ExploreBodySpecificDataModel {
    int imageBodySpecific;
    int _id;


    public ExploreBodySpecificDataModel(int imageBodySpecific, int _id) {
        this.imageBodySpecific = imageBodySpecific;
        this._id = _id;
    }

    public int getImageBodySpecific() {
        return imageBodySpecific;
    }

    public void setImageBodySpecific(int imageBodySpecific) {
        this.imageBodySpecific = imageBodySpecific;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
