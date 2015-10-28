package com.environmentalmonitoring.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.ClientApp;

public class GetDataRequest extends BaseRequest {
	private MainActivity activity;
	//实时显示的数据
	private ArrayList<Double> data;
	
	//上次正确数据
	private ArrayList<Double> lastdata = new ArrayList<Double>();
	
	//转发个网页的数据
	private ArrayList<String> dataweb = new ArrayList<String>();
	
	public GetDataRequest(MainActivity activity,ArrayList<Double> data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.activity = activity;
		lastdata.add(34.0);
		lastdata.add(38.0);
		lastdata.add(0.07);
		lastdata.add(425.0);
		lastdata.add(21.0);
		lastdata.add(50.0);
	}
	
	@Override
	public byte[] getSend_byte() {
		// TODO Auto-generated method stub
		return Config.GET_SENSOR_DATA;
	}
	
	@Override
	public void parseBack(byte[] back) {
		// TODO Auto-generated method stub
		if (back[0] == 0x01 &&
				back[1] == 0x03 &&
				back[2] == (byte)0x2e){
			BackSensorDataParse(back);
			onSocketOverListener.onBackSuccess(this);
		}else{
			onSocketOverListener.onBackFailed(this);
		}
		
	}
	
	public void BackSensorDataParse(byte[] back){	
		double jiaquan = chuli(((back[3]&0xff)*256 + (back[4]&0xff)),0);
		double pm25    = chuli((back[5]&0xff)*256 + (back[6]&0xff),1);
		double co2    = chuli((back[7]&0xff)*256 + (back[8]&0xff),2);
		double light    = chuli((back[9]&0xff)*256 + (back[10]&0xff),3);
		double wengdu    = chuli((back[11]&0xff)*256 + (back[12]&0xff),4);
		double voice    = chuli((back[13]&0xff)*256 + (back[14]&0xff),5);
		
		//过滤不正确数据，用上次正确数据代替
		if (jiaquan > 140 || jiaquan <= 0){
			jiaquan = lastdata.get(0);
		}else{
			lastdata.set(0, jiaquan);
		}
		if (pm25 > 150 || pm25 <= 0){
			pm25 = lastdata.get(1);
		}else{
			lastdata.set(1, pm25);
		}
		if (co2 > 0.3 || co2 <= 0){
			co2 = lastdata.get(2);
		}else{
			lastdata.set(2, co2);
		}
		if (light > 1400 || light <= 0){
			light = lastdata.get(3);
		}else{
			lastdata.set(3, light);
		}
		if (wengdu < -10 || wengdu > 50){
			wengdu = lastdata.get(4);
		}else{
			lastdata.set(4, wengdu);
		}
		if (voice > 1000 || voice < 0){
			voice = lastdata.get(5);
		}else{
			lastdata.set(5, voice);
		}
		//发送到网页服务器
		/*dataweb.clear();
		dataweb.add(String.valueOf(Math.round(jiaquan * 100) / 100.00));
		dataweb.add(String.valueOf(Math.round(pm25 * 100) / 100.00));
		dataweb.add(String.valueOf(Math.round(co2 * 100) / 100.00));
		dataweb.add(String.valueOf(Math.round(light * 100) / 100.00));
		dataweb.add(String.valueOf(Math.round(wengdu * 100) / 100.00));
		if (voice == 0){
			dataweb.add("无");
		}else{
			dataweb.add("有");
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sendtoweb();
			}
		}).start();*/
		//dataweb.add(String.valueOf(Math.round(voice * 100) / 100.00));
		
		//System.out.println(HexTransform.bytesToHexString(back));
		//System.out.println(jiaquan+":"+pm25+":"+co2+":"+light+":"+wengdu+":"+voice);
		while (!activity.isEnableWrite);
		activity.isEnableWrite = false;
		data.clear();
		data.add(jiaquan);
		data.add(pm25);
		data.add(co2);
		data.add(light);
		data.add(wengdu);
		data.add(voice);
		activity.isEnableWrite = true;
	}
	
	private double chuli(double d,int i){
		switch (i){
		case 0:
			return d;//甲醛
		case 1:
			return d;//pm25
		case 2:
			return d/10000;//co2
		case 3:
			return d;//light
		case 4:
			if (d < 2000){//wengdu
				return d/10;
			}else{
				//取反加一
				return -(64*1024 - d);
			}
		case 5:
			return d;//voice
		}
		return 0;
	}
	
	public String getwebdata(int i){
		while (dataweb.size() == 0);
		return dataweb.get(i);
	}
	
	/**
	 * 向网页服务器发送实时数据
	 */
	private void sendtoweb() {
		try {
			HttpPost httpPost = new HttpPost(
					"http://121.43.232.155:8182/EnvironmentalMonitoring/myServlet?submit=update");
			// 以键值对的形式，给要传给post的值进行赋值，将传到该函数的参数放到键值对中，然后下面将键值对提交到post中。
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("c00", this.getwebdata(0)));
			nvps.add(new BasicNameValuePair("c01", this.getwebdata(1)));
			nvps.add(new BasicNameValuePair("c02", this.getwebdata(2)));
			nvps.add(new BasicNameValuePair("c03", this.getwebdata(3)));
			nvps.add(new BasicNameValuePair("c04", this.getwebdata(4)));
			nvps.add(new BasicNameValuePair("c05", this.getwebdata(5)));
			// 必须要加上HTTP.UTF_8这个参数，否则就会出现提交上去是乱码，而导致出错
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public String getSend() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			json.put("flag", "ss");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
}
