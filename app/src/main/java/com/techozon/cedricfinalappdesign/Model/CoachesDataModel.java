package com.techozon.cedricfinalappdesign.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoachesDataModel implements Parcelable {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("imageURL")
    @Expose
    public String imageURL;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("coachNumber")
    @Expose
    public Integer coachNumber;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("Warmup")
    @Expose
    public List<Warmup> warmup = null;
    @SerializedName("Workout")
    @Expose
    public List<Workout> workout = null;

    protected CoachesDataModel(Parcel in) {
        name = in.readString();
        imageURL = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            coachNumber = null;
        } else {
            coachNumber = in.readInt();
        }
        role = in.readString();
    }

    public static final Creator<CoachesDataModel> CREATOR = new Creator<CoachesDataModel>() {
        @Override
        public CoachesDataModel createFromParcel(Parcel in) {
            return new CoachesDataModel(in);
        }

        @Override
        public CoachesDataModel[] newArray(int size) {
            return new CoachesDataModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCoachNumber() {
        return coachNumber;
    }

    public void setCoachNumber(Integer coachNumber) {
        this.coachNumber = coachNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Warmup> getWarmup() {
        return warmup;
    }

    public void setWarmup(List<Warmup> warmup) {
        this.warmup = warmup;
    }

    public List<Workout> getWorkout() {
        return workout;
    }

    public void setWorkout(List<Workout> workout) {
        this.workout = workout;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageURL);
        dest.writeString(description);
        if (coachNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(coachNumber);
        }
        dest.writeString(role);
    }

    public class Warmup {

        @SerializedName("coachNumber")
        @Expose
        public Integer coachNumber;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("videoUrl")
        @Expose
        public String videoUrl;
        @SerializedName("duration")
        @Expose
        public String duration;
        @SerializedName("exerciseType")
        @Expose
        public String exerciseType;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("thumbnail")
        @Expose
        public String thumbnail;

        public Integer getCoachNumber() {
            return coachNumber;
        }

        public void setCoachNumber(Integer coachNumber) {
            this.coachNumber = coachNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getExerciseType() {
            return exerciseType;
        }

        public void setExerciseType(String exerciseType) {
            this.exerciseType = exerciseType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }


    public class Workout {

        @SerializedName("exerciseType")
        @Expose
        public String exerciseType;
        @SerializedName("thumbnail")
        @Expose
        public String thumbnail;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("videoUrl")
        @Expose
        public String videoUrl;
        @SerializedName("coachNumber")
        @Expose
        public Integer coachNumber;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("programId")
        @Expose
        public Integer programId;
        @SerializedName("duration")
        @Expose
        public String duration;

    }
}