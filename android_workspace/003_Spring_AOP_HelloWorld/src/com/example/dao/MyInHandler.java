package com.example.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInHandler implements InvocationHandler{
	//目标对象
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
		System.out.println("执行需要在dao之前需执行的操作");
		
		Object returnValue = method.invoke(target, args);
		
		System.out.println("执行需要在dao之后需执行的操作");
		return returnValue;
	}

}
