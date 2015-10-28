package com.example.lohaningweather;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

public class C {

	public void getAction_Bar(int layout_id,ActionBar mActionBar){
		 
		//±ÍÃ‚¿∏
	 	//mActionBar=getActionBar();
	 	if(mActionBar!=null){
	 		mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
	 		mActionBar.setDisplayShowCustomEnabled(true);
	 		mActionBar.setCustomView(layout_id);
	 		/*a=(ImageView) mActionBar.getCustomView().findViewById(a_id);
	 		b=(ImageView) mActionBar.getCustomView().findViewById(b_id);*/
	 	}
	}
	
}
