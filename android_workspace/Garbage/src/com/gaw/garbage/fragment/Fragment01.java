package com.gaw.garbage.fragment;

import com.gaw.garbage.R;
import com.gaw.garbage.activity.CatalogActivity;
import com.gaw.garbage.adapter.MyCursorAdapter;
import com.gaw.garbage.db.MySQLiteHelper;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

public class Fragment01 extends ListFragment {
	public static final String TAG = "FRAGMENT01";

	private MySQLiteHelper mysqlitehelper;
	private CatalogActivity catalogactivity;
	
	private ListView listview;

	public Fragment01() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		catalogactivity = (CatalogActivity) getActivity();
		mysqlitehelper = new MySQLiteHelper(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.rootcatalog, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		listview = this.getListView();
		
		// 判断mainactivity中的garbage的filename;
		// 空:getRootCatalog();
		// 不为空:getCatalog();
		String filename = Fragment00.garbage.getFilename();
		if ("".equals(filename)) {
			setListAdapter(new MyCursorAdapter(this,getActivity(),
					mysqlitehelper.getRootCatalog()));
		} else {
			setListAdapter(new MyCursorAdapter(this,getActivity(),
					mysqlitehelper.getCatalog(filename)));
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	public void freshCatalog(Cursor c){
		//改变filename名称
		//若为空，则getRootCatalog()
		//否则，则getCatalog(filename)
		//notifydatasetchanged()
		if (c == null){
			//进行处理
		}
		MyCursorAdapter adapter = new MyCursorAdapter(this, getActivity(), c);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

}
