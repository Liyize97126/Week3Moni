package com.bawei.week3moni.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 全局Context和SharedPreferences对象
 */
public class MyApplication extends Application {
    private static Context context;
    private static SharedPreferences sharedPreferences;
    public static Context getContext() {
        return context;
    }
    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
    }
}
