package com.example.webservice.inter;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * SEI
 * @author Administrator
 *
 */
@WebService
public interface MyWebServiceInter {
	@WebMethod
	public String hello(String hello);
}
