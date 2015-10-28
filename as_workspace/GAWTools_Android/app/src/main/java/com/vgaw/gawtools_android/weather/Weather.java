package com.vgaw.gawtools_android.weather;

import android.util.Log;

import com.vgaw.gawtools_android.weather.catagory.Basic;
import com.vgaw.gawtools_android.weather.catagory.ChuanYi;
import com.vgaw.gawtools_android.weather.catagory.FangShai;
import com.vgaw.gawtools_android.weather.catagory.Forecast;
import com.vgaw.gawtools_android.weather.catagory.GanMao;
import com.vgaw.gawtools_android.weather.catagory.History;
import com.vgaw.gawtools_android.weather.catagory.LiangShai;
import com.vgaw.gawtools_android.weather.catagory.Today;
import com.vgaw.gawtools_android.weather.catagory.XiChe;
import com.vgaw.gawtools_android.weather.catagory.YunDong;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2015/8/4.
 */
public class Weather {

	// private String cityName = "";
	private List<String> cityNameList;

	// 娣囨繂鐡ㄩ幍锟介張澶娿亯濮樻柧淇婇幁锟�
	// private JSONObject json;

	// private HashMap<String, JSONObject> jsonHashMap = new HashMap<String,
	// JSONObject>();

	private ConcurrentHashMap<String, JSONObject> jsonHashMap;

	public Weather() {
		jsonHashMap = new ConcurrentHashMap<String, JSONObject>();
		cityNameList = new ArrayList<String>();
	}

	/**
	 * 閼惧嘲褰囪ぐ鎾炽亯婢垛晜鐨甸崣鍌涙殶
	 * 
	 * @param key
	 * @return
	 */
	private String getValueByKey(JSONObject json, int key) {
		String temp = "";

		switch (key) {
		// 閸╄櫣顢�
		case Basic.CITY:
			temp = getTwo(json, "retData", "city");
			break;
		case Basic.CITY_ID:
			temp = getTwo(json, "retData", "cityid");
			break;
		// 缁岃儻銆傞幐鍥ㄦ殶
		case ChuanYi.CODE:
			temp = getFive(json, "retData", "today", "index", 2, "code");
			break;
		case ChuanYi.DETAILS:
			temp = getFive(json, "retData", "today", "index", 2, "details");
			break;
		case ChuanYi.INDEX:
			temp = getFive(json, "retData", "today", "index", 2, "index");
			break;
		case ChuanYi.NAME:
			temp = getFive(json, "retData", "today", "index", 2, "name");
			break;
		case ChuanYi.OTHERNAME:
			temp = getFive(json, "retData", "today", "index", 2, "othername");
			break;
		// 闂冨弶妾奸幐鍥ㄦ殶
		case FangShai.CODE:
			temp = getFive(json, "retData", "today", "index", 1, "code");
			break;
		case FangShai.DETAILS:
			temp = getFive(json, "retData", "today", "index", 1, "details");
			break;
		case FangShai.INDEX:
			temp = getFive(json, "retData", "today", "index", 1, "index");
			break;
		case FangShai.NAME:
			temp = getFive(json, "retData", "today", "index", 1, "name");
			break;
		case FangShai.OTHERNAME:
			temp = getFive(json, "retData", "today", "index", 1, "othername");
			break;
		// 閹扮喎鍟嬮幐鍥ㄦ殶
		case GanMao.CODE:
			temp = getFive(json, "retData", "today", "index", 0, "code");
			break;
		case GanMao.DETAILS:
			temp = getFive(json, "retData", "today", "index", 0, "details");
			break;
		case GanMao.INDEX:
			temp = getFive(json, "retData", "today", "index", 0, "index");
			break;
		case GanMao.NAME:
			temp = getFive(json, "retData", "today", "index", 0, "name");
			break;
		case GanMao.OTHERNAME:
			temp = getFive(json, "retData", "today", "index", 0, "othername");
			break;
		// 閺呯偓妾奸幐鍥ㄦ殶
		case LiangShai.CODE:
			temp = getFive(json, "retData", "today", "index", 5, "code");
			break;
		case LiangShai.DETAILS:
			temp = getFive(json, "retData", "today", "index", 5, "details");
			break;
		case LiangShai.INDEX:
			temp = getFive(json, "retData", "today", "index", 5, "index");
			break;
		case LiangShai.NAME:
			temp = getFive(json, "retData", "today", "index", 5, "name");
			break;
		case LiangShai.OTHERNAME:
			temp = getFive(json, "retData", "today", "index", 5, "othername");
			break;
		// 濞叉婧呴幐鍥ㄦ殶
		case XiChe.CODE:
			temp = getFive(json, "retData", "today", "index", 4, "code");
			break;
		case XiChe.DETAILS:
			temp = getFive(json, "retData", "today", "index", 4, "details");
			break;
		case XiChe.INDEX:
			temp = getFive(json, "retData", "today", "index", 4, "index");
			break;
		case XiChe.NAME:
			temp = getFive(json, "retData", "today", "index", 4, "name");
			break;
		case XiChe.OTHERNAME:
			temp = getFive(json, "retData", "today", "index", 4, "othername");
			break;
		// 鏉╂劕濮╅幐鍥ㄦ殶
		case YunDong.CODE:
			temp = getFive(json, "retData", "today", "index", 3, "code");
			break;
		case YunDong.DETAILS:
			temp = getFive(json, "retData", "today", "index", 3, "details");
			break;
		case YunDong.INDEX:
			temp = getFive(json, "retData", "today", "index", 3, "index");
			break;
		case YunDong.NAME:
			temp = getFive(json, "retData", "today", "index", 3, "name");
			break;
		case YunDong.OTHERNAME:
			temp = getFive(json, "retData", "today", "index", 3, "othername");
			break;
		// 娴犲﹤銇�
		case Today.DATE:
			temp = getThree(json, "retData", "today", "date");
			break;
		case Today.FEGNXIANG:
			temp = getThree(json, "retData", "today", "fengxiang");
			break;
		case Today.FENGLI:
			temp = getThree(json, "retData", "today", "fengli");
			break;
		case Today.HIGHTEMP:
			temp = getThree(json, "retData", "today", "hightemp");
			break;
		case Today.LOWTEMP:
			temp = getThree(json, "retData", "today", "lowtemp");
			break;
		case Today.TYPE:
			temp = getThree(json, "retData", "today", "type");
			break;
		case Today.WEEK:
			temp = getThree(json, "retData", "today", "week");
			break;
		case Today.AQI:
			temp = getThree(json, "retData", "today", "aqi");
			break;
		case Today.CURTEMP:
			temp = getThree(json, "retData", "today", "curTemp");
			break;
		}
		return temp;

	}

