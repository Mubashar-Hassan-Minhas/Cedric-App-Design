package com.techozon.cedricfinalappdesign.Interface;

import com.techozon.cedricfinalappdesign.LoginResponse;
import com.techozon.cedricfinalappdesign.Model.BestProgramModel;
import com.techozon.cedricfinalappdesign.Model.CoachesDataModel;
import com.techozon.cedricfinalappdesign.Model.NutritionDataModel;
import com.techozon.cedricfinalappdesign.Model.PlansDataModel;
import com.techozon.cedricfinalappdesign.Model.ProfileActivationResponse;
import com.techozon.cedricfinalappdesign.Model.ProgramsDataModel;
import com.techozon.cedricfinalappdesign.Model.ProgressDataModel;
import com.techozon.cedricfinalappdesign.Model.UpdateProfileModel;
import com.techozon.cedricfinalappdesign.Model.VisualizationResponse;
import com.techozon.cedricfinalappdesign.SignupResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UserService {

    //post user Signup credentials
    @POST("/user/signup")
        //on below line we are creating a method to post our data.
    Call<SignupResponse> createPost(
            @Query("name") String name, @Query("email") String email, @Query("password") String password);

//post user Login credentials
    @POST("/user/login")
        //on below line we are creating a method to post our data.
    Call<LoginResponse> loginDataPost(
            @Query("email") String email, @Query("password") String password);

//ProfileActivation
    @PUT("/user/activateProfile/{id}")
        //on below line we are creating a method to post our data.
    Call<ProfileActivationResponse> profileActivate(@Path("id") String id,
            @Query("weight") String weight, @Query("height") String height, @Query("age") String age,@Query("gender") String gender, @Query("goals") String goals,
            @Query("level") String level,@Query("profileImage") String profileImage, @Query("paymentMethod") String paymentMethod,
            @Query("comments") String comments,@Query("orderId") String orderId, @Query("orderRef") String orderRef,
            @Query("orderStatus") String orderStatus, @Query("transactionDate") String transactionDate, @Query("price") String price,@Query("duration") String duration);

//get packages
    @GET("packages")
    Call<ArrayList<PlansDataModel>> getAllPackages();

    //getProfile
//    @GET("/user/getProfile/{id}")
//    Call<getProfileResponseModel> getProfileData(@Path("id")String id);

    //Update Profile Data

    @PUT("/user/updateProfile/{id}")
    Call<UpdateProfileModel> updateProfileData(@Path("id")String id, @Query("weight") String weight, @Query("name") String name, @Query("email") String email,
                                               @Query("height") String height, @Query("age") String age
                                              , @Query("profileImage") String profileImage);


    @GET("coaches")
    Call<ArrayList<CoachesDataModel>> getAllCoachesData();

    @POST("sleepVisualization")
        //on below line we are creating a method to post our data.
    Call<VisualizationResponse> VisualizationDataPost(
            @Query("time") String time, @Query("date") String date);

    @POST("nutrition")
        //on below line we are creating a method to post our data.
    Call<NutritionDataModel> nutritionDataPost(
            @Query("day") String day, @Query("time") String time);


    //get Programs
    @GET("programInformation")
    Call<ArrayList<ProgramsDataModel>> getAllPrograms();

//Programs Progress
    @POST("programInformation/getData")
        //on below line we are creating a method to post our data.
    Call<BestProgramModel> programProgressDataPost(
            @Query("programId") Integer programId,@Query("week") Integer week,@Query("day") String day);
}
