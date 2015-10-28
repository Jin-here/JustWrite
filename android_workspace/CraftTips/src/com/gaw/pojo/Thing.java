package com.gaw.pojo;

public class Thing {
	
	private String name;		//物品名称	
	private String jp;			//物品简拼
	private int picture;		//图片R地址
	private int isChild;		//该物品是否能被合成
	private int isShow;			//autocompletetextview输入是否显示
	private String zuoyong;		//武平作用
	private String fenglei;		//物品分类
	private int pic11;			//合成该物品的物品之一
	private int pic12;
	private int pic13;
	private int pic21;
	private int pic22;
	private int pic23;
	private int pic31;
	private int pic32;
	private int pic33;
	public Thing() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Thing(String name, String jp, int picture, int isChild, int isShow,
			String zuoyong, String fenglei, int pic11, int pic12, int pic13,
			int pic21, int pic22, int pic23, int pic31, int pic32, int pic33) {
		super();
		this.name = name;
		this.jp = jp;
		this.picture = picture;
		this.isChild = isChild;
		this.isShow = isShow;
		this.zuoyong = zuoyong;
		this.fenglei = fenglei;
		this.pic11 = pic11;
		this.pic12 = pic12;
		this.pic13 = pic13;
		this.pic21 = pic21;
		this.pic22 = pic22;
		this.pic23 = pic23;
		this.pic31 = pic31;
		this.pic32 = pic32;
		this.pic33 = pic33;
	}
	@Override
	public String toString() {
		return "Thing [name=" + name + ", jp=" + jp + ", picture=" + picture
				+ ", isChild=" + isChild + ", isShow=" + isShow + ", zuoyong="
				+ zuoyong + ", fenglei=" + fenglei + ", pic11=" + pic11
				+ ", pic12=" + pic12 + ", pic13=" + pic13 + ", pic21=" + pic21
				+ ", pic22=" + pic22 + ", pic23=" + pic23 + ", pic31=" + pic31
				+ ", pic32=" + pic32 + ", pic33=" + pic33 + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJp() {
		return jp;
	}
	public void setJp(String jp) {
		this.jp = jp;
	}
	public int getPicture() {
		return picture;
	}
	public void setPicture(int picture) {
		this.picture = picture;
	}
	public int getIsChild() {
		return isChild;
	}
	public void setIsChild(int isChild) {
		this.isChild = isChild;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public String getZuoyong() {
		return zuoyong;
	}
	public void setZuoyong(String zuoyong) {
		this.zuoyong = zuoyong;
	}
	public String getFenglei() {
		return fenglei;
	}
	public void setFenglei(String fenglei) {
		this.fenglei = fenglei;
	}
	public int getPic11() {
		return pic11;
	}
	public void setPic11(int pic11) {
		this.pic11 = pic11;
	}
	public int getPic12() {
		return pic12;
	}
	public void setPic12(int pic12) {
		this.pic12 = pic12;
	}
	public int getPic13() {
		return pic13;
	}
	public void setPic13(int pic13) {
		this.pic13 = pic13;
	}
	public int getPic21() {
		return pic21;
	}
	public void setPic21(int pic21) {
		this.pic21 = pic21;
	}
	public int getPic22() {
		return pic22;
	}
	public void setPic22(int pic22) {
		this.pic22 = pic22;
	}
	public int getPic23() {
		return pic23;
	}
	public void setPic23(int pic23) {
		this.pic23 = pic23;
	}
	public int getPic31() {
		return pic31;
	}
	public void setPic31(int pic31) {
		this.pic31 = pic31;
	}
	public int getPic32() {
		return pic32;
	}
	public void setPic32(int pic32) {
		this.pic32 = pic32;
	}
	public int getPic33() {
		return pic33;
	}
	public void setPic33(int pic33) {
		this.pic33 = pic33;
	}
	
	
	
	
	
}
