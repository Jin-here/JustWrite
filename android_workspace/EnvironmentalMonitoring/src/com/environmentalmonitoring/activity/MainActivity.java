package com.environmentalmonitoring.activity;



import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

import com.environmentalmonitoring.adapter.Tab00Adapter;
import com.environmentalmonitoring.bean.MachineStatus;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.crash.CrashHandler;
import com.environmentalmonitoring.db.ClientApp;
import com.environmentalmonitoring.db.MySQLiteOpenHelper;
import com.environmentalmonitoring.dialog.ExitDialog;
import com.environmentalmonitoring.dialog.LoadingDialog;
import com.environmentalmonitoring.dialog.NoNetDialog;
import com.environmentalmonitoring.dialog.SetValueDialogFragment;
import com.environmentalmonitoring.fragment.Tab00Fragment;
import com.environmentalmonitoring.fragment.Tab00Fragment.OnLongClickListener;
import com.environmentalmonitoring.fragment.Tab01Fragment;
import com.environmentalmonitoring.fragment.Tab02Fragment;
import com.environmentalmonitoring.fragment.Tab03Fragment;
import com.environmentalmonitoring.receiver.MyReceiver;
import com.environmentalmonitoring.request.BaseRequest;
import com.environmentalmonitoring.request.BaseRequest.OnSocketOverListener;
import com.environmentalmonitoring.request.GetDataRequest;
import com.environmentalmonitoring.thread.BaseThread;
import com.environmentalmonitoring.thread.GetStatusThread;
import com.environmentalmonitoring.util.DummyTabContent;

public class MainActivity extends FragmentActivity{
	private ClientApp app;
	public MyHandler mHandler = new MyHandler(this);
	private MyReceiver receiver;
	
	public BaseThread basethread;
	private int xMin = 0;
	//写加锁
	public boolean isEnableWrite = true;
	
	private TabHost mTabHost;
	private TextView mTitleTextView;	
	private TabWidget mTabWidget;
	
	private RelativeLayout mTabWidget00,mTabWidget01,mTabWidget02,mTabWidget03;
	private int mCurrentTab = Config.START_PAGE;
	
	private FragmentManager fM;
	private Tab00Fragment mTab00Fragment = new Tab00Fragment();
	private Tab01Fragment mTab01Fragment = new Tab01Fragment();
	private Tab02Fragment mTab02Fragment = new Tab02Fragment();
	private Tab03Fragment mTab03Fragment = new Tab03Fragment();
	
	private MachineStatus status = new MachineStatus();
	private ArrayList<Double> data = new ArrayList<Double>();
	private ArrayList<Double> sensor00_data = new ArrayList<Double>();
	private ArrayList<Double> sensor01_data = new ArrayList<Double>();
	private ArrayList<Double> sensor02_data = new ArrayList<Double>();
	private ArrayList<Double> sensor03_data = new ArrayList<Double>();
	private ArrayList<Double> sensor04_data = new ArrayList<Double>();
	private ArrayList<Double> sensor05_data = new ArrayList<Double>();
	
	// handler
	private static class MyHandler extends Handler {
		WeakReference<MainActivity> mActivity;

