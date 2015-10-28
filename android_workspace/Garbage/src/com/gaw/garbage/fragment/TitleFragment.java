package com.gaw.garbage.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaw.garbage.R;
import com.gaw.garbage.util.DateTools;

public class TitleFragment extends Fragment {
	public static final String TAG = "TITLEFRAGMENT";
	
	private TextView catalog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.titlefragment, container, false);
		catalog = (TextView) view.findViewById(R.id.catalog);
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		String filename = Fragment00.garbage.getFilename();
		if ("".equals(filename)){
			catalog.setText("/");
		}else{
			catalog.setText("/" + filename + "/");
		}
		
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
