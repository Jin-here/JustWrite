package com.getweather.asynctask;

import com.getweather.category.Today;
import com.getweather.category.Weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoadWeatherThread extends Thread {
	private Weather weather;
	private Handler handler;
	private String cityName;
	
	public LoadWeatherThread(Handler handler, Weather weather, String cityName) {
		// TODO Auto-generated constructor stub
		this.weather = weather;
		this.handler = handler;
		this.cityName = cityName;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		weather.getWeatherInfo(cityName, Today.CURTEMP);
		System.out.println(cityName);
		Message message = handler.obtainMessage();
		message.what = 0x00;
		Bundle data = new Bundle();
		data.putString("cityName", cityName);
		message.setData(data);
		handler.sendMessage(message);
	}
}
