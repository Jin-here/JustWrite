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
 * ���ܣ� 1���������� 2��
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

	// //////////////////////////��������///////////////////////////////////
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
		// ��δ����ı��浽���ݿ�
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
					//����Ŀ¼
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
						
						//��û���ļ��е�brief����һ��
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

	// //////////////////////////��������///////////////////////////////////
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		// �ɲݸ���ʾ�������ڲ�ѯ
		// �ɲݸ��޸ģ�����༭����ԭ���ڱ���
		String context = fragment00_garbage_context.getText().toString();
		if (hidden) {
			Log.e("error", "hidden/show:hidden");
			// ����޸ı���

			// ���������ݿ�
			if (garbage_last.getContext().equals("")) {
				Log.e("error", "add:false");
				if (TextEditor.isAll32(context)) {
					Log.e("add?", "true");
					// �ղݸ壬д����Ϣ�󱣴�,������ʱ��
					garbage_last.setFilename("");
					garbage_last.setContext(context);
					garbage_last.setBrief(TextEditor.getBrief(context));
					garbage_last.setHasFile(false);
					garbage_last.setTime(DateTools.getTimeL());
					garbage_last.setId(mysqlitehelper.addGarbage(garbage_last));
					Log.e("garbage:", garbage.toString());
					//����Ŀ¼
					mysqlitehelper.addCatalog(garbage_last);
				}
			} else {
				Log.e("error", "update:false");
				if (!garbage_last.getContext().equals(context)) {
					// ���Ϊ�գ���ɾ��
					Log.e("update?", "true");
					garbage_last.setFilename("");
					garbage_last.setContext(context);
					garbage_last.setBrief(TextEditor.getBrief(context));
					garbage_last.setHasFile(false);
					garbage_last.setTime(DateTools.getTimeL());
					mysqlitehelper.updateGarbage(garbage_last);
				}
				// Fragment00.nowTime = garbage.getTime();
				
				//��û���ļ��е�brief����һ��
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
