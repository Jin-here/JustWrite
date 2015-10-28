package com.gaw.garbage.adapter;

import com.gaw.garbage.R;
import com.gaw.garbage.activity.CatalogActivity;
import com.gaw.garbage.bean.Garbage;
import com.gaw.garbage.config.Config;
import com.gaw.garbage.db.MySQLiteHelper;
import com.gaw.garbage.fragment.Fragment00;
import com.gaw.garbage.fragment.Fragment01;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyCursorAdapter extends CursorAdapter {
	private TextView list_item_filename;
	private TextView list_item_time;
	private View view;
	
	private Fragment01 fragment01;
	private MySQLiteHelper mysqlitehelper;

	public MyCursorAdapter(Fragment01 fragment01, Context context, Cursor c) {
		super(context, c, false);
		// TODO Auto-generated constructor stub
		this.fragment01 = fragment01;
		mysqlitehelper = new MySQLiteHelper(context);
	}

	@Override
	public void bindView(View v, final Context mContext, Cursor c) {
		// TODO Auto-generated method stub
		//如果hasfile为空，则取brief，否则取filename
		final short hasfile = c.getShort(c.getColumnIndex(Config.HASFILE));
		final String filename = c.getString(c.getColumnIndex(Config.FILENAME));
		if (hasfile == 0){
			list_item_filename.setText(c.getString(c.getColumnIndex(Config.BRIEF)));
			//时间设置，只有没有文件夹的才显示时间
			list_item_time.setText(c.getString(c.getColumnIndex(Config.TIME)));
		}else{
			list_item_filename.setText(filename);
		}
		
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//如果hasfile=0，则进入context
				//否则进入文件夹
				if (hasfile == 0){
					mysqlitehelper.getGarbageById(Long.parseLong(filename));
					//Fragment00.garbage = new Garbage(garbage);
					//结束activity
					fragment01.getActivity().finish();
					fragment01.getActivity().overridePendingTransition(R.anim.up_in, R.anim.up_out);
					
				}else{
					fragment01.freshCatalog(mysqlitehelper.getCatalog(filename));
				}
			}
		});
	}

	@Override
	public View newView(Context mContext, Cursor c, ViewGroup group) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.list_item, null);
		list_item_filename = (TextView) view.findViewById(R.id.list_item_filename);
		list_item_time = (TextView) view.findViewById(R.id.list_item_time);
		return view;
	}

}
