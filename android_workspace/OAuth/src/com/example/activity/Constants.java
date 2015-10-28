package com.example.activity;

public class Constants {
	
	//腾讯所分配的APP_KEY
	public static final String CONSUMER_KEY = "";
	//腾讯所分配的APP_SECRET
	public static final String CONSUMER_SECRET = "";
	//用于获取未授权的request token
	public static final String REQUEST_URL = "https://open.t.qq.com/cgi-bin/request_token";
	//用户对未授权的request token进行授权
	public static final String AUTHORIZE_URL = "https://open.t.qq.com/cgi-bin/authorize";
	//用于获取access_token
	public static final String ACCESS_URL = "https://open.t.qq.com/cgi-bin/access_token";
	
	public static final String ENCODING = "UTF-8";
	
	public static final String OAUTH_CALLBACK_SCHEME = "7oauth";
	public static final String OAUTH_CALLBACK_HOST = "callback";
	//毁掉地址
	public static final String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME + OAUTH_CALLBACK_HOST;
}
