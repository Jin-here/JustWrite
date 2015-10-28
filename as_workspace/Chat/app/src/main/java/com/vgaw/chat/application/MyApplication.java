package com.vgaw.chat.application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/9/19.
 */
public class MyApplication extends Application{
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        this.sharedPreferences = getSharedPreferences("com.vgaw.chat.mSP", MODE_PRIVATE);

    }

    public SharedPreferences getSP(){
        return this.sharedPreferences;
    }

}
