package com.example.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInHandler implements InvocationHandler{
	//Ŀ�����
	private Object target;
	
	public MyInHandler(Object target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	public Object newProxy(){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("ִ����Ҫ��dao֮ǰ��ִ�еĲ���");
		
		Object returnValue = method.invoke(target, args);
		
		System.out.println("ִ����Ҫ��dao֮����ִ�еĲ���");
		return returnValue;
	}

}
