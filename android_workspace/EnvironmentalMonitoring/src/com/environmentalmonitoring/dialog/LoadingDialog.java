package com.environmentalmonitoring.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.environmentalmonitoring.activity.R;

public class LoadingDialog extends DialogFragment 
{
	public static final String TAG  = "LOADINGDIALOG";
	
	private ImageView mImage;  
    private TextView mTipTextView;
    private String mTipString = ""; 
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
    }
    
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) 
    {  
        View view = inflater.inflate(R.layout.load_progress_dialog, container, false);  
        mImage = (ImageView) view.findViewById(R.id.progress_dialog_icon);  
        //设置动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.loading_animation);
        mImage.startAnimation(hyperspaceJumpAnimation); 
        //设置提示字符串
        mTipTextView = (TextView) view.findViewById(R.id.progress_dialog_msg);
        mTipString = getString(R.string.please_wait);
        mTipTextView.setText(mTipString);
        setCancelable(true);
        
        return view;  
    }
    
    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	LayoutInflater inflater = getActivity().getLayoutInflater();
    	View view = inflater.inflate(R.layout.load_progress_dialog, null);  
        mImage = (ImageView) view.findViewById(R.id.progress_dialog_icon);  
        //设置动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.loading_animation);
        mImage.startAnimation(hyperspaceJumpAnimation); 
        //设置提示字符串
        mTipTextView = (TextView) view.findViewById(R.id.progress_dialog_msg);
        mTipString = getString(R.string.please_wait);
        mTipTextView.setText(mTipString);
        
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setView(view);
    	setCancelable(false);
    	builder.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
				{
					Message msg = mHandler.obtainMessage();
					msg.what = Config.EXIT_WHAT;
					mHandler.sendMessage(msg);
				}
				return false;
			}
		});
    	
    	return builder.create();
    }*/
    
    public void setTipString(String tipStr)
    {
    	mTipString = tipStr;
    	if(mTipTextView != null)
    	{
    		mTipTextView.setText(mTipString);
    	}
    }
}
