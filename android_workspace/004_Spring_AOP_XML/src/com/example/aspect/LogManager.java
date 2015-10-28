package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogManager {
	public LogManager() {
		// TODO Auto-generated constructor stub
		System.out.println("new LogManager()");
	}
	
	public void addLogBefore(){
		System.out.println("�����־��¼Before...");
	}
	
	public void addLogAfter(){
		System.out.println("�����־after...");
	}
	
	public void addLogAfterReturning(){
		System.out.println("�����־��¼AfterReturning...");
	}
	
	public void addLogAfterThrowning(){
		System.out.println("�����־��¼AfterThrowning...");
	}
	
	public void addLogFinal(){
		System.out.println("�����־��¼Final...");
	}
	
	public void addLogAround(ProceedingJoinPoint pjp){
		System.out.println("�����־��¼Around-start...");
		try {
			pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�����־��¼Around-end...");
	}
	
	public void getTip(int tip){
		System.out.println("�����־��¼showTip..." + tip);
	}
}
