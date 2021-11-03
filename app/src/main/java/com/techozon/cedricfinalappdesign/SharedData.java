package com.techozon.cedricfinalappdesign;

import android.widget.TextView;

import java.util.Calendar;

public class SharedData {
     public static String  plan;
    public static String  id;
    public static String planPrice;
    public static double dPlanPrice;
    public static String planMonthlyPrice;
    public static String planDiscount;
    public static Boolean profileActivation;
    public static String username;
    public static String gender;
    public static String age;
    public static String height;
    public static String weight;
    public static String email;
    public static String imageUrl="oooo";
    public static  Integer coachId;
    public static  Integer warmupId;


    public static void setWelcomeMessage(TextView mTextWelcome) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 1 && hour <= 12)
            mTextWelcome.setText(new StringBuilder("Good morning."));
        else if (hour >= 13 && hour <= 17)
            mTextWelcome.setText(new StringBuilder("Good afternoon."));
        else
            mTextWelcome.setText(new StringBuilder("Good evening."));


    }
}
