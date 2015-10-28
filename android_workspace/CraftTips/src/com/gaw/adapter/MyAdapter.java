package com.gaw.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaw.activity.R;
import com.gaw.db.MyHelper;
import com.gaw.stastics.THING;

public class MyAdapter extends CursorAdapter {

	

	public MyAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		this.context = context;
		
		// TODO Auto-generated constructor stub
	}

	private Context context;
	
	

	

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView zuoyong = (TextView) view.findViewById(R.id.zuoyong);
		name.setText(cursor.getString(cursor.getColumnIndex(THING.NAME)));
		zuoyong.setText(cursor.getString(cursor.getColumnIndex(THING.ZUOYONG)));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
		View layout = inflater.inflate(R.layout.auto, null);				
	
		TextView name = (TextView) layout.findViewById(R.id.name);
		TextView zuoyong = (TextView) layout.findViewById(R.id.zuoyong);
		name.setText(cursor.getString(cursor.getColumnIndex(THING.NAME)));
		zuoyong.setText(cursor.getString(cursor.getColumnIndex(THING.ZUOYONG)));
		return layout;
	}

	@Override
	public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
		// TODO Auto-generated method stub
		super.runQueryOnBackgroundThread(constraint);
		
		if (constraint != null){
			MyHelper helper = new MyHelper(context);
			Cursor c = helper.autoquery(constraint.toString());
			
			return c;
		}
		return null;
	}
	
	
	@Override
	public CharSequence convertToString(Cursor cursor) {
		// TODO Auto-generated method stub
		return "";
	}
}
