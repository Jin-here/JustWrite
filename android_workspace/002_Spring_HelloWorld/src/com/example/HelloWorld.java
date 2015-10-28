package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorld {
	private String name;

	public void setName(String name) {
		this.name = name;
	}
	
	public void show(){
		System.out.println("Hello World show...");
	}
	
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		HelloWorld hello = factory.getBean(HelloWorld.class);
		System.out.println(hello.name);
	}
}
