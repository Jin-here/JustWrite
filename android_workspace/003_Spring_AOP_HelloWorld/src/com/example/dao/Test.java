package com.example.dao;

public class Test {
	public static void main(String[] args) {
		DaoI daoi = new Dao();
		MyInHandler h = new MyInHandler(daoi);
		DaoI proxydaoi = (DaoI) h.newProxy();
		System.out.println(proxydaoi.getClass().getName());
		
		proxydaoi.add();
	}
}
