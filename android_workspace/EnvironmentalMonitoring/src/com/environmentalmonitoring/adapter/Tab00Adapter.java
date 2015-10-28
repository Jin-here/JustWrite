package com.environmentalmonitoring.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.ClientApp;
import com.environmentalmonitoring.thread.GetStatusThread;

public class Tab00Adapter extends ArrayAdapter<Double> {
	private ClientApp app;
	private Context mContext;
	private ArrayList<Double> data;
	
	private int[] name = new int[]{R.string.pm25_title,R.string.co2_title
			,R.string.light_title,R.string.air_title
			,R.string.soil_title,R.string.air_tmper_title};
	/*private int[] warn = new int[]{R.string.warn,R.string.warn
			,R.string.warn,R.string.warn
			,R.string.warn,R.string.warn};*/
	private String[] danwei = new String[]{"ppb","ug/m3","%","lx","℃","db"};
	
	
	public Tab00Adapter(Context context, int resource, int textViewResourceId,
			List<Double> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.data = (ArrayList<Double>) objects;
		app = (ClientApp) mContext.getApplicationContext();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.sensor_grid_item, parent,
					false);
		}
		RelativeLayout sensor_gird_item_layout = (RelativeLayout) convertView
				.findViewById(R.id.sensor_gird_item_layout);
		TextView sensor_name_text = (TextView) convertView
				.findViewById(R.id.sensor_name_text);
		TextView status_warn_text = (TextView) convertView
				.findViewById(R.id.status_warn_text);
		TextView set_value_text = (TextView) convertView
				.findViewById(R.id.set_value_text);
		TextView sensor_value_text = (TextView) convertView
				.findViewById(R.id.sensor_value_text);

		sensor_name_text.setText(name[position]);
		// status_warn_text.setText(warn[position]);
		// 获取阀值，保留2位小数
		double minValue = app.getSensorMin(position);
		minValue = Math.round(minValue * 100) / 100.00;
		double maxValue = app.getSensorMax(position);
		maxValue = Math.round(maxValue * 100) / 100.00;
		double value = Math.round((data.get(position)) * 100) / 100.00;

		set_value_text.setText(minValue + "~" + maxValue + "("
				+ danwei[position] + ")");

		if (value == 7777777) {
			sensor_value_text.setText("");
		} else if (position == 5){
			sensor_value_text.setText(value>0?"有":"无");
		}else{
			sensor_value_text.setText(String.valueOf(value));
			// 判断，若超出阀值(...),背景变红，发出通知（notifacation和短信）
			if (judge(minValue, maxValue, value)) {
				sensor_gird_item_layout.setBackgroundColor(mContext
						.getResources().getColor(R.color.red));
				if (value < minValue) {
					status_warn_text.setText(R.string.warn_small);
				} else {
					status_warn_text.setText(R.string.warn_big);
				}
				// 发出通知（notifacation和短信）
			} else {
				sensor_gird_item_layout.setBackgroundColor(mContext
						.getResources().getColor(R.color.green));
				status_warn_text.setText(R.string.warn_normal);
			}
		}

		return convertView;
	}
	
	private boolean judge(double min,double max,double value){
		if (value < min 
				|| value > max){
			return true;
		}
		return false;
	}

}
