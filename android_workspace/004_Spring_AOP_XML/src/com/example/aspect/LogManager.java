package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogManager {
	public LogManager() {
		// TODO Auto-generated constructor stub
		System.out.println("new LogManager()");
	}
	
	public void addLogBefore(){
		System.out.println("添加日志记录Before...");
	}
	
	public void addLogAfter(){
		System.out.println("添加日志after...");
	}
	
	public void addLogAfterReturning(){
		System.out.println("添加日志记录AfterReturning...");
	}
	
	public void addLogAfterThrowning(){
		System.out.println("添加日志记录AfterThrowning...");
	}
	
	public void addLogFinal(){
		System.out.println("添加日志记录Final...");
	}
	
	public void addLogAround(ProceedingJoinPoint pjp){
		System.out.println("添加日志记录Around-start...");
		try {
			pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("添加日志记录Around-end...");
	}
	
	public void getTip(int tip){
		System.out.println("添加日志记录showTip..." + tip);
	}
}
