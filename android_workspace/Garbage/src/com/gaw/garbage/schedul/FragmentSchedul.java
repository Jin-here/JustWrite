package com.gaw.garbage.schedul;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.gaw.garbage.R;
import com.gaw.garbage.activity.CatalogActivity;
import com.gaw.garbage.bean.Garbage;
import com.gaw.garbage.db.MySQLiteHelper;
import com.gaw.garbage.fragment.Fragment00;
import com.gaw.garbage.fragment.Fragment01;
import com.gaw.garbage.fragment.TitleFragment;
import com.gaw.garbage.util.DateTools;
import com.gaw.garbage.util.GawGestureListener.OnGestureOverListener;

public class FragmentSchedul extends SimpleOnGestureListener {

	private ArrayList<Fragment00> stack = new ArrayList<Fragment00>();
	private FragmentActivity fragmentactivity;
	private FragmentManager mFM;
	private TitleFragment titleFragment;
	private Fragment00 fragment00;
	private Fragment01 fragment01;
	private MySQLiteHelper mysqlitehelper;
	
	// 内参
	private boolean turn = false;
	private boolean direction = false;//right:true

	public FragmentSchedul(FragmentActivity fragmentactivity) {
		// TODO Auto-generated constructor stub
		this.fragmentactivity = fragmentactivity;
		this.mFM = fragmentactivity.getSupportFragmentManager();
		this.mysqlitehelper = new MySQLiteHelper(fragmentactivity);
		
		initalStack();
		//添加 titlefragment
		FragmentTransaction mFT = mFM.beginTransaction();
		mFT.add(R.id.title_container, new TitleFragment(), TitleFragment.TAG);
		mFT.commit();
		// 进入主页面先添加fragment00
		popFragmentByContext();
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "onFing", Toast.LENGTH_SHORT).show();
		int verticalMinDistance = 20;  
		int minVelocity         = 0;
		
		float e1x = e1.getX();
		float e1y = e1.getY();
		float e2x = e2.getX();
		float e2y = e2.getY();
		
		
		if (Math.abs(e2y-e1y)<(e1x-e2x) && e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
		    
	        OnLeftGestureOver();
	        //Toast.makeText(getActivity(), "向左手势", Toast.LENGTH_SHORT).show();  
	    }else if (Math.abs(e2y-e1y)<(e2x-e1x) && e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
	    
	        OnRightGestureOver();
	        //Toast.makeText(getActivity(), "向右手势", Toast.LENGTH_SHORT).show();  
	    }else if (Math.abs(e2x-e1x)<(e1y-e2y) && e1.getY() - e2.getY() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
	    
	    	//Toast.makeText(getActivity(), "向上手势", Toast.LENGTH_SHORT).show();  
	    }else if (Math.abs(e2x-e1x)<(e2y-e1y) && e2.getY() - e1.getY() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
	    
	        //Toast.makeText(getActivity(), "向下手势", Toast.LENGTH_SHORT).show();  
	    }  
	    
	    return false;
	}

	public void OnLeftGestureOver() {
		// TODO Auto-generated method stub
		Log.e("error", "gesture:left");
		// 返回上次草稿
		Log.e("error-nowTime", String.valueOf(Fragment00.nowId));
		Garbage garbage = mysqlitehelper.getRightGarbage(Fragment00.nowId);
		if (garbage == null){
			Log.e("error--onrightgestureover:", "no-right");
			//弹出提示框
			Toast.makeText(fragmentactivity, fragmentactivity.getString(R.string.no_right), Toast.LENGTH_SHORT).show();
		}else{
			direction = false;
			popFragmentByContext();
		}
		
	}

	public void OnRightGestureOver() {
		// TODO Auto-generated method stub
		direction = true;
		Log.e("error", "gesture:right");
		// 新建草稿纸
		Garbage garbage = mysqlitehelper.getLeftGarbage(Fragment00.nowId);
		if (garbage == null){
			Toast.makeText(fragmentactivity, "草稿已保存！", Toast.LENGTH_SHORT).show();
			Log.e("error--onleftgestureover:", "no-left");
			popFragmentByContext();
		}else{
			popFragmentByContext();
		}
		
	}

	private void initalStack() {
		// 添加两个初始的fragment,轮流工作
		stack.add(new Fragment00());
		stack.add(new Fragment00());
	}

	/**
	 * 添加新的草稿纸
	 * @param context
	 */
	private void popFragmentByContext() {
		// 若是资源被异常收回，则重新添加新的fragment
		if (stack.size() != 2) {
			Log.e("error", "stack.size():" + String.valueOf(stack.size()));
			stack.clear();
			initalStack();
		}
		int temp = getTurn();
		if (mFM.findFragmentByTag(Fragment00.TAG[temp]) == null) {
			FragmentTransaction mFT = mFM.beginTransaction();
			if (direction){
				mFT.setCustomAnimations(R.anim.left_in, R.anim.left_out);
			}else{
				mFT.setCustomAnimations(R.anim.right_in, R.anim.right_out);
			}
			mFT.add(R.id.container, stack.get(temp),
					Fragment00.TAG[temp]);
			
			Log.e("add-fragment:", Fragment00.TAG[temp]);
			mFT.commit();
			
		} else {
			FragmentTransaction mFT = mFM.beginTransaction();
			if (direction){
				mFT.setCustomAnimations(R.anim.left_in, R.anim.left_out);
			}else{
				mFT.setCustomAnimations(R.anim.right_in, R.anim.right_out);
			}
			mFT.show(stack.get(temp));
			
			mFT.commit();
			
		}
		if (mFM.findFragmentByTag(Fragment00.TAG[1 - temp]) != null) {
			FragmentTransaction mFT = mFM.beginTransaction();
			if (direction){
				mFT.setCustomAnimations(R.anim.left_in, R.anim.left_out);
			}else{
				mFT.setCustomAnimations(R.anim.right_in, R.anim.right_out);
			}
			mFT.hide(stack.get(1 - temp));
			mFT.commit();
		}
	}

	private int getTurn() {
		turn = !turn;
		return turn ? 1 : 0;
	}

}
