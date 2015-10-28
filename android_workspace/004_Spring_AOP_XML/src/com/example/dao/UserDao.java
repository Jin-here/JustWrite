package com.example.dao;


public class UserDao implements Dao {
	
	public UserDao() {
		// TODO Auto-generated constructor stub
		System.out.println("new UserDao()");
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		System.out.println("UserDao add...");
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		System.out.println("UserDao delete...");
	}

	@Override
	public int showTip(){
		System.out.println("UserDao showTip...");
		//System.out.println(1/0);
		return 1;
	}
}
