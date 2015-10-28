package com.environmentalmonitoring.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.ClientApp;

public class SetValueDialogFragment extends DialogFragment {

	public static final String TAG = "SETVALUEDIALOGFRAGMENT";
	private boolean isMax = false;
	private boolean isMin = false;
	private String titleS = "Dialog";
	private double maxD = 100.0;
	private double minD = 0.0;
	private int location = 0;
	
	public SetValueDialogFragment(boolean isMax,boolean isMin,String title,double max,double min,int location){
		this.isMax = isMax;
		this.isMin = isMin;
		this.titleS = title;
		this.maxD = Math.round(max*100)/100.00;
		this.minD = Math.round(min*100)/100.00;
		this.location = location;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View layout = inflater.inflate(R.layout.dialog, null);
		TextView title = (TextView) layout.findViewById(R.id.dialog_title);
		
		final EditText min = (EditText) layout.findViewById(R.id.dialog_min);
		final EditText max = (EditText) layout.findViewById(R.id.dialog_max);
		TextView dialog_min_textview = (TextView) layout.findViewById(R.id.dialog_min_textview);
		TextView dialog_max_textview = (TextView) layout.findViewById(R.id.dialog_max_textview);
		
		final ClientApp app = (ClientApp) getActivity().getApplication();		
		title.setText(titleS);
		max.setText(String.valueOf(maxD));
		min.setText(String.valueOf(minD));
		if (!isMax){
			max.setVisibility(View.INVISIBLE);
			dialog_max_textview.setVisibility(View.INVISIBLE);
		}
		if (!isMin){
			min.setVisibility(View.INVISIBLE);
			dialog_min_textview.setVisibility(View.INVISIBLE);
		}
		
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
	            	   switch (location){
	   				case 0:
	   					if (isMax){
	   						app.SetSensorMax(0, Float.parseFloat(max.getText().toString()));						
	   					}
	   					if (isMin){
	   						app.SetSensorMin(0, Float.parseFloat(min.getText().toString()));
	   					}
	   					break;
	   				case 1:
	   					if (isMax){
	   						app.SetSensorMax(1, Float.parseFloat(max.getText().toString()));
	   					}
	   					if (isMin){
	   						app.SetSensorMin(1, Float.parseFloat(min.getText().toString()));
	   					}
	   					break;
	   				case 2:
	   					if (isMax){
	   						app.SetSensorMax(2, Float.parseFloat(max.getText().toString()));
	   					}
	   					if (isMin){
	   						app.SetSensorMin(2, Float.parseFloat(min.getText().toString()));
	   					}
	   					break;
	   				case 3:
	   					if (isMax){
	   						app.SetSensorMax(3, Float.parseFloat(max.getText().toString()));
	   					}
	   					if (isMin){
	   						app.SetSensorMin(3, Float.parseFloat(min.getText().toString()));
	   					}
	   					break;
	   				case 4:
	   					if (isMax){
	   						app.SetSensorMax(4, Float.parseFloat(max.getText().toString()));
	   					}
	   					if (isMin){
	   						app.SetSensorMin(4, Float.parseFloat(min.getText().toString()));
	   					}
	   					break;
	   				case 5:
	   					if (isMax){
	   						app.SetSensorMax(5, Float.parseFloat(max.getText().toString()));
	   					}
	   					if (isMin){
	   						app.SetSensorMin(5, Float.parseFloat(min.getText().toString()));
	   					}
	   					break;
	   				}
	               }
	           })
	           .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   SetValueDialogFragment.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();

	}
}
