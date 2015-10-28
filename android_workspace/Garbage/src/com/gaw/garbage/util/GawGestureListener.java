package com.gaw.garbage.util;

import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

public class GawGestureListener extends SimpleOnGestureListener implements OnScaleGestureListener{
	
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "OnSingleTap", Toast.LENGTH_SHORT).show();
		return super.onSingleTapUp(e);
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "OnLongPress", Toast.LENGTH_SHORT).show();
		super.onLongPress(e);
		onGestureOverListener.OnLongPressGestureOver();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2,
			float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "onScroll", Toast.LENGTH_SHORT).show();
		return super.onScroll(e1, e2, distanceX, distanceY);
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
		    
	        onGestureOverListener.OnLeftGestureOver();
	        //Toast.makeText(getActivity(), "向左手势", Toast.LENGTH_SHORT).show();  
	    }else if (Math.abs(e2y-e1y)<(e2x-e1x) && e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
	    
	        onGestureOverListener.OnRightGestureOver();
	        //Toast.makeText(getActivity(), "向右手势", Toast.LENGTH_SHORT).show();  
	    }else if (Math.abs(e2x-e1x)<(e1y-e2y) && e1.getY() - e2.getY() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
	    
	        onGestureOverListener.OnUpGestureOver();
	        //Toast.makeText(getActivity(), "向上手势", Toast.LENGTH_SHORT).show();  
	    }else if (Math.abs(e2x-e1x)<(e2y-e1y) && e2.getY() - e1.getY() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
	    
	        onGestureOverListener.OnDownGestureOver();
	        //Toast.makeText(getActivity(), "向下手势", Toast.LENGTH_SHORT).show();  
	    }  
	    
	    return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "onShowPress", Toast.LENGTH_SHORT).show();
		super.onShowPress(e);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "onDown", Toast.LENGTH_SHORT).show();
		return super.onDown(e);
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "onDoubleTap", Toast.LENGTH_SHORT).show();
		onGestureOverListener.OnDoubleTapGestureOver();
		return false;		
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "向左手势", Toast.LENGTH_SHORT).show();
		return super.onDoubleTapEvent(e);
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
		onGestureOverListener.OnSingleTapConfirmed();
		return false;
	}
	

	//OnScaleGestureListener 双手缩放监听器方法
	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		return true;//返回true才继续执行onscale方法，以及后续方法
	}
	
	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub

		onGestureOverListener.OnScaleGestureOver();
		return true;
	}
	
	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
	}
	
	
	//实现此listener，以实现手势完成后采取相应措施
	public interface OnGestureOverListener{
		public void OnUpGestureOver();
		
		public void OnSingleTapConfirmed();

		public void OnDownGestureOver();
		
		public void OnLeftGestureOver();
		
		public void OnRightGestureOver();
		
		public void OnScaleGestureOver();
		
		public void OnLongPressGestureOver();
		
		public void OnDoubleTapGestureOver();
	}
	
	private OnGestureOverListener onGestureOverListener;
	
	public void setOnGestureOverListener(OnGestureOverListener onGestureOverListener){
		this.onGestureOverListener = onGestureOverListener;
	}

	

}
