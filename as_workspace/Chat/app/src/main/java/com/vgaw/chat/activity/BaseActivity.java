package com.vgaw.chat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.vgaw.chat.application.MyApplication;

/**
 * Created by Administrator on 2015/9/19.
 */
public class BaseActivity extends FragmentActivity {
    protected MyApplication app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (MyApplication) this.getApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
