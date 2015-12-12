package com.vgaw.bugcat;

import android.app.Application;

/**
 * Created by Administrator on 2015-12-12.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BugCat.getInstance().initial(getApplicationContext());
    }
}
