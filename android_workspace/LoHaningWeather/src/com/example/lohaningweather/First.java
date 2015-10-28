package com.example.lohaningweather;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

 
public class First extends Activity {
	Handler handler=new Handler();
	Intent intent;
	public static First f=null;
	
	private TimerTask timerTask=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					 
					startActivity(intent);
				}
			});
			
		}
	}; 
		
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first);
		 
		
		f=this;
		
		Timer timer=new Timer();
		timer.schedule(timerTask,2000);
		 
	    intent=new Intent(this,MainActivity.class);
		 
		 
		
	}
}
