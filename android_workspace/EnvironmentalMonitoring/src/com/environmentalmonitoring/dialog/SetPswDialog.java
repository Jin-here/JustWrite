package com.environmentalmonitoring.dialog;

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

import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.thread.SetPswThread;

public class SetPswDialog extends DialogFragment {
	public static final String TAG = "SETPSWDIALOG";
	
	private EditText set_psw_account;
	private EditText set_psw_psw;
	private EditText set_psw_psw_again;
	private Button confirm;
	private Button cancel;
	
	private Handler mHandler;
	private String username = "";
	private String phone = "";
	
	public SetPswDialog(String username,String phone,Handler mHandler){
		this.username = username;
		this.phone = phone;
		this.mHandler = mHandler;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.set_psw_dialog, null);
		set_psw_account = (EditText) view.findViewById(R.id.set_psw_account);
		set_psw_psw = (EditText) view.findViewById(R.id.set_psw_password);
		set_psw_psw_again = (EditText) view.findViewById(R.id.set_psw_password_again);
		confirm = (Button) view.findViewById(R.id.set_psw_confirm);
		cancel = (Button) view.findViewById(R.id.set_psw_cancel);

		set_psw_account.setText(username);
		confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String psw = set_psw_psw.getText().toString();
				if (!psw.equals(set_psw_psw_again.getText().toString())){
					set_psw_psw.setText("");
					set_psw_psw_again.setText("");
					// ◊Û”““∆∂Ø∂Øª≠
	          		  Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
	          		  set_psw_psw.startAnimation(shakeAnimation);
	          		set_psw_psw_again.startAnimation(shakeAnimation);
				}else{
					//∑¢ÀÕ
					new SetPswThread(username, psw, phone).start();
					SetPswDialog.this.getDialog().cancel();
				}
				
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SetPswDialog.this.getDialog().cancel();
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
