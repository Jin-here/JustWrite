package com.environmentalmonitoring.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.environmentalmonitoring.config.Config;

public class MachineControlThread extends Thread {
	private byte[] send_order;
	
	

	public MachineControlThread(byte[] send_data){
		this.send_order = send_data;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket client = new Socket(Config.HTTP_SERVER_IP,Config.HTTP_SERVER_PORT);			
			sendMsg(client, send_order);
			byte[] result = rcvMsg(client);
			//System.out.println("client get:" + result);
			client.close();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	private void sendMsg(Socket client,byte[] send_order2) throws IOException{	
		//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		OutputStream out = client.getOutputStream();
		if (!client.isClosed() && client.isConnected() && !client.isOutputShutdown()){
			out.write(send_order2);
			//out.write(send_order2 + "\n");
			out.flush();
			System.out.println("client:send.." + send_order2 + " belong to Thread:" + Thread.currentThread().getId());
			//out.close();
		}	
	}
	
	private byte[] rcvMsg(Socket client) throws IOException{
		// TODO Auto-generated method stub
		//BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		InputStream in = client.getInputStream();
		byte[] msg = new byte[]{};
		in.read(msg);
		//String msg = in.readLine();
		System.out.println("client:rcv.." + msg + " belong to Thread:" + Thread.currentThread().getId());
		//in.close();
		return msg;
	}
	
}
