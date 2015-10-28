package com.gaw.garbage.fragment;

import com.gaw.garbage.R;
import com.gaw.garbage.activity.MainActivity;
import com.gaw.garbage.bean.Garbage;
import com.gaw.garbage.config.Config;
import com.gaw.garbage.db.MySQLiteHelper;
import com.gaw.garbage.util.DateTools;
import com.gaw.garbage.util.TextEditor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * 功能： 1、接受手势 2、
 *
 */
public class Fragment00 extends Fragment {
	public static String[] TAG = new String[] { "FRAGMENT00_0", "FRAGMENT00_1" };

	public static long nowId = 0;

	public static Garbage garbage = new Garbage();

	public static Garbage garbage_last = new Garbage();

	private EditText fragment00_garbage_context;

	private String contextfrom = "";
	private String contextto = "";

	private MySQLiteHelper mysqlitehelper;

	public Fragment00() {
		// TODO Auto-generated constructor stub
	}

	// //////////////////////////生命周期///////////////////////////////////
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mysqlitehelper = new MySQLiteHelper(getActivity());
		nowId = Config.TIME_MAX;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment00, container, false);
		fragment00_garbage_context = (EditText) view
				.findViewById(R.id.fragment00_garbage_context);

		fragment00_garbage_context.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return ((MainActivity) getActivity()).mGestureDetector
						.onTouchEvent(event);
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (!this.isHidden()){
			onHiddenChanged(false);
		}
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("error", "fragment:onpause");
		// 将未保存的保存到数据库
		if (!this.isHidden()) {
			String context = fragment00_garbage_context.getText().toString();
			if (!"".equals(context)) {
				if (garbage.getId() == Long.MAX_VALUE) {
					// add
					// Garbage garbage = new Garbage();
					garbage.setFilename("");
					garbage.setContext(context);
					garbage.setBrief(TextEditor.getBrief(context));
					garbage.setHasFile(false);
					garbage.setTime(DateTools.getTimeL());
					garbage.setId(mysqlitehelper.addGarbage(garbage));
					//存入目录
					mysqlitehelper.addCatalog(garbage);
				} else {
					// update
					// Garbage garbage = new Garbage();
					if (!context.equals(garbage.getContext())){
						garbage.setFilename("");
						garbage.setContext(context);
						garbage.setBrief(TextEditor.getBrief(context));
						garbage.setHasFile(false);
						garbage.setTime(DateTools.getTimeL());
						mysqlitehelper.updateGarbage(garbage);
						
						//将没有文件夹的brief更新一下
						mysqlitehelper.updateCatalog(garbage);
					}
										
				}
			}
		}

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("error", "fragment:onstop");
	}

	// //////////////////////////生命周期///////////////////////////////////
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		// 旧草稿显示：按日期查询
		// 旧草稿修改：如果编辑，按原日期保存
		String context = fragment00_garbage_context.getText().toString();
		if (hidden) {
			Log.e("error", "hidden/show:hidden");
			// 如果修改保存

			// 保存至数据库
			if (garbage_last.getContext().equals("")) {
				Log.e("error", "add:false");
				if (TextEditor.isAll32(context)) {
					Log.e("add?", "true");
					// 空草稿，写入信息后保存,并更新时间
					garbage_last.setFilename("");
					garbage_last.setContext(context);
					garbage_last.setBrief(TextEditor.getBrief(context));
					garbage_last.setHasFile(false);
					garbage_last.setTime(DateTools.getTimeL());
					garbage_last.setId(mysqlitehelper.addGarbage(garbage_last));
					Log.e("garbage:", garbage.toString());
					//存入目录
					mysqlitehelper.addCatalog(garbage_last);
				}
			} else {
				Log.e("error", "update:false");
				if (!garbage_last.getContext().equals(context)) {
					// 如果为空，则删除
					Log.e("update?", "true");
					garbage_last.setFilename("");
					garbage_last.setContext(context);
					garbage_last.setBrief(TextEditor.getBrief(context));
					garbage_last.setHasFile(false);
					garbage_last.setTime(DateTools.getTimeL());
					mysqlitehelper.updateGarbage(garbage_last);
				}
				// Fragment00.nowTime = garbage.getTime();
				
				//将没有文件夹的brief更新一下
				mysqlitehelper.updateCatalog(garbage_last);
			}

		} else {
			Log.e("error", "hidden/show:show");
			nowId = (garbage.getId() == Long.MAX_VALUE ? Long.MAX_VALUE
					: garbage.getId());
			fragment00_garbage_context.setText(garbage.getContext());
		}
	}
}
