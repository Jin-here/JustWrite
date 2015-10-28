package com.environmentalmonitoring.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.environmentalmonitoring.config.Config;
import com.example.dbs.User;

public class LoginThread extends Thread {
	private boolean isClosed = false;

	private String username;
	private String password;
	private Handler mHandler;
	private Context mContext;

	public LoginThread(String username, String password, Context mContext,
			Handler mHandler) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.mContext = mContext;
		this.mHandler = mHandler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			/**
			 * 登陆 注册 找回密码 实时数据 flag:login flag:regist flag: flag:ss user_name:
			 * user_name: user_password: user_password user_phone
			 */

			Socket client = new Socket(Config.HTTP_SERVER_IP,
					Config.HTTP_SERVER_PORT);
			//client.setSoTimeout(Config.TIME_OUT);

			JSONObject json = new JSONObject();
			json.put("flag", "login");
			json.put("user_name", username);
			json.put("user_password", password);
			sendMsg(client, json.toString());
			String back = "";
			back = rcvMsg(client);
			System.out.println(back);

			client.close();

			Message msg = mHandler.obtainMessage();
			msg.what = Config.LOGIN_WHAT;
			msg.arg1 = "success".equals(back)?1:0;
			mHandler.sendMessage(msg);

			// MainActivity.callAdapterDataChanged();
			// client.close();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			// 弹窗显示没网
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	public void closeSocket() {
		isClosed = true;
	}
}
