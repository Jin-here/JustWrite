package com.gaw.garbage.schedul;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.gaw.garbage.R;
import com.gaw.garbage.activity.CatalogActivity;
import com.gaw.garbage.util.GawGestureListener.OnGestureOverListener;

public class ControlSchedul implements OnGestureOverListener {

	private FragmentActivity fragmentactivity;
	
	public ControlSchedul(FragmentActivity fragmentactivity) {
		// TODO Auto-generated constructor stub
		this.fragmentactivity = fragmentactivity;
	}
	
	@Override
	public void OnUpGestureOver() {
		// TODO Auto-generated method stub
		//�رճ���
		Toast.makeText(fragmentactivity, "�رճ���", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void OnDownGestureOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnLeftGestureOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnRightGestureOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnScaleGestureOver() {
		// TODO Auto-generated method stub
		Toast.makeText(fragmentactivity, "����", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void OnLongPressGestureOver() {
		// TODO Auto-generated method stub
		Toast.makeText(fragmentactivity, "����", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void OnDoubleTapGestureOver() {
		// TODO Auto-generated method stub
		Toast.makeText(fragmentactivity, "˫��", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void OnSingleTapConfirmed() {
		// TODO Auto-generated method stub
		
	}

}
