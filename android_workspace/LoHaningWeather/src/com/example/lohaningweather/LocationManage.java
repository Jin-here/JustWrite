package com.example.lohaningweather;

import java.util.ArrayList;
import java.util.List;

import com.example.lohaningweather.SlideCutListView.RemoveDirection;
import com.example.lohaningweather.SlideCutListView.RemoveListener;
import com.weather.sql.dao.CityDAO;
import com.weather.sql.dao.DBOpenHelper;
import com.weather.sql.model.Tb_city;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LocationManage extends Activity implements OnClickListener,RemoveListener{
	private ImageView addLocation;
	private SlideCutListView listView;
	private List arrayList=null; 
	private ArrayAdapter adapter;
	private ImageView bt_remove;
	private ActionBar mActionBar;
	private ImageView action_back;
	private ImageView action_remove;
	private TextView dingwei_name;
	
	int requestCode=0;
	public static LocationManage f=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locationmanage);
		init();
		f=this;
		
		adapter=new ArrayAdapter(this,R.layout.list_style,R.id.tv_location,arrayList);
		listView.setAdapter(adapter);
		addLocation.setOnClickListener(this);
		listView.setRemoveListener(this);  //删除监听
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			//列表点击事件
			}
		});
		
		
		//标题栏设置
		mActionBar=getActionBar();
		C c=new C();
		c.getAction_Bar(R.layout.location_actionbar, mActionBar);
		action_back=(ImageView) mActionBar.getCustomView().findViewById(R.id.action_back);
 		action_remove=(ImageView) mActionBar.getCustomView().findViewById(R.id.action_remove);
		action_back.setOnClickListener(this);
 		action_remove.setOnClickListener(this);
 		
 		//获取定位的地址名
 		SharedPreferences sharedPreferences= getSharedPreferences("test",
				Activity.MODE_PRIVATE); 
		String current_location=sharedPreferences.getString("current_location", "");
		String current_location_detail=sharedPreferences.getString("current_location_detail","");
		dingwei_name.setText(current_location_detail);
	}

	private void init(){
		listView=(SlideCutListView) findViewById(R.id.listView);
		addLocation=(ImageView) findViewById(R.id.addLocation);
		dingwei_name=(TextView) findViewById(R.id.dingwei_name);
		arrayList=new ArrayList();
		getData();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.addLocation:
			Intent intent=new Intent(this,switchLocation.class);
			startActivityForResult(intent, requestCode);
		case R.id.action_back:	
			this.finish();
		case R.id.action_remove:
			action_remove.setVisibility(View.INVISIBLE);
		}
		
		 	
	}
	
    //从数据库中获取数据
	private void getData(){
		   CityDAO cityDAO=new CityDAO(LocationManage.this);
		   List<Tb_city> strInfo=cityDAO.getScrollData();
		   for(Tb_city tb_city:strInfo){
			   arrayList.add(String.valueOf(tb_city.getCity()));
		   }
	}

	private void showInfo(String cityName){
		CityDAO cityDAO=new CityDAO(LocationManage.this);
		cityDAO.delete(cityName);
		adapter.notifyDataSetChanged();
		
	}
	
	//滑动删除之后的回调方法
		@Override
		public void removeItem(RemoveDirection direction, int position) {
			showInfo(adapter.getItem(position).toString());
			adapter.remove(adapter.getItem(position));
		}
		
		

}