		MyHandler(MainActivity activity) {
			mActivity = new WeakReference<MainActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			MainActivity theActivity = mActivity.get();
			switch (msg.what) {
			case Config.REGIS_WHAT:
				if (msg.arg1 == 1){
					Toast.makeText(theActivity, theActivity.getString(R.string.register_success), Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(theActivity, theActivity.getString(R.string.register_failed), Toast.LENGTH_SHORT).show();
				}
				break;
			case Config.EXCEPTION_WHAT:
				break;
			case Config.TIME_OUT_WHAT:
				theActivity.timeOut();
				if (theActivity.mTab00Fragment.isVisible()) {
					theActivity.mTab00Fragment.notifyDataChanged();
				}
				break;
			case Config.GET_VALUE_WHAT:
				break;
			case Config.GET_STATUS_WHAT:
				Bundle bundle = msg.getData();
				theActivity.status.setStatus(
						bundle.getBoolean("machine00_status"),
						bundle.getBoolean("machine01_status"),
						bundle.getBoolean("machine02_status"),
						bundle.getBoolean("machine03_status"));
				break;
			case Config.EXIT_WHAT:
				break;
			}

		}
	}  
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		mTab00Fragment.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public void onLongClick(int position) {
				// TODO Auto-generated method stub
				mTab01Fragment.setChoice(position);
				mTabHost.setCurrentTab(1);
				
			}
		});
		app = (ClientApp) getApplication();
		//注册receiver
		if (receiver == null){
			receiver = new MyReceiver();  
			IntentFilter filter = new IntentFilter();  
			filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);         
			registerReceiver(receiver, filter);
		}		
		//开启获取实时数据线程
		inital_data();
		GetDataRequest getdatarequest = new GetDataRequest(MainActivity.this,data);
		getdatarequest.setOnSocketOverListener(new OnSocketOverListener() {
			
			@Override
			public void onBackSuccess(BaseRequest baserequest) {
				// TODO Auto-generated method stub
				//实时数据存入数据库
				MySQLiteOpenHelper helper = new MySQLiteOpenHelper(getBaseContext());
				helper.insert(data);
				//更新曲线数据
				refresh_chart_data();
				xMin++;
				//call数据变化
				if (mTab00Fragment.isVisible()){
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							mTab00Fragment.notifyDataChanged();
						}
					});
				}
				if (mTab01Fragment.isVisible() && !mTab01Fragment.isHistory()){
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							mTab01Fragment.updataChartView(xMin);
						}
					});
					
					
				}
			}
			
			@Override
			public void onBackFailed(BaseRequest baserequest) {
				// TODO Auto-generated method stub
				
			}
		});
		basethread = new BaseThread(getBaseContext(), getdatarequest,mHandler);
		basethread.start();
		
		//获取开关状态		
		//new GetStatusThread(mHandler).start();

		//初始化tabhost
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTitleTextView = (TextView) findViewById(R.id.title_text_view);		
		mTabWidget = (TabWidget)findViewById(android.R.id.tabs);
		
		createTabView();
		mTabHost.setup();
		initTab();
		mTabHost.setCurrentTab(mCurrentTab);
		//首次进入第一个tab
		fM = getSupportFragmentManager();
		setupEnvirFrag();
		mTitleTextView.setText(R.string.tabhost_tab00_title);
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				if (tabId.equals(Tab00Fragment.TAG) || tabId.equals(Tab01Fragment.TAG)){
					if (basethread.isPause()){
						basethread.unLockPause();
					}
				}
				//分离子窗体
				Tab00Fragment Tab00Fragmentt = (Tab00Fragment) fM.findFragmentByTag(Tab00Fragment.TAG);
				Tab01Fragment Tab01Fragmentt = (Tab01Fragment) fM.findFragmentByTag(Tab01Fragment.TAG);
				Tab02Fragment Tab02Fragmentt = (Tab02Fragment) fM.findFragmentByTag(Tab02Fragment.TAG);
				Tab03Fragment Tab03Fragmentt = (Tab03Fragment) fM.findFragmentByTag(Tab03Fragment.TAG);
				if(Tab00Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab00Fragmentt);
					mFT.commit();
					
				}
										
				if(Tab01Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab01Fragmentt);
					mFT.commit();
					
				}
					
				if(Tab02Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab02Fragmentt);
					mFT.commit();
					
				}
					
				if(Tab03Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab03Fragmentt);
					mFT.commit();
					
				}

				//根据tabid添加fragment				
				if(tabId.equals(Tab00Fragment.TAG)){					
					setupEnvirFrag();
				}else if(tabId.equals(Tab01Fragment.TAG)){	
					setupHistroyFrag();
				}else if(tabId.equals(Tab02Fragment.TAG)){	
					setupControlFrag();
				}else if(tabId.equals(Tab03Fragment.TAG)){	
					setupSettingFrag();
				}
				
				
			}
		});
		
		
	}
	
	/**
	 * 设置widget界面
	 */
	private void createTabView()
    {

         //创建"环境指标"的tab view
         mTabWidget00 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, mTabWidget, false);
         TextView tvTab1 = (TextView)mTabWidget00.getChildAt(1);
         ImageView ivTab1 = (ImageView)mTabWidget00.getChildAt(0);
         ivTab1.setBackgroundResource(R.drawable.widget00_icon);
         tvTab1.setText(R.string.widget_tab00_text);
         
         //创建"历史数据查询"的tab view
         mTabWidget01 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, mTabWidget, false);
         TextView tvTab2 = (TextView)mTabWidget01.getChildAt(1);
         ImageView ivTab2 = (ImageView)mTabWidget01.getChildAt(0);
         ivTab2.setBackgroundResource(R.drawable.widget01_icon);
         tvTab2.setText(R.string.widget_tab01_text);
         
         //创建"手动控制"的tab view
         mTabWidget02 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, mTabWidget, false);
         TextView tvTab3 = (TextView)mTabWidget02.getChildAt(1);
         ImageView ivTab3 = (ImageView)mTabWidget02.getChildAt(0);
         ivTab3.setBackgroundResource(R.drawable.widget02_icon);
         tvTab3.setText(R.string.widget_tab02_text);
         
         //创建"系统设置"的tab view
         mTabWidget03 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, mTabWidget, false);
         TextView tvTab4 = (TextView)mTabWidget03.getChildAt(1);
         ImageView ivTab4 = (ImageView)mTabWidget03.getChildAt(0);
         ivTab4.setBackgroundResource(R.drawable.widget03_icon);
         tvTab4.setText(R.string.widget_tab03_text);
    }
	
	
	private void initTab(){
    	//将"环境指标"的tab view绑定到主界面的Tab host上, 通过子窗体TAG进行关联
        TabHost.TabSpec tSpecEnvir = mTabHost.newTabSpec(Tab00Fragment.TAG);
        tSpecEnvir.setIndicator(mTabWidget00);        
        tSpecEnvir.setContent(new DummyTabContent(getBaseContext()));//添加一个简单的上下文环境
        mTabHost.addTab(tSpecEnvir);
        
        //将"历史数据查询"的tab view绑定到主界面的Tab host上, 通过子窗体TAG进行关联
        TabHost.TabSpec tSpecHistory = mTabHost.newTabSpec(Tab01Fragment.TAG);
        tSpecHistory.setIndicator(mTabWidget01);        
        tSpecHistory.setContent(new DummyTabContent(getBaseContext()));//添加一个简单的上下文环境
        mTabHost.addTab(tSpecHistory);
        
        //将"手动控制"的tab view绑定到主界面的Tab host上, 通过子窗体TAG进行关联
        TabHost.TabSpec tSpecControl = mTabHost.newTabSpec(Tab02Fragment.TAG);
        tSpecControl.setIndicator(mTabWidget02);      
        tSpecControl.setContent(new DummyTabContent(getBaseContext()));//添加一个简单的上下文环境
        mTabHost.addTab(tSpecControl);
        
        //将"系统设置"的tab view绑定到主界面的Tab host上, 通过子窗体TAG进行关联
        TabHost.TabSpec tSpecSet = mTabHost.newTabSpec(Tab03Fragment.TAG);
        tSpecSet.setIndicator(mTabWidget03);        
        tSpecSet.setContent(new DummyTabContent(getBaseContext()));//添加一个简单的上下文环境
        mTabHost.addTab(tSpecSet);
    }
	
	private void setupEnvirFrag(){
		FragmentTransaction mFT = fM.beginTransaction();
    	if(fM.findFragmentByTag(Tab00Fragment.TAG) == null){		
			mFT.add(R.id.realTabcontent, mTab00Fragment, Tab00Fragment.TAG);
			
		}else{
			mFT.attach(mTab00Fragment);						
		}
    	mFT.commit();
    	mTitleTextView.setText(R.string.tabhost_tab00_title);
    	
    }
	
	private void setupHistroyFrag(){
		FragmentTransaction mFT = fM.beginTransaction();		
    	if(fM.findFragmentByTag(Tab01Fragment.TAG) == null){
			mFT.add(R.id.realTabcontent, mTab01Fragment, Tab01Fragment.TAG);						
		}else{
			mFT.attach(mTab01Fragment);						
		}
    	mFT.commit();
    	mTitleTextView.setText(R.string.tabhost_tab01_title);
    	
    }
	
	private void setupControlFrag(){
		FragmentTransaction mFT = fM.beginTransaction();
    	if(fM.findFragmentByTag(Tab02Fragment.TAG) == null){
			mFT.add(R.id.realTabcontent, mTab02Fragment, Tab02Fragment.TAG);						
		}else{
			mFT.attach(mTab02Fragment);						
		}
    	mFT.commit();
    	mTitleTextView.setText(R.string.tabhost_tab02_title);
    
    }
	
	private void setupSettingFrag(){
		FragmentTransaction mFT = fM.beginTransaction();
    	if(fM.findFragmentByTag(Tab03Fragment.TAG) == null){
			mFT.add(R.id.realTabcontent, mTab03Fragment, Tab03Fragment.TAG);						
		}else{
			mFT.attach(mTab03Fragment);	
		}
    	mFT.commit();
    	mTitleTextView.setText(R.string.tabhost_tab03_title);
    	
    }
	
	public  ArrayList<Double> getData(){
		return data;
	}

	public MachineStatus getStatus(){
		System.out.println(status + "1");
		return status;
	}
	
	private void refresh_chart_data(){
		autoInsert(sensor00_data, data.get(0));
		autoInsert(sensor01_data, data.get(1));
		autoInsert(sensor02_data, data.get(2));
		autoInsert(sensor03_data, data.get(3));
		autoInsert(sensor04_data, data.get(4));
		autoInsert(sensor05_data, data.get(5));
	}
	
	private void autoInsert(ArrayList<Double> data,double item){
		data.add(0, item);
		data.remove(data.size()-1);
	}
	
	public  ArrayList<Double> getchart_data(int i){
		switch(i){
		case 0:
			return sensor00_data;
		case 1:
			return sensor01_data;
		case 2:
			return sensor02_data;
		case 3:
			return sensor03_data;
		case 4:
			return sensor04_data;
		case 5:
			return sensor05_data;
		}
		return null;
	}
	
	/**
	 * 用于tab00  跳转  tab01
	 * @return
	 */
	public Tab01Fragment getFragment01(){
		return this.mTab01Fragment;
	}
	
	/**
	 * 用于tab00  跳转  tab01
	 * @return
	 */
	public TabHost getTabHost(){
		return mTabHost;
	}
	
	/**
	 * 初始化data
	 */
	public void inital_data(){
		for (int i = 0;i < 6;i++){
			data.add((double) 7777777);
		}
		
		//曲线缓存2行数据
		for (int i = 0;i < 2*Config.CHART_MAX_POINT;i++){
			sensor00_data.add((double) 0);
			sensor01_data.add((double) 0);
			sensor02_data.add((double) 0);
			sensor03_data.add((double) 0);
			sensor04_data.add((double) 0);
			sensor05_data.add((double) 0);
		}
	}
	
	/**
	 * 退出程序提示框
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			new ExitDialog().show(getSupportFragmentManager(), ExitDialog.TAG);
		}
		return false;
	}
	
	public void timeOut(){
		while (!isEnableWrite);
		isEnableWrite = false;
		data.clear();
		for (int i = 0;i < 6;i++){
			data.add((double) 7777777);
		}
		isEnableWrite = true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		//分离子窗体
				Tab00Fragment Tab00Fragmentt = (Tab00Fragment) fM.findFragmentByTag(Tab00Fragment.TAG);
				Tab01Fragment Tab01Fragmentt = (Tab01Fragment) fM.findFragmentByTag(Tab01Fragment.TAG);
				Tab02Fragment Tab02Fragmentt = (Tab02Fragment) fM.findFragmentByTag(Tab02Fragment.TAG);
				Tab03Fragment Tab03Fragmentt = (Tab03Fragment) fM.findFragmentByTag(Tab03Fragment.TAG);
				if(Tab00Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab00Fragmentt);
					mFT.commit();
					
				}
										
				if(Tab01Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab01Fragmentt);
					mFT.commit();
					
				}
					
				if(Tab02Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab02Fragmentt);
					mFT.commit();
					
				}
					
				if(Tab03Fragmentt!=null){
					FragmentTransaction mFT = fM.beginTransaction();
					mFT.detach(Tab03Fragmentt);
					mFT.commit();
					
				}
		//super.onSaveInstanceState(outState);
		
	}
	
	/**
	 * 退出程序关闭线程、receiver
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		basethread.closeSocket();
		unregisterReceiver(receiver);
		MySQLiteOpenHelper helper = new MySQLiteOpenHelper(getBaseContext());
		helper.fresh(20000);
		System.out.println("destroy1");
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("onresume");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("onpause");
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("onstop");
	}
}
