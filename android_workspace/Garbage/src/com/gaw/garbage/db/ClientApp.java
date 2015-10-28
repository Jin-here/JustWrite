package com.gaw.garbage.db;

import com.gaw.garbage.debug.CrashHandler;

import android.app.Application;

public class ClientApp extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		new CrashHandler(getBaseContext());
	}
}
