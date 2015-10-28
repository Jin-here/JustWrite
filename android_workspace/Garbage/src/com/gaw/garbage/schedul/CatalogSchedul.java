package com.gaw.garbage.schedul;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

import com.gaw.garbage.R;
import com.gaw.garbage.fragment.Fragment01;
import com.gaw.garbage.fragment.TitleFragment;
import com.gaw.garbage.util.GawGestureListener.OnGestureOverListener;

public class CatalogSchedul implements OnGestureOverListener {
	private FragmentActivity fragmentactivity;
	private FragmentManager mFM;

	public CatalogSchedul(FragmentActivity fragmentactivity) {
		// TODO Auto-generated constructor stub
		this.fragmentactivity = fragmentactivity;
		this.mFM = fragmentactivity.getSupportFragmentManager();

		// 添加 titlefragment
		FragmentTransaction mFT1 = mFM.beginTransaction();
		mFT1.add(R.id.title_container1, new TitleFragment(), TitleFragment.TAG);
		mFT1.commit();

		// 创建fragment01
		FragmentTransaction mFT = mFM.beginTransaction();
		//mFT.setCustomAnimations(R.anim.up_in, R.anim.up_out);
		mFT.add(R.id.container1, new Fragment01(), Fragment01.TAG);
		mFT.commit();

	}

	@Override
	public void OnUpGestureOver() {
		// TODO Auto-generated method stub
		fling();
	}

	@Override
	public void OnDownGestureOver() {
		// TODO Auto-generated method stub
		fling();
	}

	@Override
	public void OnLeftGestureOver() {
		// TODO Auto-generated method stub
		fling();
	}

	@Override
	public void OnRightGestureOver() {
		// TODO Auto-generated method stub
		fling();
	}

	@Override
	public void OnScaleGestureOver() {
		// TODO Auto-generated method stub
		
	}

	//长按进行全局搜索
	@Override
	public void OnLongPressGestureOver() {
		// TODO Auto-generated method stub
		
	}

	//滑动在当前目录新建草稿纸
	private void fling(){
		
	}

	@Override
	public void OnDoubleTapGestureOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnSingleTapConfirmed() {
		// TODO Auto-generated method stub
		
	}

}
