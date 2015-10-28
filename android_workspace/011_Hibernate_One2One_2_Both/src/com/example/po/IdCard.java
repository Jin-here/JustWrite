package com.example.po;

public class IdCard {
	private int id;
	private String cardNum;
	private User user;
	public IdCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IdCard(int id, String cardNum, User user) {
		super();
		this.id = id;
		this.cardNum = cardNum;
		this.user = user;
	}
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", cardNum=" + cardNum + ", user=" + user
				+ "]";
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
