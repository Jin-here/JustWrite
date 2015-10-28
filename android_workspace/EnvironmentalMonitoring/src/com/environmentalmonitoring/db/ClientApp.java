package com.environmentalmonitoring.db;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentManager;

import com.environmentalmonitoring.bean.SensorBean;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.crash.CrashHandler;
import com.environmentalmonitoring.dialog.NoNetDialog;


public class ClientApp extends Application{
	public static final String NAME = "MY_SHAREDPREFERENCES";
	
	private SharedPreferences mSP;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		//����������־
		new CrashHandler(getBaseContext());
				
		mSP = getSharedPreferences(NAME, Context.MODE_PRIVATE);
		
		inital_Sensor();
		
	}
	
	/**
	 * ��ʼ��Ĭ�Ϸ�ֵ,�����һ����ʾ,�Ժ�����ֶ�����
	 */
	private void inital_Sensor() {
		Editor e = mSP.edit();

		e.putFloat(Config.SENSOR00_MAX, Config.SENSOR00_MAX_VALUE);
		e.putFloat(Config.SENSOR00_MIN, Config.SENSOR00_MIN_VALUE);

		e.putFloat(Config.SENSOR01_MAX, Config.SENSOR01_MAX_VALUE);
		e.putFloat(Config.SENSOR01_MIN, Config.SENSOR01_MIN_VALUE);

		e.putFloat(Config.SENSOR02_MAX, Config.SENSOR02_MAX_VALUE);
		e.putFloat(Config.SENSOR02_MIN, Config.SENSOR02_MIN_VALUE);

		e.putFloat(Config.SENSOR03_MAX, Config.SENSOR03_MAX_VALUE);
		e.putFloat(Config.SENSOR03_MIN, Config.SENSOR03_MIN_VALUE);

		e.putFloat(Config.SENSOR04_MAX, Config.SENSOR04_MAX_VALUE);
		e.putFloat(Config.SENSOR04_MIN, Config.SENSOR04_MIN_VALUE);

		e.putFloat(Config.SENSOR05_MAX, Config.SENSOR05_MAX_VALUE);
		e.putFloat(Config.SENSOR05_MIN, Config.SENSOR05_MIN_VALUE);

		e.commit();
	}
	
	/**
	 * ��ȡ��Ӧ��������ֵ���ֵ
	 * @param i
	 * @return
	 */
	public double getSensorMax(int i){
		switch (i){
		case 0:
			return mSP.getFloat(Config.SENSOR00_MAX, Config.SENSOR00_MAX_VALUE);			
		case 1:
			return mSP.getFloat(Config.SENSOR01_MAX, Config.SENSOR01_MAX_VALUE);
		case 2:
			return mSP.getFloat(Config.SENSOR02_MAX, Config.SENSOR02_MAX_VALUE);
		case 3:
			return mSP.getFloat(Config.SENSOR03_MAX, Config.SENSOR03_MAX_VALUE);
		case 4:
			return mSP.getFloat(Config.SENSOR04_MAX, Config.SENSOR04_MAX_VALUE);
		case 5:
			return mSP.getFloat(Config.SENSOR05_MAX, Config.SENSOR05_MAX_VALUE);
		}
		return -1;
	}
	
	/**
	 * ���÷�ֵ���ֵ
	 * @param i
	 * @param value
	 */
	public void SetSensorMax(int i,float value){
		switch (i){
		case 0:
			setValue(Config.SENSOR00_MAX, value);			
		case 1:
			setValue(Config.SENSOR01_MAX, value);
		case 2:
			setValue(Config.SENSOR02_MAX, value);
		case 3:
			setValue(Config.SENSOR03_MAX, value);
		case 4:
			setValue(Config.SENSOR04_MAX, value);
		case 5:
			setValue(Config.SENSOR05_MAX, value);
		}
	}
	
	/**
	 * ���÷�ֵ��Сֵ
	 * @param i
	 * @param value
	 */
	public void SetSensorMin(int i,float value){
		switch (i){
		case 0:
			setValue(Config.SENSOR00_MIN, value);			
		case 1:
			setValue(Config.SENSOR01_MIN, value);
		case 2:
			setValue(Config.SENSOR02_MIN, value);
		case 3:
			setValue(Config.SENSOR03_MIN, value);
		case 4:
			setValue(Config.SENSOR04_MIN, value);
		case 5:
			setValue(Config.SENSOR05_MIN, value);
		}
	}
	
	/**
	 * ��ȡ��Ӧ��ֵ��Сֵ
	 * @param i
	 * @return
	 */
	public double getSensorMin(int i){
		switch (i){
		case 0:
			return mSP.getFloat(Config.SENSOR00_MIN, Config.SENSOR00_MIN_VALUE);			
		case 1:
			return mSP.getFloat(Config.SENSOR01_MIN, Config.SENSOR01_MIN_VALUE);
		case 2:
			return mSP.getFloat(Config.SENSOR02_MIN, Config.SENSOR02_MIN_VALUE);
		case 3:
			return mSP.getFloat(Config.SENSOR03_MIN, Config.SENSOR03_MIN_VALUE);
		case 4:
			return mSP.getFloat(Config.SENSOR04_MIN, Config.SENSOR04_MIN_VALUE);
		case 5:
			return mSP.getFloat(Config.SENSOR05_MIN, Config.SENSOR05_MIN_VALUE);
		}
		return -1;
	}
	
	/**
	 * �ж��Ƿ��һ�ν������������  չʾ����
	 * @return
	 */
	public boolean isFirstRun(){
		return mSP.getBoolean("isfirstrun", true);
	}
	
	/**
	 * ��һ�ν�����Ʒ�
	 */
	public void setFirstRun(){
		Editor e = mSP.edit();
		e.putBoolean("isfirstrun", false);
		e.commit();
	}
	
	/**
	 * �����û����������´ε�¼
	 */
	public void setUsername(String username){
		Editor e = mSP.edit();
		e.putString(Config.USERNAME, username);
		e.commit();
	}
	
	public String getUsername(){
		return mSP.getString(Config.USERNAME, "");
	}
	
	/**
	 * �����ס���룬�����sp
	 * @param username
	 */
	public void setPassword(String password){
		Editor e = mSP.edit();
		e.putString(Config.PASSWORD, password);
		e.commit();
	}
	
	public String getPassword(){
		return mSP.getString(Config.PASSWORD, "");
	}
	
	/**
	 * �����Ƿ��ס����
	 * @param username
	 */
	public void setRecode(boolean isrecode){
		Editor e = mSP.edit();
		e.putBoolean(Config.RECODE, isrecode);
		e.commit();
	}
	
	public boolean isRecode(){
		return mSP.getBoolean(Config.RECODE, false);
	}
	
	/**
	 * ����IP��ַ�������浽����
	 * @param ip
	 */
	public void SetServerIp(String ip){
		Editor e = mSP.edit();
		e.putString(Config.SERVER_IP, ip);
		e.commit();
		
		Config.HTTP_SERVER_IP = ip;
	}
	
	public String getServerIp(){
		return mSP.getString(Config.SERVER_IP, Config.HTTP_SERVER_IP);
	}
	
	/**
	 * ���ö˿ں�,�����浽����
	 * @param ip
	 */
	public void SetServerPort(int port){
		Editor e = mSP.edit();
		e.putInt(Config.SERVER_PORT, port);
		e.commit();
		
		Config.HTTP_SERVER_PORT = port;
	}
	
	public int getServerPort(){
		return mSP.getInt(Config.SERVER_PORT, Config.HTTP_SERVER_PORT);
	}
	
	/**
	 * �ڲ�����
	 * @param key
	 * @param value
	 */
	private void setValue(String key,float value){
		Editor e = mSP.edit();		
		e.putFloat(key, value);
		e.commit();
	}
	
	
}
