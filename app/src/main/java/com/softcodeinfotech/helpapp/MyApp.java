package com.softcodeinfotech.helpapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApp extends Application {

    private static Context context;
    private String TAG = "myApp";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
