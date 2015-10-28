package com.environmentalmonitoring.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.dialog.LoadingDialog;
import com.environmentalmonitoring.dialog.NoNetDialog;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub		
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        MainActivity activity = (MainActivity) context;
    	FragmentManager fM = activity.getSupportFragmentManager();
    	NoNetDialog nonetdialog = new NoNetDialog();
        if (!wifiInfo.isConnected()){       	
        	if (fM.findFragmentByTag(NoNetDialog.TAG)
        			== null && fM.findFragmentByTag(LoadingDialog.TAG) == null){
        		nonetdialog.show(activity.getSupportFragmentManager(), NoNetDialog.TAG);
        	}
        }
        if (wifiInfo.isConnected()){
        	LoadingDialog loadingdialog = (LoadingDialog) fM.findFragmentByTag(LoadingDialog.TAG);
        	if (loadingdialog != null){
        		loadingdialog.dismiss();
        	}
        }
	}
	
	

}
