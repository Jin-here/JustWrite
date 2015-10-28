package com.environmentalmonitoring.thread;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.os.Message;

import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.request.BaseRequest;

public class TestThread extends Thread {
	private BaseRequest baserequest;
	
	public TestThread(BaseRequest baserequest) {
		// TODO Auto-generated constructor stub
		this.baserequest = baserequest;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			Socket client = new Socket(Config.HTTP_SERVER_IP, Config.HTTP_SERVER_PORT);
			client.setSoTimeout(Config.TIME_OUT);

			//sendMsg(client, baserequest.getSend_byte());
			sendMsg(client, baserequest.getSend());
			// —” ±1s
			try {
				Thread.sleep(Config.GET_DATA_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			client.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

	private byte[] rcvMsg(Socket client) throws IOException {
		// TODO Auto-generated method stub
		byte[] msg = new byte[51];
		InputStream in = client.getInputStream();
		in.read(msg);
		return msg;
	}
	
	private void sendMsg(Socket client, byte[] getSensorData)
			throws IOException {
		OutputStream out = client.getOutputStream();
		if (!client.isClosed() && client.isConnected()
				&& !client.isOutputShutdown()) {
			out.write(getSensorData);
			out.flush();
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
}
