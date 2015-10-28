package com.example.dao;


public class ThingDao implements Dao {

	private int count = 0;
	private int price = 0;
	private int discount = 0;
	
	public ThingDao() {
		// TODO Auto-generated constructor stub
		System.out.println("new ThingDao()");
	}
	
	public ThingDao(int count){
		this.count = count;
		System.out.println("new ThingDao(int count)");
	}
	
	public ThingDao(int count, int price){
		System.out.println("new ThingDao(int count, int price)");
		this.count = count;
		this.price = price;
	}
	
	public ThingDao(int count, int price, int discount){
		this.count = count;
		this.price = price;
		this.discount = discount;
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		System.out.println("ThingDao add...\nprice:" + price + "\ncount:"
				+ count);
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		System.out.println("ThingDao delete...");
	}

	@Override
	public int showTip() {
		// TODO Auto-generated method stub
		return 0;
	}

}
