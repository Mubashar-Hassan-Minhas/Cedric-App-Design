package com.techozon.cedricfinalappdesign.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BestProgramModel {

        @SerializedName("Warmup")
        @Expose
        public List<Warmup> warmup;

        @SerializedName("Workout")
        @Expose
        public List<Workout> workout;


    public class Workout{
        @SerializedName("workoutId")
        @Expose
        public int workoutId;
        @SerializedName("programId")
        @Expose
        public Integer programId;
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
    }
    public class Warmup{
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

        public int workoutId;

    }

}
