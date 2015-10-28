package com.environmentalmonitoring.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.adapter.Tab00Adapter;
import com.environmentalmonitoring.request.BaseRequest;
import com.environmentalmonitoring.request.BaseRequest.OnSocketOverListener;

public class Tab00Fragment extends Fragment {
	public static final String TAG = "TAB00FRAGMENT";
	
	private Tab00Adapter adapter;
	private MainActivity activity;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = (MainActivity) getActivity();
		
		adapter = new Tab00Adapter(activity, R.id.sensor_grid_view, R.layout.sensor_grid_item, activity.getData());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View layout = inflater.inflate(R.layout.tab00_fragment, container,false);
		GridView gV = (GridView) layout.findViewById(R.id.sensor_grid_view);
		
		gV.setAdapter(adapter);
		
		gV.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				onLongClickListener.onLongClick(position);
				return true;
			}
		});
		return layout;
	}
	
	public void notifyDataChanged(){
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * ³¤°´¼àÌýÆ÷
	 * @author Administrator
	 *
	 */
	public interface OnLongClickListener{
		void onLongClick(int position);
	}

	protected OnLongClickListener onLongClickListener;

	public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
		this.onLongClickListener = onLongClickListener;
	}
}
