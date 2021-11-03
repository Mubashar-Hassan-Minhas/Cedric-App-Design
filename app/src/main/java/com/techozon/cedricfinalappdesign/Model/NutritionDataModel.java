package com.techozon.cedricfinalappdesign.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NutritionDataModel implements Parcelable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("day")
    @Expose
    public String day;
    @SerializedName("time")
    @Expose
    public String time;
    @SerializedName("imageURL")
    @Expose
    public String imageURL;

    public List<String> method;
    @SerializedName("nutritionId")
    @Expose
    public int nutritionId;
    @SerializedName("Ingredients")
    @Expose
    public List<Ingredients> ingredients = null;

    protected NutritionDataModel(Parcel in) {
        status = in.readString();
        name = in.readString();
        day = in.readString();
        time = in.readString();
        imageURL = in.readString();
        method = in.createStringArrayList();
        nutritionId = in.readInt();
    }

    public static final Creator<NutritionDataModel> CREATOR = new Creator<NutritionDataModel>() {
        @Override
        public NutritionDataModel createFromParcel(Parcel in) {
            return new NutritionDataModel(in);
        }

        @Override
        public NutritionDataModel[] newArray(int size) {
            return new NutritionDataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(name);
        dest.writeString(day);
        dest.writeString(time);
        dest.writeString(imageURL);
        dest.writeStringList(method);
        dest.writeInt(nutritionId);
    }

    public class Ingredients {
        public int nutritionId;
        public String name;
        public int ingredientId;
        public String protiens;
        public String quantity;
        public String calories;
        public String fats;
        public String carbs;
        public String unit;

        public int getNutritionId() {
            return nutritionId;
        }

        public void setNutritionId(int nutritionId) {
            this.nutritionId = nutritionId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIngredientId() {
            return ingredientId;
        }

        public void setIngredientId(int ingredientId) {
            this.ingredientId = ingredientId;
        }

        public String getProtiens() {
            return protiens;
        }

        public void setProtiens(String protiens) {
            this.protiens = protiens;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getCalories() {
            return calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public String getFats() {
            return fats;
        }

        public void setFats(String fats) {
            this.fats = fats;
        }

        public String getCarbs() {
            return carbs;
        }

        public void setCarbs(String carbs) {
            this.carbs = carbs;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }


}
