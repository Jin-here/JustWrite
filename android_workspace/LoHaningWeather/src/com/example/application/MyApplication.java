package com.example.application;

import java.util.LinkedList;
import java.util.List;


import com.getweather.category.Weather;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	private Weather weather;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		weather = new Weather();
	}
	
	public Weather getWeather(){
		return this.weather;
	}
	
	
	
  
}
