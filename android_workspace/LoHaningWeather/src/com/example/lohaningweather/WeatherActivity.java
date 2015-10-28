package com.example.lohaningweather;

import java.util.ArrayList;
import java.util.List;

import location.Locate;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.MyApplication;
import com.getweather.asynctask.LoadWeatherThread;
import com.getweather.category.ChuanYi;
import com.getweather.category.FangShai;
import com.getweather.category.Forecast;
import com.getweather.category.GanMao;
import com.getweather.category.History;
import com.getweather.category.Today;
import com.getweather.category.Weather;
import com.weather.sql.dao.CityDAO;
import com.weather.sql.model.Tb_city;

public class WeatherActivity extends Activity implements OnPageChangeListener,
		OnClickListener {
	private TextView current_temp;
	private ImageView more_detail;
	private TextView current_time;
	private TextView temp_quality_number;
	private TextView temp_quality;
	private TextView high_temp;
	private TextView low_temp;
	private TextView temp_condition;
	private TextView wind_direction;
	private ImageView temp_picture;
	private TextView Location_name;
	private ImageView iv_location_right;
	private ImageView more_city;

	public static WeatherActivity f=null;
	
	private String GMdetail;
	private String CYdetail;
	private String FSdetail;
	/**
	 * viewpager相关
	 * 
	 */
	private ViewPager mViewPager;
	private ArrayList<View> mPageViews;
	private ImageView mImageView;
	private ImageView[] mImageViews;
	// 该应用的主布局LinearLayout
	private ViewGroup mainViewGroup;
	// 主布局底部指示当前页面的小圆点视图，LinearLayout
	private ViewGroup indicatorViewGroup;
	// 头布局
	private ViewGroup headViewGroup;
	// 定义LayoutInflater
	LayoutInflater mInflater;

	private Weather weather = null;
	private MyApplication app;
	
	private ArrayList<String> labels;
	private ArrayList<Float> hightemp;
	private ArrayList<Float> lowtemp;

	private ArrayList<String> arrayList = null;
	
	private Locate locate;
	private String current_location_detail;
	
	/*
	 * 最下方未来几天的天气
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	private ImageView zuori_image;
	private ImageView jinri_image;
	private ImageView mingri_image;
	private ImageView houri_image;
	private ImageView weilai_image;
	private TextView zuori_temp;
	private TextView jinri_temp;
	private TextView mingri_temp;
	private TextView houri_temp;
	private TextView weilai_temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.weather_viewpager);
	
		handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch (msg.what){
				case 0x00:
					String cityName = msg.getData().getString("cityName");
					getWeather(cityName);
					System.out.println("刷新界面：" + cityName);
					
					break;
				case 0x01:
					//处理返回的地址信息
					String address = msg.getData().getString("address");
					//locate.locate();
				    //获取截取后的地址信息
					getLocate(address);
					
					String a="";
					if(address.indexOf("省")!=-1){
						if(address.indexOf("区")!=-1){
					         a=address.substring(address.indexOf("省")+1,address.indexOf("区")+1);
						}else if(address.indexOf("县")!=-1){
							 a=address.substring(address.indexOf("省")+1,address.indexOf("县")+1);
						}else if(address.indexOf("市",8)!=-1){
							a=address.substring(address.indexOf("省")+1,address.indexOf("市",8)+1);
						}else{
							a=address.substring(0,address.indexOf("省")+1);
						}
					}
					
					Location_name.setText(a==null?"无":a);
					viewPager_init(0);
					new LoadWeatherThread(handler, weather,getLocate(address)).start();
					
					SharedPreferences mySharedPreferences= getSharedPreferences("test",
							Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = mySharedPreferences.edit();
					editor.putString("current_location",getLocate(address).trim());
					editor.putString("current_location_detail",a==null?"暂无":a);
					editor.commit(); 
					break;
				}
			}
		};
		app = (MyApplication) getApplication();
		
		weather = app.getWeather();
		init_category();
	 
		
		locate = new Locate(getApplicationContext(), handler); 
		locate.locate();
		
		more_city.setOnClickListener(this);
		setContentView(mainViewGroup);

		mViewPager.setAdapter(new MyPagerAdapter(mPageViews,
				WeatherActivity.this));
		
		mViewPager.setOnPageChangeListener(this);
		f=this;
	}

	// 从数据库中获取城市数据
	private void SQLgetData() {
		SharedPreferences sharedPreferences= getSharedPreferences("test",
				Activity.MODE_PRIVATE); 
		String current_location=sharedPreferences.getString("current_location", "");
		current_location_detail=sharedPreferences.getString("current_location_detail","");
		arrayList.add(current_location);// 添加默认定位的城市
		
		//arrayList.add("北京");
		CityDAO cityDAO = new CityDAO(WeatherActivity.this);
		List<Tb_city> strInfo = cityDAO.getScrollData();
		for (Tb_city tb_city : strInfo) {
			if (String.valueOf(tb_city.getCity()) != null) {
				String cityList = String.valueOf(tb_city.getCity());
				if(cityList.indexOf('市')!=-1){
				arrayList.add(cityList.substring(0, cityList.indexOf('市')));
				}else{
				arrayList.add(cityList);	
				}
			}
		}
	}

	/*
	 * 对viewPager中组件进行管理
	 */
	private void viewPager_init(int i) {
		current_temp = (TextView) mPageViews.get(i).findViewById(
				R.id.current_temp);
		current_time = (TextView) mPageViews.get(i).findViewById(
				R.id.current_time);
		temp_quality_number = (TextView) mPageViews.get(i).findViewById(
				R.id.temp_quality_number);
		temp_quality = (TextView) mPageViews.get(i).findViewById(
				R.id.temp_quality);
		high_temp = (TextView) mPageViews.get(i).findViewById(R.id.high_temp);
		low_temp = (TextView) mPageViews.get(i).findViewById(R.id.low_temp);
		temp_condition = (TextView) mPageViews.get(i).findViewById(
				R.id.temp_condition);
		wind_direction = (TextView) mPageViews.get(i).findViewById(
				R.id.wind_direction);
		temp_picture = (ImageView) mPageViews.get(i).findViewById(
				R.id.temp_picture);
		more_detail=(ImageView)mPageViews.get(i).findViewById(R.id.more_detail);
		
		//未来几天天气情况
		zuori_image=(ImageView)mPageViews.get(i).findViewById(R.id.zuori_image);
		jinri_image=(ImageView)mPageViews.get(i).findViewById(R.id.jinri_image);
		mingri_image=(ImageView)mPageViews.get(i).findViewById(R.id.mingri_image);
		houri_image=(ImageView)mPageViews.get(i).findViewById(R.id.houri_image);
		weilai_image=(ImageView)mPageViews.get(i).findViewById(R.id.weilai_image);
		
		zuori_temp=(TextView)mPageViews.get(i).findViewById(R.id.zuori_temp);
		jinri_temp=(TextView)mPageViews.get(i).findViewById(R.id.jinri_temp);
		mingri_temp=(TextView)mPageViews.get(i).findViewById(R.id.mingri_temp);
		houri_temp=(TextView)mPageViews.get(i).findViewById(R.id.houri_temp);
		weilai_temp=(TextView)mPageViews.get(i).findViewById(R.id.weilai_temp);
	}

	// 初始化
	private void init_category() {

		// 获取数据库中城市的列表
		arrayList = new ArrayList<String>();
		SQLgetData();

		/**
		 * viewpager相关
		 * 
		 */
		mInflater = getLayoutInflater();
		mPageViews = new ArrayList<View>();
		for (int i = 0; i < arrayList.size(); i++) {
			mPageViews.add(mInflater.inflate(R.layout.weather, null));	
		}
		 
			
		mImageViews = new ImageView[mPageViews.size()];
		
	 

		mainViewGroup = (ViewGroup) mInflater.inflate(
				R.layout.weather_viewpager, null);

		mViewPager = (ViewPager) mainViewGroup.findViewById(R.id.myviewpager);
		indicatorViewGroup = (ViewGroup) mainViewGroup
				.findViewById(R.id.mybottomviewgroup);

		
		
		for (int i = 0; i < mImageViews.length; i++) {
			mImageView = new ImageView(WeatherActivity.this);
			mImageView.setLayoutParams(new LayoutParams(20, 20));
			mImageView.setPadding(20, 0, 20, 0);
			if (i == 0) {
				mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
			} else {
				mImageView.setBackgroundResource(R.drawable.page_indicator_focused);
			}

			mImageViews[i] = mImageView;

			// 把指示作用的远点图片加入底部的视图中
			indicatorViewGroup.addView(mImageViews[i]);
		}

		// 注意这两种用法的区别，前者无法正常显示！！
		// setContentView(R.layout.main);
		headViewGroup = (ViewGroup) mainViewGroup
				.findViewById(R.id.headViewGroup);
	
		Location_name = (TextView) headViewGroup
				.findViewById(R.id.location_name);
		iv_location_right=(ImageView) headViewGroup.findViewById(R.id.iv_location_right);
		more_city = (ImageView) headViewGroup.findViewById(R.id.more_city);
		//Location_name.setText("南京");
		
		//第一个加载的页面
		String cityName = arrayList.get(0);
		Location_name.setText(current_location_detail);
		viewPager_init(0);
		new LoadWeatherThread(handler, weather, arrayList.get(0)).start();
		
		 
	}

	// 从天气接口获取天气信息
	private void getWeather(String cityName) {

		/*
		 * //异步处理 myAsyncTask=(MyWeatherAsyncTask) new
		 * MyWeatherAsyncTask(cityName, this).execute(); String data=this.data;
		 * String WeatherData[]=data.split("|");
		 */

		if(cityName.equals("")){
			return;
		}

		current_temp.setText(check(weather.getWeatherInfo(cityName, Today.CURTEMP)));
		current_time.setText(check(weather.getWeatherInfo(cityName, Today.DATE)
				+ weather.getWeatherInfo(cityName, Today.WEEK)));
		temp_quality_number.setText(check(weather.getWeatherInfo(cityName, Today.AQI)));
		high_temp.setText(check(weather.getWeatherInfo(cityName, Today.HIGHTEMP)));
		low_temp.setText(check(weather.getWeatherInfo(cityName, Today.LOWTEMP)));
		temp_condition.setText(check(weather.getWeatherInfo(cityName, Today.TYPE)));
		wind_direction.setText(check(weather.getWeatherInfo(cityName, Today.FENGLI)));
		
		
		//点击进入穿衣指数 等详情页
        more_detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showTANC();
			}
		});
		
		CYdetail=weather.getWeatherInfo(cityName,ChuanYi.DETAILS);//穿衣细节
		GMdetail=weather.getWeatherInfo(cityName,GanMao.DETAILS); //感冒注意
		FSdetail=weather.getWeatherInfo(cityName,FangShai.DETAILS); //防晒细节
		//Toast.makeText(WeatherActivity.this, CYdetail, Toast.LENGTH_SHORT).show();
		//cy_detail.setText(CYdetail);
		int aNumber = -1;
		if(weather.getWeatherInfo(cityName, Today.AQI).equals("")||
				weather.getWeatherInfo(cityName, Today.AQI).equals("null")){
			aNumber = -1;
		}else{
		   aNumber = Integer.parseInt(temp_quality_number.getText().toString());
		}
		if(aNumber<0){
			temp_quality.setText("无");
		}else if (aNumber >= 0 && aNumber <= 50) {
			temp_quality.setText("优");
			temp_quality.setTextColor(getResources().getColorStateList(R.color.hao));		  
		} else if (aNumber > 50 && aNumber <= 100) {
			temp_quality.setText("良");
			temp_quality.setTextColor(getResources().getColorStateList(R.color.liang));
		} else {
			temp_quality.setText("差");
			temp_quality.setTextColor(getResources().getColorStateList(R.color.cha));
		}
		// 设置天气状况的图片
		String type = weather.getWeatherInfo(cityName, Today.TYPE);
		type_image(type,temp_picture);
		
	 
		
		System.out.print("\u2103");
		//未来几天天气情况
		zuori_temp.setText(sub(weather.getWeatherInfo(cityName, History.HIGHTEMP,-1))+"/"+
				sub(weather.getWeatherInfo(cityName, History.LOWTEMP,-1)));
		jinri_temp.setText(sub(weather.getWeatherInfo(cityName, Today.HIGHTEMP))+"/"+
				sub(weather.getWeatherInfo(cityName, Today.LOWTEMP)));
		mingri_temp.setText(sub(weather.getWeatherInfo(cityName, History.HIGHTEMP,1))+"/"+
				sub(weather.getWeatherInfo(cityName, History.LOWTEMP,1)));
		houri_temp.setText(sub(weather.getWeatherInfo(cityName, History.HIGHTEMP,2))+"/"+
				sub(weather.getWeatherInfo(cityName, History.LOWTEMP,2)));
		weilai_temp.setText(sub(weather.getWeatherInfo(cityName, History.HIGHTEMP,3))+"/"+
				sub(weather.getWeatherInfo(cityName, History.LOWTEMP,3)));
		//未来几天天气情况所显示的图片  type_image(String type,ImageView imageView)
		
		type_image(weather.getWeatherInfo(cityName, History.TYPE,-1),zuori_image);
		type_image(weather.getWeatherInfo(cityName, Today.TYPE),jinri_image);
		type_image(weather.getWeatherInfo(cityName, History.TYPE,1),mingri_image);
		type_image(weather.getWeatherInfo(cityName, History.TYPE,2),houri_image);
		type_image(weather.getWeatherInfo(cityName, History.TYPE,3),weilai_image);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		Log.e("e", "onPageScrolled");
	}

	@Override
	public void onPageSelected(int arg0) {
		//Log.e("e", "onPageSelected");
		if(arg0==0){
		    iv_location_right.setVisibility(View.VISIBLE);
		}else{
		    iv_location_right.setVisibility(View.GONE);	
		}
		 
		String cityName = arrayList.get(arg0);
		viewPager_init(arg0);

		//new GawLineChart(getBaseContext(), this.handler, chartView, imageButton).showChart();
		//加载地点
		new LoadWeatherThread(handler, weather, cityName).start();
		Location_name.setText(cityName);
		if(arg0==0){
			Location_name.setText(current_location_detail);
		}

        //设置滑动时的小圆点
		for (int i = 0; i < mImageViews.length; i++) {
			if (i == arg0) {
				mImageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_unfocused);
			} else {
				mImageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.more_city:
			Intent intent = new Intent(WeatherActivity.this,
					LocationManage.class);
			startActivity(intent);
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch (msg.what){
				case 0x00:
					String cityName = msg.getData().getString("cityName");
					getWeather(cityName);
					System.out.println("刷新界面：" + cityName);
					break;
				case 0x01:
	
				}
			}
		};
		app = (MyApplication) getApplication();
		weather = app.getWeather();
		init_category();
		more_city.setOnClickListener(this);
		setContentView(mainViewGroup);
		mViewPager.setAdapter(new MyPagerAdapter(mPageViews,
				WeatherActivity.this));
		
		
		mViewPager.setOnPageChangeListener(this);
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

				WeatherActivity.this.finish();
				switchLocation s=new switchLocation();
				s.f.finish();
				LocationManage l=new LocationManage();
				l.f.finish();
				First f=new First();
				f.f.finish();
				MainActivity m=new MainActivity();
				m.f.finish();
				MoreDetail more=new MoreDetail();
				more.f.finish();
			
			}
		}
		return super.onKeyDown(keyCode, event);

	}
	
	private Handler handler;

	
	
	
	/*public   boolean last_item(){
		//!!!!!!// 异步处理/加载最后一个页面的地点
		        mViewPager.setCurrentItem(arrayList.size()-1==-1?0:arrayList.size()-1);
				viewPager_init(arrayList.size()-1==-1?0:arrayList.size()-1);
				new LoadWeatherThread(handler, weather, arrayList.get(arrayList.size()-1==-1?0:arrayList.size()-1)).start();
				String cityName = arrayList.get(arrayList.size()-1==-1?0:arrayList.size()-1);
				mImageViews[arrayList.size()-1==-1?0:arrayList.size()-1]
						.setBackgroundResource(R.drawable.page_indicator_focused);
 			   mImageViews[0].setBackgroundResource(R.drawable.page_indicator);
			return true;
		}
	public boolean first_item(){
		//!!!!!// 异步处理/加载默认定位的地点
		viewPager_init(0);
		String cityName = arrayList.get(0);
		new LoadWeatherThread(handler, weather, arrayList.get(0)).start();
		return true;
	}*/

