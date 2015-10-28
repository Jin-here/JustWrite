package com.gaw.garbage.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.gaw.garbage.R;
import com.gaw.garbage.schedul.CatalogSchedul;
import com.gaw.garbage.schedul.FragmentSchedul;
import com.gaw.garbage.util.GawGestureListener;

public class CatalogActivity extends FragmentActivity {
	public GestureDetector mGestureDetector;
	
	private TextView jumper1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catalog_activity);
		
		GawGestureListener gesture = new GawGestureListener();
		gesture.setOnGestureOverListener(new CatalogSchedul(CatalogActivity.this));
		mGestureDetector = new GestureDetector(getBaseContext(), gesture);
		
		jumper1 = (TextView) findViewById(R.id.jumper1);
		jumper1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return mGestureDetector.onTouchEvent(event);
			}
		});
		jumper1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(R.anim.down_in, R.anim.down_out);
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
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.finish();
	}
}
