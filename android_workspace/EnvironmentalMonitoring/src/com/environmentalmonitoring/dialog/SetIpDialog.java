package com.environmentalmonitoring.dialog;

import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.ClientApp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class SetIpDialog extends DialogFragment {
	public static final String TAG = "SETIPDIALOG";
	
	private ClientApp app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		app = (ClientApp) getActivity().getApplication();
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View layout = inflater.inflate(R.layout.set_ip_dialog, null);
		final EditText dialog_ip = (EditText) layout.findViewById(R.id.dialog_ip);
		final EditText dialog_port = (EditText) layout.findViewById(R.id.dialog_port);
		dialog_ip.setText(app.getServerIp());
		dialog_port.setText(String.valueOf(app.getServerPort()));
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(layout)
	    // Add action buttons
	           .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // sign in the user ...
	            	  String ip = dialog_ip.getText().toString();
	            	  String port = dialog_port.getText().toString();
	            	  app.SetServerIp(ip);
	            	  app.SetServerPort(Integer.parseInt(port));
	               }
	           })
	           .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   SetIpDialog.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
	}
}
