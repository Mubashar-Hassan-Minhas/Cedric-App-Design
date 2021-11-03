package com.techozon.cedricfinalappdesign.Retrofit;

import com.techozon.cedricfinalappdesign.Interface.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://us-central1-cedric-8cb7d.cloudfunctions.net/")
                    // as we are sending data in json format so
                    // we have to add Gson converter factory
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    // at last we are building our retrofit builder.
                    .build();

            // below line is to create an instance for our retrofit api class.


        return retrofit;
    }
    public static UserService getService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

}