//判断定位到的地点
	private String getLocate(String address){
		String a;
		if(address.indexOf("北京")==-1&&address.indexOf("重庆")==-1&&
		   address.indexOf("天津")==-1&&address.indexOf("上海")==-1){
			    if(address.indexOf("香港")==-1&&address.indexOf("澳门")==-1){
			    	//各省 
			        a=address.substring(address.indexOf("省")+1,address.indexOf("市"));
			    	return a==null?"无数据":a;
			    }else{
			    	//港澳
			        a=address.substring(0, 2);
			        return a==null?"无数据":a;
			    }
	    }else{
			        a=address.substring(0,address.indexOf("市"));
			        return a==null?"无数据":a;
			  }
	}
	
	 private void showTANC() {  
	        final Dialog dialog = new Dialog(this, R.style.popupDialog);  
	        dialog.setContentView(R.layout.more_detail);  
	        dialog.setTitle("注意事项");  
	        dialog.setCancelable(true);  
	        dialog.setCanceledOnTouchOutside(true);
	        TextView cy_detail = (TextView) dialog.findViewById(R.id.cy_detail);
	        TextView gm_detail = (TextView) dialog.findViewById(R.id.gm_detail);
	        TextView fs_detail = (TextView) dialog.findViewById(R.id.fs_detail);
	        cy_detail.setText(CYdetail==null?"暂无":CYdetail);  
	        gm_detail.setText(GMdetail==null?"暂无":GMdetail); 
	        fs_detail.setText(FSdetail==null?"暂无":FSdetail); 
	        dialog.show();  
	    }
	
	
	 //截取字符串格式
	 public String sub(String x){
		 if(x.equals("")||x==null){
			 return "无";
		 }
			x=x.substring(0,x.indexOf(("\u2103")));
			return x;
		}
	 
	 //检查字符串是否为空
	 public String check(String x){
		 if(x.equals("")||x==null){
			 return "暂无";
		 }
			return x;
		}
	 
	 //判断天气情况，设置图片
	 public void type_image(String type,ImageView imageView){
		 if (type.equals("晴")) {
			    imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.qing));
			} else if (type.equals("多云")||type.equals("阴")) {
				imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.duoyun));
			} else if (type.equals("小雨") || type.equals("中雨") || type.equals("大雨")
					|| type.equals("雨")||type.equals("阵雨")||type.equals("暴雨")) {
				imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.yu));
			} else if (type.equals("小雪") || type.equals("中雪") || type.equals("大雪")
					|| type.equals("雪")) {
				imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.xue));
			} else if (type.equals("冰雹")) {
				imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.bingbao));
			} else if (type.equals("雷阵雨")) {
				imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.leizhenyu));
			} else {
				imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.other));
			}
	 }
}
