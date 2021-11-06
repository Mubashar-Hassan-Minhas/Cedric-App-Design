//package com.techozon.cedricfinalappdesign.LocalDatabase;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class DBHelper extends SQLiteOpenHelper {
////   public static final String DBNAME="cedric.db";
////
////    public DBHelper(Context context) {
////        super(context, "cedric.db", null, 1);
////
////    }
////
////    @Override
////    public void onCreate(SQLiteDatabase db) {
////        db.execSQL("create table users(email TEXT primary key,name TEXT, password TEXT)");
////
////    }
////
////    @Override
////    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
////        db.execSQL("drop table if exists users");
////
////    }
////
////    public Boolean insertData (String email,String name,String password){
////        SQLiteDatabase db=this.getWritableDatabase();
////        ContentValues values= new ContentValues();
////
////        values.put("email",email);
////        values.put("name",name);
////        values.put("password",password);
////
////        long result=db.insert("users",null,values);
////        if(result==-1)
////            return false;
////        else
////            return true;
////    }
//}
