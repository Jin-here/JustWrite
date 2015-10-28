package com.example.lohaningweather;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity implements OnClickListener {
	private LinearLayout layout1;
	private TextView weather;
	private TextView tourism;
	private TextView me;
	private ImageView weather_ic;
	private ImageView tourism_ic;
	private ImageView me_ic;
	
	private Intent weIntent;
	private Intent toIntent;
	private Intent meIntent;
	private TabHost tabHost;
	
	public static MainActivity f=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		init();
		addTab(); 
		f=this;
		weather.setOnClickListener(this);
		tourism.setOnClickListener(this);
		me.setOnClickListener(this);
		weather_ic.setOnClickListener(this);
		tourism_ic.setOnClickListener(this);
		me_ic.setOnClickListener(this);
		tabHost.setCurrentTab(0);
	}
	
	//初始化
	 private void init(){
		weather=(TextView) findViewById(R.id.weather);
		tourism=(TextView) findViewById(R.id.tourism);
		me=(TextView) findViewById(R.id.me);
		
		weather_ic=(ImageView) findViewById(R.id.weather_ic);
		tourism_ic=(ImageView) findViewById(R.id.tourism_ic);
		me_ic=(ImageView) findViewById(R.id.me_ic);
		
		weIntent=new Intent(this,WeatherActivity.class);
		toIntent=new Intent(this,Tourism.class);
		meIntent=new Intent(this,Me.class);
		
		//获取tabHost
		 tabHost=getTabHost();
	 }
	
	//添加tab
	private void addTab(){
		 tabHost.addTab(tabHost.newTabSpec("weather").setIndicator("0").setContent(weIntent));
		 tabHost.addTab(tabHost.newTabSpec("tourism").setIndicator("1").setContent(toIntent));
		 tabHost.addTab(tabHost.newTabSpec("me").setIndicator("2").setContent(meIntent));
     }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		 
		case R.id.weather_ic:
			weather.setTextColor(getResources().getColor(R.color.tab));
			weather_ic.setImageResource(R.drawable.weather2);
			tabHost.setCurrentTab(0);
			tourism.setTextColor(getResources().getColor(R.color.default_c));
			tourism_ic.setImageResource(R.drawable.tourism);
			me.setTextColor(getResources().getColor(R.color.default_c));
			me_ic.setImageResource(R.drawable.me);
			break;
		case R.id.weather:
			weather.setTextColor(getResources().getColor(R.color.tab));
			weather_ic.setImageResource(R.drawable.weather2);
			tabHost.setCurrentTab(0);
			tourism.setTextColor(getResources().getColor(R.color.default_c));
			tourism_ic.setImageResource(R.drawable.tourism);
			me.setTextColor(getResources().getColor(R.color.default_c));
			me_ic.setImageResource(R.drawable.me);
			break;
			
		case R.id.tourism_ic:
			tourism.setTextColor(getResources().getColor(R.color.tab));
			tourism_ic.setImageResource(R.drawable.tourism2);
			tabHost.setCurrentTab(1);
			weather.setTextColor(getResources().getColor(R.color.default_c));
			weather_ic.setImageResource(R.drawable.weather);
			me.setTextColor(getResources().getColor(R.color.default_c));
			me_ic.setImageResource(R.drawable.me);
			break;
		case R.id.tourism:
			tourism.setTextColor(getResources().getColor(R.color.tab));
			tourism_ic.setImageResource(R.drawable.tourism2);
			tabHost.setCurrentTab(1);
			weather.setTextColor(getResources().getColor(R.color.default_c));
			weather_ic.setImageResource(R.drawable.weather);
			me.setTextColor(getResources().getColor(R.color.default_c));
			me_ic.setImageResource(R.drawable.me);
			break;
			
		case R.id.me_ic:
			me.setTextColor(getResources().getColor(R.color.tab));
			me_ic.setImageResource(R.drawable.me2);
			tabHost.setCurrentTab(2);
			weather.setTextColor(getResources().getColor(R.color.default_c));
			weather_ic.setImageResource(R.drawable.weather);
			tourism.setTextColor(getResources().getColor(R.color.default_c));
			tourism_ic.setImageResource(R.drawable.tourism);
			break;
		case R.id.me:
			me.setTextColor(getResources().getColor(R.color.tab));
			me_ic.setImageResource(R.drawable.me2);
			tabHost.setCurrentTab(2);
			weather.setTextColor(getResources().getColor(R.color.default_c));
			weather_ic.setImageResource(R.drawable.weather);
			tourism.setTextColor(getResources().getColor(R.color.default_c));
			tourism_ic.setImageResource(R.drawable.tourism);
			break;
			
			
		/*case R.id.weather:
			weather.setTextColor(getResources().getColor(R.color.tab));
			weather_ic.setImageResource(R.drawable.weather2);
			tabHost.setCurrentTab(0);
			break;
		case R.id.tourism:
			tourism.setTextColor(getResources().getColor(R.color.tab));
			tourism_ic.setImageResource(R.drawable.tourism2);
			tabHost.setCurrentTab(1);
			break;
		case R.id.me:
			me.setTextColor(getResources().getColor(R.color.tab));
			me_ic.setImageResource(R.drawable.me2);
			tabHost.setCurrentTab(2);
			break;*/
		}
	}
	
	
	// 返回键退出
		// 返回键
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (System.currentTimeMillis() - mExitTime > 1000) {
				Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
				return true;
			} else {

				MainActivity.this.finish();
				switchLocation s=new switchLocation();
				s.f.finish();
				LocationManage l=new LocationManage();
				l.f.finish();
				First f=new First();
				f.f.finish();
				WeatherActivity w=new WeatherActivity();
				w.f.finish();
				MoreDetail more=new MoreDetail();
				more.f.finish();
			
			}
		}
		return super.onKeyDown(keyCode, event);

	} 
	}