	/**
	 * 閻€劋绨棃鐐茬秼婢垛晛銇夊鏂垮棘閺佹媽骞忛崣鏍电礉娓氬顩ч懢宄板絿閺勫骸銇夐惃鍕刊鎼达拷
	 * 
	 * @param key
	 * @param detail
	 * @return
	 * @throws JSONException
	 */
	private String getValueByKey(JSONObject json, int key, int detail) {
		String temp = "";

		switch (key) {
		// 閸氾拷4婢讹拷
		case Forecast.DATE:
			temp = getFour(json, "retData", "forecast", detail - 1, "date");
			break;
		case Forecast.FEGNXIANG:
			temp = getFour(json, "retData", "forecast", detail - 1, "fengxiang");
			break;
		case Forecast.FENGLI:
			temp = getFour(json, "retData", "forecast", detail - 1, "fengli");
			break;
		case Forecast.HIGHTEMP:
			temp = getFour(json, "retData", "forecast", detail - 1, "hightemp");
			break;
		case Forecast.LOWTEMP:
			temp = getFour(json, "retData", "forecast", detail - 1, "lowtemp");
			break;
		case Forecast.TYPE:
			temp = getFour(json, "retData", "forecast", detail - 1, "type");
			break;
		case Forecast.WEEK:
			temp = getFour(json, "retData", "forecast", detail - 1, "week");
			break;
		// 閺勶拷2婢讹拷
		case History.DATE:
			temp = getFour(json, "retData", "history", detail + 2, "date");
			break;
		case History.FEGNXIANG:
			temp = getFour(json, "retData", "history", detail + 2, "fengxiang");
			break;
		case History.FENGLI:
			temp = getFour(json, "retData", "history", detail + 2, "fengli");
			break;
		case History.HIGHTEMP:
			temp = getFour(json, "retData", "history", detail + 2, "hightemp");
			break;
		case History.LOWTEMP:
			temp = getFour(json, "retData", "history", detail + 2, "lowtemp");
			break;
		case History.TYPE:
			temp = getFour(json, "retData", "history", detail + 2, "type");
			break;
		case History.WEEK:
			temp = getFour(json, "retData", "history", detail + 2, "week");
			break;
		}
		return temp;

	}

	/**
	 * 鐠佸墽鐤嗛崺搴＄閸氬秶袨
	 *
	 * @param cityName
	 */
	/*
	 * private void setCityName(String cityName) { this.cityName = cityName; }
	 */

	public String getWeatherInfo(String cityName, int key) {
		boolean temp = false;
		temp = jsonHashMap.containsKey(cityName);
		if (temp) {
			return getValueByKey(jsonHashMap.get(cityName), key);
		} else {
			// /setCityName(cityName);
			cityNameList.add(cityName);
			int i = cityNameList.size() - 1;
			boolean isSuccess = get(i);
			if (isSuccess) {
				return getValueByKey(jsonHashMap.get(cityName), key);
			} else {
				cityNameList.remove(i);
				return "";
			}
		}
	}

	public String getWeatherInfo(String cityName, int key, int detail) {
		boolean temp = false;
		temp = jsonHashMap.containsKey(cityName);
		if (temp) {
			return getValueByKey(jsonHashMap.get(cityName), key, detail);
		} else {
			// setCityName(cityName);
			cityNameList.add(cityName);
			int i = cityNameList.size() - 1;
			boolean isSuccess = get(i);
			if (isSuccess) {
				return getValueByKey(jsonHashMap.get(cityName), key, detail);
			} else {
				cityNameList.remove(i);
				return "";
			}
		}
	}

