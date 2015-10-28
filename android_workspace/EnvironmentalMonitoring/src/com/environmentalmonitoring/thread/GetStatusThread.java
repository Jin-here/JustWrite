package com.environmentalmonitoring.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.environmentalmonitoring.bean.MachineStatus;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.parse.Parse;

public class GetStatusThread extends Thread {
	private boolean isClosed = false;
	
	private Handler mHandler;

	
	public GetStatusThread(Handler mHandler) {
		// TODO Auto-generated constructor stub
		this.mHandler = mHandler;
	}
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!isClosed){
			try {
					Socket client = new Socket(Config.HTTP_SERVER_IP,Config.HTTP_SERVER_PORT);
					client.setSoTimeout(Config.TIME_OUT);
					
					
					sendMsg(client, Config.GET_MACHINE_STATUS);
					MachineStatus status = Parse.BackStatusParse(rcvMsg(client));
				
					client.close();
					
				
				Message msg = mHandler.obtainMessage();
				msg.what = Config.GET_STATUS_WHAT;
				Bundle bundle = new Bundle();
				bundle.putBoolean("machine00_status", status.getStatus(0));
				bundle.putBoolean("machine01_status", status.getStatus(1));
				bundle.putBoolean("machine02_status", status.getStatus(2));
				bundle.putBoolean("machine03_status", status.getStatus(3));
				msg.setData(bundle);
				mHandler.sendMessage(msg);
				
				//MainActivity.callAdapterDataChanged();
				//client.close();
				
			
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				//µØ¥∞œ‘ æ√ªÕ¯
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(Config.GET_DATA_DELAY);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
	
	private void sendMsg(Socket client,byte[] getMachineStatus) throws IOException{	
		//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		OutputStream out = client.getOutputStream();
		if (!client.isClosed() && client.isConnected() && !client.isOutputShutdown()){
			out.write(getMachineStatus);
			out.flush();
			System.out.println("client:send.." + getMachineStatus + " belong to Thread:" + Thread.currentThread().getId());
			//out.close();
		}	
	}
	
	private String rcvMsg(Socket client) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String msg = in.readLine();
		System.out.println("client:rcv.." + msg + " belong to Thread:" + Thread.currentThread().getId());
		//in.close();
		return msg;
	}
	
	public void closeSocket(){
		isClosed = true;
	}
}
