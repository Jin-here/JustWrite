package com.example.aspect;

public class MyAdvice implements ExtraAdvice {

	@Override
	public void sell(String thing, String customer) {
		// TODO Auto-generated method stub
		System.out.println("成功卖给" + customer + "商品：" + thing);
	}

}
