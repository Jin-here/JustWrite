package com.example.action;



public class LogManager {
	public LogManager() {
		// TODO Auto-generated constructor stub
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
	
	/*public void addLogAround(ProceedingJoinPoint joinPoint){
		System.out.println("�����־��¼Around start...");
		
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�����־��¼Around end...");
	}*/
}
