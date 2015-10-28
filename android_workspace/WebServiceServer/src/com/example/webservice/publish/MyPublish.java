package com.example.webservice.publish;

import javax.xml.ws.Endpoint;

import com.example.webservice.impl.MyWebService;

/**
 * 发布server
 * @author Administrator
 *
 */
public class MyPublish {
	public static void main(String[] args) {
		String address = "http://192.168.1.103:7777/mypublish";
		Endpoint.publish(address, new MyWebService());
		System.out.println("发布成功");
	}
}
