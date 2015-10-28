package com.environmentalmonitoring.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.thread.RegisThread.OnSocketOverListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class FindPswThread extends Thread {
	private String phone = "";
	private Handler mHandler;

	public FindPswThread(String phone, Handler mHandler) {
		this.phone = phone;
		this.mHandler = mHandler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		try {
			Socket client = new Socket(Config.HTTP_SERVER_IP,
					Config.HTTP_SERVER_PORT);
			client.setSoTimeout(Config.TIME_OUT);

			JSONObject json = new JSONObject();
			json.put("flag", "reset");
			json.put("user_phone", phone);
			sendMsg(client, json.toString());
			String back = "";
			back = rcvMsg(client);
			client.close();
			onSocketOverListener.onSocketOver(back);

			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendMsg(Socket client, String data) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
		if (!client.isClosed() && client.isConnected()
				&& !client.isOutputShutdown()) {
			out.write(data + "\n");
			out.flush();
		}
	}

	private String rcvMsg(Socket client) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		return in.readLine();

	}
	
	public interface OnSocketOverListener {
		void onSocketOver(String back);
	}

	protected OnSocketOverListener onSocketOverListener;

	public void setOnSocketOverListener(
			OnSocketOverListener onSocketOverListener) {
		this.onSocketOverListener = onSocketOverListener;
	}
}
