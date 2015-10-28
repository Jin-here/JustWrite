package com.example.lohaningweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MoreDetail extends Activity {

	private TextView cy_detail;
	private TextView gm_detail;
	private TextView fs_detail;
	public static MoreDetail f=null;
	private Intent intent;
	//private Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_detail);
		f=this;
		
		cy_detail=(TextView) findViewById(R.id.cy_detail);
		gm_detail=(TextView) findViewById(R.id.gm_detail);
		fs_detail=(TextView) findViewById(R.id.fs_detail);
		
		intent=getIntent();
		//bundle=new Bundle();
		String a=intent.getExtras().getString("CYdetail");
		String b=intent.getExtras().getString("GMdetail");
		String c=intent.getExtras().getString("FSdetail");
		
		/*cy_detail.setText(a);
		gm_detail.setText(b);
		fs_detail.setText(c);*/
		
	}
}
