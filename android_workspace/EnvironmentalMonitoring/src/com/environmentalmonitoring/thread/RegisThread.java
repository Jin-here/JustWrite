package com.environmentalmonitoring.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class RegisThread extends Thread {
	private boolean isClosed = false;

	private String username;
	private String password;
	private String mail;
	private Handler mHandler;
	private Context mContext;

	public RegisThread(String username, String password, String mail,
			Context mContext,Handler mHandler) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.mContext = mContext;
		this.mHandler = mHandler;
		this.mail = mail;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			Socket client = new Socket(Config.HTTP_SERVER_IP,
					Config.HTTP_SERVER_PORT);
			client.setSoTimeout(Config.TIME_OUT);

			JSONObject json = new JSONObject();
			json.put("flag", "regist");
			json.put("user_name", username);
			json.put("user_password", password);
			json.put("user_phone", mail);
			sendMsg(client, json.toString());
			String back = "";

			back = rcvMsg(client);
			client.close();
			onSocketOverListener.onSocketOver(back);

			
			 Message msg = mHandler.obtainMessage(); 
			 msg.what = Config.REGIS_WHAT;
			 msg.arg1 = ("success".equals(back))?1:0;
			 mHandler.sendMessage(msg);
			 /* (back.getPassword().equals(password)){ bundle.putBoolean("login",
			 * true); }else{ bundle.putBoolean("login", false); }
			 * msg.setData(bundle); mHandler.sendMessage(msg);
			 */

			// MainActivity.callAdapterDataChanged();
			// client.close();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			// µØ¥∞œ‘ æ√ªÕ¯
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
		// ObjectInputStream in = new
		// ObjectInputStream(client.getInputStream());

		String back = in.readLine();
		System.out.println("client:rcv.." + back + " belong to Thread:"
				+ Thread.currentThread().getId());
		// in.close();
		return back;
	}

	public interface OnSocketOverListener {
		void onSocketOver(String back);
	}

	protected OnSocketOverListener onSocketOverListener;

	public void setOnSocketOverListener(
			OnSocketOverListener onSocketOverListener) {
		this.onSocketOverListener = onSocketOverListener;
	}

	public void closeSocket() {
		isClosed = true;
	}
}
