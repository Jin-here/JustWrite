package com.environmentalmonitoring.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.bean.MachineStatus;
import com.environmentalmonitoring.request.BaseRequest;
import com.environmentalmonitoring.request.BaseRequest.OnSocketOverListener;
import com.environmentalmonitoring.request.MachineControlRequest;
import com.environmentalmonitoring.thread.BaseThread;
import com.environmentalmonitoring.thread.TestThread;

public class Tab02Fragment extends Fragment {
	public static final String TAG = "TAB02FRAGMENT";
	
	private Handler mHandler;
	private MainActivity activity;
	
	private MachineControlRequest machinecontrolrequest;
	private BaseThread basethread;
	
	private MachineStatus status;
	private ArrayList<ControllerUiData> data;
	private boolean[] flag = new boolean[]{false,false,false,false};
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = (MainActivity)getActivity();
		mHandler = activity.mHandler;
		
		status = activity.getStatus();
		
		flag[0] = status.getStatus(0);
		flag[1] = status.getStatus(1);
		flag[2] = status.getStatus(2);
		flag[3] = status.getStatus(3);
		
		data = new ArrayList<Tab02Fragment.ControllerUiData>();
		data.add(new ControllerUiData(R.string.blower, R.drawable.blower_on, R.drawable.blower_off));
		data.add(new ControllerUiData(R.string.road_lamp, R.drawable.roadlamp_on, R.drawable.roadlamp_off));
		data.add(new ControllerUiData(R.string.water_pump, R.drawable.water_pump_on, R.drawable.water_pump_off));
		data.add(new ControllerUiData(R.string.buzzer, R.drawable.buzzer_on, R.drawable.buzzer_off));
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		System.out.println("on_create_view");
		View layout = inflater.inflate(R.layout.tab02_fragment, container,false);
		GridView gV = (GridView) layout.findViewById(R.id.controller_grid_view);
		gV.setAdapter(new MyAdapter(getActivity(), R.id.controller_grid_view,R.layout.controller_item, data));
		//gV.setAdapter(getActivity());
		return layout;
	}
	
	private class ControllerUiData{	
		public ControllerUiData(int name, int onIcon, int offIcon) {
			super();
			this.name = name;
			this.onIcon = onIcon;
			this.offIcon = offIcon;
		}
		public int name;		
		public int onIcon;
		public int offIcon;
	}
	
	
	private class MyAdapter extends ArrayAdapter<ControllerUiData>{

		private ArrayList<ControllerUiData> data;
		private Context mContext;
		public MyAdapter(Context context, int resource, int textViewResourceId,
				List<ControllerUiData> objects) {
			super(context, resource, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
			this.data = (ArrayList<ControllerUiData>) objects;
			this.mContext = context;
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			if(convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.controller_item, parent, false);
	        }
			TextView name_text_view = (TextView) convertView.findViewById(R.id.name_text_view);
			
			final TextView status_text_view = (TextView) convertView.findViewById(R.id.status_text_view);
			
			final ImageView controller_icon = (ImageView) convertView.findViewById(R.id.controller_icon);
			
			final TextView open_text_view = (TextView) convertView.findViewById(R.id.open_text_view);
			
			
			
			/**************初始化machine状态**************************/
			name_text_view.setText(data.get(position).name);
			if (flag[position]){
				status_text_view.setText(getString(R.string.current_status_open));
				controller_icon.setImageResource(data.get(position).onIcon);
				open_text_view.setText(getString(R.string.close));
			}else{
				controller_icon.setImageResource(data.get(position).offIcon);
			}
			/**************初始化machine状态**************************/
			
			machinecontrolrequest = new MachineControlRequest();
			machinecontrolrequest.setOnSocketOverListener(new OnSocketOverListener() {
				
				@Override
				public void onBackSuccess(BaseRequest baserequest) {
					// TODO Auto-generated method stub
					basethread.closeSocket();
				}
				
				@Override
				public void onBackFailed(BaseRequest baserequest) {
					// TODO Auto-generated method stub
					basethread.closeSocket();
				}
			});
			basethread = new BaseThread(getActivity(), machinecontrolrequest, mHandler);
			
			open_text_view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub					
					if (!flag[position]){
						status_text_view.setText(getString(R.string.current_status_open));
						controller_icon.setImageResource(data.get(position).onIcon);
						open_text_view.setText(getString(R.string.close));
						flag[position] = !flag[position];
						/************发送开请求*************************/
						machinecontrolrequest.setControl(position, true);
						activity.basethread.setPause();
						new TestThread(machinecontrolrequest).start();
						//basethread.start();
					}else{
						status_text_view.setText(getString(R.string.current_status_close));
						controller_icon.setImageResource(data.get(position).offIcon);
						open_text_view.setText(getString(R.string.open));
						flag[position] = !flag[position];
						/************发送关请求*************************/
						machinecontrolrequest.setControl(position, false);
						activity.basethread.setPause();
						new TestThread(machinecontrolrequest).start();
						//basethread.start();
					}
				}
			});
			
			return convertView;
		}

		
		
	}
	
	
}
