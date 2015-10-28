package com.environmentalmonitoring.fragment;

import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.ClientApp;
import com.environmentalmonitoring.dialog.SetValueDialogFragment;

public class Tab03Fragment extends Fragment {
	public static final String TAG = "TAB03FRAGMENT";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.tab03_fragment, container,false);
		LinearLayout language_layout = (LinearLayout) layout.findViewById(R.id.language_layout);
		
		
		LinearLayout pm25 = (LinearLayout) layout.findViewById(R.id.pm25_setting_layout);
		LinearLayout co2 = (LinearLayout) layout.findViewById(R.id.co2_setting_layout);
		LinearLayout light = (LinearLayout) layout.findViewById(R.id.light_setting_layout);
		LinearLayout air = (LinearLayout) layout.findViewById(R.id.air_setting_layout);
		LinearLayout soil = (LinearLayout) layout.findViewById(R.id.soil_setting_layout);
		LinearLayout air_tmper = (LinearLayout) layout.findViewById(R.id.air_tmper_setting_layout);
		
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClientApp app = (ClientApp) getActivity().getApplication();
				switch (v.getId()){
				case R.id.pm25_setting_layout:
					new SetValueDialogFragment(true, true, getString(R.string.pm25_title), app.getSensorMax(0), app.getSensorMin(0),0).show(getActivity().getSupportFragmentManager(), SetValueDialogFragment.TAG);
					break;
				case R.id.co2_setting_layout:
					new SetValueDialogFragment(true, true, getString(R.string.co2_title), app.getSensorMax(1), app.getSensorMin(1),1).show(getActivity().getSupportFragmentManager(), SetValueDialogFragment.TAG);
					break;
				case R.id.light_setting_layout:
					new SetValueDialogFragment(true, true, getString(R.string.light_title), app.getSensorMax(2), app.getSensorMin(2),2).show(getActivity().getSupportFragmentManager(), SetValueDialogFragment.TAG);
					break;
				case R.id.air_setting_layout:
					new SetValueDialogFragment(true, true, getString(R.string.air_title), app.getSensorMax(3), app.getSensorMin(3),3).show(getActivity().getSupportFragmentManager(), SetValueDialogFragment.TAG);
					break;
				case R.id.soil_setting_layout:
					new SetValueDialogFragment(true, true, getString(R.string.soil_title), app.getSensorMax(4), app.getSensorMin(4),4).show(getActivity().getSupportFragmentManager(), SetValueDialogFragment.TAG);
					break;
				case R.id.air_tmper_setting_layout:
					new SetValueDialogFragment(true, true, getString(R.string.air_tmper_title), app.getSensorMax(5), app.getSensorMin(5),5).show(getActivity().getSupportFragmentManager(), SetValueDialogFragment.TAG);
					break;
				}
			}
		};
		
		pm25.setOnClickListener(listener);
		co2.setOnClickListener(listener);
		light.setOnClickListener(listener);
		air.setOnClickListener(listener);
		soil.setOnClickListener(listener);
		air_tmper.setOnClickListener(listener);
		
		language_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentTransaction fT = getActivity().getSupportFragmentManager().beginTransaction();
				fT.detach(Tab03Fragment.this);
				fT.commit();
				/*MainActivity activity = (MainActivity) getActivity();
				activity.getTabHost().setCurrentTab(0);*/
				Intent intent=new Intent(Settings.ACTION_LOCALE_SETTINGS);
				startActivity(intent);
			}
		});
		/*language_setting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked){
					if (!isZh()){
						switchLanguage(Locale.CHINESE);
					}
				}else{
					if (isZh()){
						switchLanguage(Locale.ENGLISH);
					}
				}
			}
		});*/
		return layout;
	}
	
	/*
	private void switchLanguage(Locale locale) {
        Configuration config = getResources().getConfiguration();// 获得设置对象
        Resources resources = getResources();// 获得res资源对象
        DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
        config.locale = locale; // 简体中文
        resources.updateConfiguration(config, dm);
        
        MainActivity activity = (MainActivity) getActivity();
        activity.getTabHost().setCurrentTab(0);
	}
	
	private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }*/
}
