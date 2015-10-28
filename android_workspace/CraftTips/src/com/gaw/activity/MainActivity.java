package com.gaw.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaw.adapter.MyAdapter;
import com.gaw.db.MyHelper;
import com.gaw.stastics.THING;


public class MainActivity extends Activity {
	
	private MyHelper helper = null;
	private MyAdapter adapter = null;
	private AutoCompleteTextView auto = null;
	private ImageView pic11 = null;
	private ImageView pic12 = null;
	private ImageView pic13 = null;
	private ImageView pic21 = null;
	private ImageView pic22 = null;
	private ImageView pic23 = null;
	private ImageView pic31 = null;
	private ImageView pic32 = null;
	private ImageView pic33 = null;
	private Button help = null;
	
	private int[] hechengbiaopic = new int[]{R.drawable.kong,R.drawable.kong,R.drawable.kong,
			R.drawable.kong,R.drawable.kong,R.drawable.kong,
			R.drawable.kong,R.drawable.kong,R.drawable.kong};
	private final int[] hechengbiaoid = new int[]{R.id.pic11,R.id.pic12,R.id.pic13,
			R.id.pic21,R.id.pic22,R.id.pic23,
			R.id.pic31,R.id.pic32,R.id.pic33};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		auto = (AutoCompleteTextView) findViewById(R.id.auto);
		pic11 = (ImageView) findViewById(R.id.pic11);
		pic12 = (ImageView) findViewById(R.id.pic12);
		pic13 = (ImageView) findViewById(R.id.pic13);
		pic21 = (ImageView) findViewById(R.id.pic21);
		pic22 = (ImageView) findViewById(R.id.pic22);
		pic23 = (ImageView) findViewById(R.id.pic23);
		pic31 = (ImageView) findViewById(R.id.pic31);
		pic32 = (ImageView) findViewById(R.id.pic32);
		pic33 = (ImageView) findViewById(R.id.pic33);
		help = (Button) findViewById(R.id.help);
		
		
		helper = new MyHelper(MainActivity.this);
		
		SQLiteDatabase sd  = helper.getReadableDatabase();
		Cursor c = sd.query(THING.TABLE_NAME, null, THING.IS_SHOW + "=1", null, null, null, null);
		
		adapter = new MyAdapter(MainActivity.this, c, true); 
		auto.setAdapter(adapter);
		
		auto.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
				TextView tV = (TextView) view.findViewById(R.id.name);
				Cursor c = helper.querybyname((String) tV.getText());
				while (c.moveToNext()){
					int p11 = c.getInt(c.getColumnIndex(THING.PIC11));
					int p12 = c.getInt(c.getColumnIndex(THING.PIC12));
					int p13 = c.getInt(c.getColumnIndex(THING.PIC13));
					int p21 = c.getInt(c.getColumnIndex(THING.PIC21));
					int p22 = c.getInt(c.getColumnIndex(THING.PIC22));
					int p23 = c.getInt(c.getColumnIndex(THING.PIC23));
					int p31 = c.getInt(c.getColumnIndex(THING.PIC31));
					int p32 = c.getInt(c.getColumnIndex(THING.PIC32));
					int p33 = c.getInt(c.getColumnIndex(THING.PIC33));
					
					pic11.setImageResource(p11);
					pic12.setImageResource(p12);
					pic13.setImageResource(p13);
					pic21.setImageResource(p21);
					pic22.setImageResource(p22);
					pic23.setImageResource(p23);
					pic31.setImageResource(p31);
					pic32.setImageResource(p32);
					pic33.setImageResource(p33);
					
					hechengbiaopic[0] = p11;
					hechengbiaopic[1] = p12;
					hechengbiaopic[2] = p13;
					hechengbiaopic[3] = p21;
					hechengbiaopic[4] = p22;
					hechengbiaopic[5] = p23;
					hechengbiaopic[6] = p31;
					hechengbiaopic[7] = p32;
					hechengbiaopic[8] = p33;
					
					c.close();
					
				}
			}
		});
		
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ImageView iV = (ImageView) v;
				int id = iV.getId();
				
				if (hechengbiaoid[0]==id){
					fresh(0);
				}else
				if (hechengbiaoid[1]==id){
					fresh(1);
				}else
				if (hechengbiaoid[2]==id){
					fresh(2);
				}else
				if (hechengbiaoid[3]==id){
					fresh(3);
				}else
				if (hechengbiaoid[4]==id){
					fresh(4);
				}else
				if (hechengbiaoid[5]==id){
					fresh(5);
				}else
				if (hechengbiaoid[6]==id){
					fresh(6);
				}else
				if (hechengbiaoid[7]==id){
					fresh(7);
				}else
				if (hechengbiaoid[8]==id){
					fresh(8);
				}
			}
		};
		
		pic11.setOnClickListener(listener);
		pic12.setOnClickListener(listener);
		pic13.setOnClickListener(listener);
		pic21.setOnClickListener(listener);
		pic22.setOnClickListener(listener);
		pic23.setOnClickListener(listener);
		pic31.setOnClickListener(listener);
		pic32.setOnClickListener(listener);
		pic33.setOnClickListener(listener);
		
		/*****************help****button*************************/
		help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,HelpActivity.class));
			}
		});
		/*****************help****button*************************/
	}
	
	private void fresh(int position){		
		Cursor c = helper.querybypic(hechengbiaopic[position]);
		while (c.moveToNext()){
			if (c.getInt(c.getColumnIndex(THING.IS_CHILD))!=1){
				break;
			}
			
			int p11 = c.getInt(c.getColumnIndex(THING.PIC11));
			int p12 = c.getInt(c.getColumnIndex(THING.PIC12));
			int p13 = c.getInt(c.getColumnIndex(THING.PIC13));
			int p21 = c.getInt(c.getColumnIndex(THING.PIC21));
			int p22 = c.getInt(c.getColumnIndex(THING.PIC22));
			int p23 = c.getInt(c.getColumnIndex(THING.PIC23));
			int p31 = c.getInt(c.getColumnIndex(THING.PIC31));
			int p32 = c.getInt(c.getColumnIndex(THING.PIC32));
			int p33 = c.getInt(c.getColumnIndex(THING.PIC33));
			
			pic11.setImageResource(p11);
			pic12.setImageResource(p12);
			pic13.setImageResource(p13);
			pic21.setImageResource(p21);
			pic22.setImageResource(p22);
			pic23.setImageResource(p23);
			pic31.setImageResource(p31);
			pic32.setImageResource(p32);
			pic33.setImageResource(p33);
			
			hechengbiaopic[0] = p11;
			hechengbiaopic[1] = p12;
			hechengbiaopic[2] = p13;
			hechengbiaopic[3] = p21;
			hechengbiaopic[4] = p22;
			hechengbiaopic[5] = p23;
			hechengbiaopic[6] = p31;
			hechengbiaopic[7] = p32;
			hechengbiaopic[8] = p33;
		}
		c.close();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub		
		if (adapter != null && adapter.getCursor() != null){
			adapter.getCursor().close();
		}
		super.onDestroy();
	}
}
