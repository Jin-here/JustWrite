package com.example.webservice.impl;

import javax.jws.WebService;

import com.example.webservice.inter.MyWebServiceInter;

@WebService
public class MyWebService implements MyWebServiceInter {

	@Override
	public String hello(String hello) {
		// TODO Auto-generated method stub
		return "ÄãºÃ";
	}

}
