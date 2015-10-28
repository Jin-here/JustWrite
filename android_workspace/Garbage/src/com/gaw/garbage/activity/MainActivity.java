package com.gaw.garbage.activity;

import com.gaw.garbage.R;
import com.gaw.garbage.schedul.ControlSchedul;
import com.gaw.garbage.schedul.FragmentSchedul;
import com.gaw.garbage.util.GawGestureListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	public GestureDetector mGestureDetector;
	public GestureDetector mGestureDetector1;
	
	private TextView jumper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mGestureDetector = new GestureDetector(getBaseContext(), new FragmentSchedul(MainActivity.this));
		
		GawGestureListener gesture = new GawGestureListener();
		gesture.setOnGestureOverListener(new ControlSchedul(MainActivity.this));
		mGestureDetector1 = new GestureDetector(getBaseContext(), gesture);		
		jumper = (TextView) findViewById(R.id.jumper);
		jumper.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return mGestureDetector1.onTouchEvent(event);
			}
		});
		jumper.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 进入列表界面
				startActivity(new Intent(getBaseContext(), CatalogActivity.class));
				overridePendingTransition(R.anim.up_in, R.anim.up_out);
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("error", "activity:onstop");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.finish();
	}
	
}
