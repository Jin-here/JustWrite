package com.example.po;

public class IdCard {
	private int id;
	private String cardNum;
	public IdCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IdCard(int id, String cardNum) {
		super();
		this.id = id;
		this.cardNum = cardNum;
	}
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", cardNum=" + cardNum + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
}
