package com.environmentalmonitoring.dialog;

import com.environmentalmonitoring.activity.LoginActivity;
import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.ClientApp;
import com.environmentalmonitoring.thread.RegisThread;
import com.environmentalmonitoring.thread.RegisThread.OnSocketOverListener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisDialog extends DialogFragment {
	public static final String TAG = "REGISDIALOG";
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		final ClientApp app = (ClientApp) getActivity().getApplication();
		final LoginActivity activity = (LoginActivity) getActivity();
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View layout = inflater.inflate(R.layout.regisdialog, null);
		final EditText register_account = (EditText) layout.findViewById(R.id.register_account);
		final EditText register_password = (EditText) layout.findViewById(R.id.register_password);
		final EditText register_email = (EditText) layout.findViewById(R.id.register_email);
		Button regis_confirm = (Button) layout.findViewById(R.id.regis_confirm);
		Button regis_cancel = (Button) layout.findViewById(R.id.regis_cancel);
		
		regis_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String username = register_account.getText().toString();
          	  String port = register_password.getText().toString();
          	  String mail = register_email.getText().toString();
          	  if (username.equals("") || username == null){
          		  //register_account.setHint(R.string.username_not_empty);
          		  register_account.setHint(R.string.username_not_empty);
          		  // �����ƶ�����
          		  Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
          		  register_account.startAnimation(shakeAnimation);
          	  }else
          	  if (port.equals("") || port == null){
          		  //register_password.setHint(R.string.password_not_empty);
          		  register_password.setHint(R.string.password_not_empty);
          		  Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
          		  register_password.startAnimation(shakeAnimation);
          	  }else
          	  if (mail.equals("") || mail == null){
          		  //register_account.setHint(R.string.email_not_empty);
          		  register_email.setHint(R.string.email_not_empty);
        		  Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        		  register_email.startAnimation(shakeAnimation);
          	  }else{
          		  RegisThread thread = new RegisThread(username, port, mail, activity,activity.mHandler);
          		  thread.setOnSocketOverListener(new OnSocketOverListener() {
						
						@Override
						public void onSocketOver(String back) {
							// TODO Auto-generated method stub
							if (back.equals("success")){
								app.setUsername(username);
								RegisDialog.this.getDialog().cancel();
							}else{
								activity.mHandler.post(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										register_account.setText("");
										register_password.setText("");
										register_email.setText("");
									}
								});
							}
						}
					});
          		  thread.start();
          		  //�ύ��������
          		  
          	  }
			}
		});
		
		regis_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisDialog.this.getDialog().cancel();
			}
		});
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(layout)
	    // Add action buttons
	           /*.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // sign in the user ...
	            	  final String ip = register_account.getText().toString();
	            	  String port = register_password.getText().toString();
	            	  String mail = register_email.getText().toString();
	            	  if (ip.equals("") || ip == null){
	            		  register_account.setHint(R.string.username_not_empty);
	            	  }else
	            	  if (port.equals("") || port == null){
	            		  register_password.setHint(R.string.password_not_empty);
	            	  }else
	            	  if (mail.equals("") || mail == null){
	            		  register_account.setHint(R.string.email_not_empty);
	            	  }else{
	            		  RegisThread thread = new RegisThread(ip, port, mail, getActivity());
	            		  thread.setOnSocketOverListener(new OnSocketOverListener() {
							
							@Override
							public void onSocketOver(String back) {
								// TODO Auto-generated method stub
								if (back.equals("true")){
									app.setValue("username", ip);
									Toast.makeText(getActivity(), getString(R.string.register_success), Toast.LENGTH_SHORT).show();;
								}else{
									register_account.setText("");
									register_password.setText("");
									register_email.setText("");
									Toast.makeText(getActivity(), getString(R.string.register_failed), Toast.LENGTH_SHORT).show();;
								}
							}
						});
	            		  thread.start();
	            		  //�ύ��������
	            		  
	            	  }
	               }
	           })
	           .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   RegisDialog.this.getDialog().cancel();
	               }
	           })*/;      
	    return builder.create();
	}
}