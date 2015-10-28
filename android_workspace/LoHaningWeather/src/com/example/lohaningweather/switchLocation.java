package com.example.lohaningweather;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.weather.sql.dao.CityDAO;
import com.weather.sql.dao.DBOpenHelper;
import com.weather.sql.model.Tb_city;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class switchLocation extends Activity implements OnItemClickListener,OnClickListener {

	private ImageView iv_search;
	private GridView city_list;
	private String []city;
	private ArrayList<String> arrayList;
	private ArrayAdapter adapter;
	private Intent intent;
	private ActionBar mActionbar;
	private ImageView action_back;
	private AutoCompleteTextView search_location;
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	int resultCode=0;
	public static switchLocation f=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switchlocation);
		init();
		f=this;
		//�Զ�ƥ���
		String strName=search_location.getText().toString();
		helper=new DBOpenHelper(switchLocation.this);
		db=helper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select _id,name"
				+ " from city where name like'"+strName+"%'", null);
		if(cursor.moveToNext()){
			strName=cursor.getString(cursor.getColumnIndex("_id"));
		}
		myCursorAdapter cursorAdapter=new myCursorAdapter(switchLocation.this, cursor,false);
		
		search_location.setThreshold(1);
		search_location.setCompletionHint("û�и���������");
		search_location.setAdapter(cursorAdapter);

		
		//ƥ��
		for(int i=0;i<city.length;i++){
		arrayList.add(city[i]);
		}
		adapter=new ArrayAdapter(this,R.layout.gridview_city_style,R.id.city,arrayList);
		city_list.setAdapter(adapter);
		
		//���������ͼ����¼�
		city_list.setOnItemClickListener(this);
		iv_search.setOnClickListener(this);
		//�����������
		mActionbar=getActionBar();
		C c=new C();
		c.getAction_Bar(R.layout.switch_location_actionbar,mActionbar);
		action_back=(ImageView) mActionbar.getCustomView().findViewById(R.id.action_back_s);
		action_back.setOnClickListener(this);
		
	}
	
	private void init(){
		search_location=(AutoCompleteTextView) findViewById(R.id.search_location);
		iv_search=(ImageView) findViewById(R.id.iv_search);
		city_list=(GridView) findViewById(R.id.city_list);
		arrayList=new ArrayList<String>();
		
		city=new String []{"������","�����","�Ϻ���","������","������","������","������"
				,"��������","֣����","�人��","��ɳ��","������","������","�Ͼ���","������","������"
				,"������","������","������","������","������","�ൺ��","������","������","������","�ɶ���"
				,"������"};

	}
	
	//��������
	private void sendData(String data){
		intent=new Intent(this,LocationManage.class);
		intent.putExtra("city", data);
		this.setResult(resultCode, intent);
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		   CityDAO cityDAO=new CityDAO(switchLocation.this);
		   
		   //������ݿ�������Ӹó��У��������
		     if( cityDAO.find_city(city[position])!=null){
		    	 Toast.makeText(switchLocation.this, "�����ظ���ӳ���Ŷ��", Toast.LENGTH_SHORT).show();
		     }else{    
		        Tb_city tb_city=new Tb_city(cityDAO.getMaxId()+1,city[position]);
		        cityDAO.add(tb_city);
		      //��ת
		        Intent intnet=new Intent(switchLocation.this,MainActivity.class);
		        startActivity(intnet);  
		        
		      }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.action_back_s:
			this.finish();
		    break;
		case R.id.iv_search:
			CityDAO cityDAO=new CityDAO(switchLocation.this);
			String name=search_location.getText().toString();
			//�ж��Ƿ��ǺϷ��ĳ��������Ϸ�����������ݿ���
			if(name.equals(cityDAO.cfind(name))){
				 //������ݿ�������Ӹó��У��������
			     if( cityDAO.find_city(name)!=null){
			    	 Toast.makeText(switchLocation.this, "�����ظ���ӳ���Ŷ��", Toast.LENGTH_SHORT).show();
			     }else{    
			        Tb_city tb_city=new Tb_city(cityDAO.getMaxId()+1,name);
			        cityDAO.add(tb_city);
			      //��ת
			        Intent intnet=new Intent(switchLocation.this,MainActivity.class);
			        startActivity(intnet);  
			        
			      }
			}
			break;
		}
	}
	
	private class myCursorAdapter extends CursorAdapter{
	 
	private LayoutInflater layoutInflater;
	private TextView auto_name;
	private Cursor cursor;
	public myCursorAdapter(Context context, Cursor c,boolean autoRequery) {
			super(context, c);
			// TODO Auto-generated constructor stub
			layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		}
	@Override
		public CharSequence convertToString(Cursor cursor) {
			// TODO Auto-generated method stub
			return cursor == null ? "" : cursor.getString(cursor.getColumnIndex("name"));
		}

	@Override
	public void bindView(View v, Context arg1, Cursor cursor) {
		// TODO Auto-generated method stub
		((TextView)v).setText(cursor.getString(cursor.getColumnIndex("name")));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		TextView view=(TextView)layoutInflater.inflate(android.R.layout.simple_list_item_1, null); 
		return view;
	}

    @Override
    	public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
    		// TODO Auto-generated method stub
    		//return super.runQueryOnBackgroundThread(constraint);
    		cursor=db.rawQuery("select *"
    				+ " from city where name like'"+constraint+"%'", null);
    		return cursor;
    	}
	
	}
}
