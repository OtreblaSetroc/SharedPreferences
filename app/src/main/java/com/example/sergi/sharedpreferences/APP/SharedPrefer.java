package com.example.sergi.sharedpreferences.APP;

import android.content.SharedPreferences;

public  class SharedPrefer {

    public static String getUsername(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("email","");
    }
    public static String getPass(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("pass","");
    }
}
