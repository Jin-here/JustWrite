package com.example.aspect;

public class LogManager {
	public LogManager() {
		// TODO Auto-generated constructor stub
	}

	public void addLogBefore() {
		System.out.println("添加日志记录Before...");
	}

	public void addLogAfter() {
		System.out.println("添加日志after...");
	}

	public void addLogAfterReturning() {
		System.out.println("添加日志记录AfterReturning...");
	}

	public void addLogAfterThrowning() {
		System.out.println("添加日志记录AfterThrowning...");
	}

	/*public void addLogAround(ProceedingJoinPoint joinPoint) {
		System.out.println("添加日志记录Around start...");

		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("添加日志记录Around end...");
	}*/
}
