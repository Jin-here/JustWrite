package com.environmentalmonitoring.dialog;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.environmentalmonitoring.activity.LoginActivity;
import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.thread.FindPswThread;
import com.environmentalmonitoring.thread.FindPswThread.OnSocketOverListener;

public class FindPwdDialog extends DialogFragment {
	public static final String TAG = "FINDPSWDIALOG";
	
	private EditText find_psw_account;
	private EditText find_psw_email;
	private EditText yanzhengma;
	private Button send;
	private Button confirm;
	private Button cancel;
	
	private FindPswThread findpswthread;
	private String yanzhengmaS = "";
	private String usernameS = "";
	
	private Handler mHandler;
	public FindPwdDialog(Handler mHandler){
		this.mHandler = mHandler;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.find_psw_dialog, null);
		
		//find_psw_account = (EditText) view.findViewById(R.id.find_psw__account);
		find_psw_email = (EditText) view.findViewById(R.id.find_psw_phone);
		yanzhengma = (EditText) view.findViewById(R.id.yanzhengma);
		send = (Button) view.findViewById(R.id.send);
		confirm = (Button) view.findViewById(R.id.find_psw_confirm);
		cancel = (Button) view.findViewById(R.id.find_psw_cancel);
		
		
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send.setText("请稍等");
				findpswthread = new FindPswThread(find_psw_email.getText().toString(), mHandler);
				findpswthread.setOnSocketOverListener(new OnSocketOverListener() {
					
					@Override
					public void onSocketOver(String back) {
						// TODO Auto-generated method stub
						try {
							JSONObject json = new JSONObject(back);
							String isok = json.getString("isok");
							if ("success".equals(isok)){
								//用户名，验证码存入本地
								usernameS = json.getString("user_name");
								yanzhengmaS = json.getString("user_code");
								send.post(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										send.setText("已发送");
									}
								});
							}else{
								//手机号已存在
								find_psw_email.post(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										find_psw_email.setHint("手机号错误");
										// 左右移动动画
						          		  Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
						          		  find_psw_email.startAnimation(shakeAnimation);
									}
								});
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				findpswthread.start();
				
			}
		});
		
		confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (yanzhengma.getText().toString() == null){
					yanzhengma.setHint("请输入验证码");
					// 左右移动动画
	          		  Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
	          		  yanzhengma.startAnimation(shakeAnimation);
				}else
				if (!(yanzhengma.getText().toString().equals(yanzhengmaS))){
					yanzhengma.setHint("验证码错误");
					// 左右移动动画
	          		  Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
	          		  yanzhengma.startAnimation(shakeAnimation);
				}else{
					FindPwdDialog.this.getDialog().cancel();
					new SetPswDialog(usernameS,find_psw_email.getText().toString(),mHandler).show(getActivity().getSupportFragmentManager(), SetPswDialog.TAG);
					
				}
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FindPwdDialog.this.getDialog().cancel();
			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(view);     
	    return builder.create();
	}
	
	
}