	/**
	 * 鎵嬪姩鍒锋柊澶╂皵鏁版嵁
	 * 
	 * @param cityName
	 * @return
	 */
	public boolean fresh(String cityName) {
		boolean isSuccess = false;
		if (jsonHashMap.containsKey(cityName)) {
			int j = -1;
			for (int i = 0; i < cityNameList.size(); i++) {
				if (cityName.equals(cityNameList.get(i))) {
					j = i;
				}
			}
			isSuccess = get(j);
		} else {
			cityNameList.add(cityName);
			int i = cityNameList.size() - 1;
			isSuccess = get(i);
		}
		return isSuccess;
	}

	/**
	 * 閺傛澘绱戠痪璺ㄢ柤閿涘苯褰傞柅浣瑰Г閺傚浄绱濋獮鎯板箯閸欙拷 閹绘劗銇氶敍姘涧闂囷拷鐠嬪啰鏁ゆ稉锟藉▎锟�
	 */
	private boolean get(int i) {
		final String cityName = cityNameList.get(i);
		if ("".equals(cityName)) {
			Log.e("Weather:get()",
					"閸╁骸绔堕崥宥勮礋缁岀尨绱濇担璺ㄦ暏get()閸撳秹娓剁拫鍐暏setCityName()鐠佸墽鐤嗛崺搴＄閸氾拷");
			return false;
		}
		FutureTask<String> future = new FutureTask<String>(
				new Callable<String>() {
					@Override
					public String call() throws Exception {
						String jsonResult = request(cityName);
						return jsonResult;
					}
				});
		new Thread(future).start();
		try {
			boolean isSuccess = false;
			String back = future.get();
			if (!back.equals("")) {
				JSONObject jsonNew = new JSONObject(back);
				if ((jsonNew.getString("errMsg").equals("success"))) {
					jsonHashMap.put(cityName, jsonNew);
					isSuccess = true;
				}
			}
			return isSuccess;
		} catch (Exception e) {
			// e.printStackTrace();
			// 閼惧嘲褰囨径鈺傜毜娣団剝浼呮径杈Е閿涘矁顔曠純顔藉复閸欙綇绱濇禒銉唨閻€劍鍩涚拫鍐暏閿涘奔浜掓径鍕倞婢惰精瑙﹂幆鍛埌
			return false;
		}
	}

	/**
	 * 閼惧嘲褰囨径鈺傜毜娣団剝浼呴崙鑺ユ殶
	 *
	 * @param cityName
	 * @return
	 * @throws Exception
	 *             閼惧嘲褰囨径鈺傜毜娣団剝浼呮径杈Е
	 */
	private String request(String cityName) throws Exception {
		String result = "";

		String httpArg = "cityname=" + URLEncoder.encode(cityName, "UTF-8");

		String httpUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
		String apikey = "885923019af925976beb182a3b794a2e";

		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer("");
		httpUrl = httpUrl + "?" + httpArg;

		URL url = new URL(httpUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		// 婵夘偄鍙哸pikey閸掔檹TTP header
		connection.setRequestProperty("apikey", apikey);
		connection.connect();
		InputStream is = connection.getInputStream();
		reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String strRead = null;
		while ((strRead = reader.readLine()) != null) {
			sbf.append(strRead);
			sbf.append("\r\n");
		}
		reader.close();
		result = sbf.toString();

		return result;
	}

	private String getOne(JSONObject json, String key1) throws JSONException {
		return (String) json.get(key1);
	}

	private String getTwo(JSONObject json, String key1, String key2) {
		try {
			Object result = "";
			JSONObject temp1 = (JSONObject) json.get(key1);
			result = (String) temp1.get(key2);
			return String.valueOf(result);
		} catch (JSONException e) {
			return "";
		}
	}

	private String getThree(JSONObject json, String key1, String key2,
			String key3) {
		try {
			Object result = "";
			JSONObject temp1 = (JSONObject) json.get(key1);
			JSONObject temp2 = (JSONObject) temp1.get(key2);
			result = temp2.get(key3);
			return String.valueOf(result);
		} catch (JSONException e) {
			return "";
		}
	}

	private String getFour(JSONObject json, String key1, String key2,
			int detail, String key4) {
		try {
			Object result = "";
			JSONObject temp1 = (JSONObject) json.get(key1);
			JSONArray temp3 = temp1.getJSONArray(key2);
			JSONObject temp4 = temp3.getJSONObject(detail);
			result = (String) temp4.get(key4);
			return String.valueOf(result);
		} catch (JSONException e) {
			return "";
		}
	}

	private String getFive(JSONObject json, String key1, String key2,
			String key3, int detail, String key4) {
		try {
			Object result = "";
			JSONObject temp1 = (JSONObject) json.get(key1);
			JSONObject temp2 = (JSONObject) temp1.get(key2);
			JSONArray temp3 = temp2.getJSONArray(key3);
			JSONObject temp4 = temp3.getJSONObject(detail);
			result = (String) temp4.get(key4);
			return String.valueOf(result);
		} catch (JSONException e) {
			return "";
		}
	}
}
