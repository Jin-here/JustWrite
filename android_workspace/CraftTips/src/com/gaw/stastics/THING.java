package com.gaw.stastics;

public class THING {
	public static final String TABLE_NAME = "thing";
	
	public static final String _ID = "_id";
	public static final String NAME = "name";
	public static final String JP = "jp";
	public static final String PICTURE = "picture";
	public static final String IS_CHILD = "ischild";
	public static final String IS_SHOW = "isshow";
	public static final String ZUOYONG = "zuoyong";
	public static final String FENGLEI = "fenglei";
	public static final String PIC11 = "pic11";
	public static final String PIC12 = "pic12";
	public static final String PIC13 = "pic13";
	public static final String PIC21 = "pic21";
	public static final String PIC22 = "pic22";
	public static final String PIC23 = "pic23";
	public static final String PIC31 = "pic31";
	public static final String PIC32 = "pic32";
	public static final String PIC33 = "pic33";
	
	public static final String CREATE_THING_TABLE = "create table if not exists " + TABLE_NAME
			+ "(" + _ID + " integer primary key autoincrement, " + NAME + " nvchar, "
			+ JP + " nvchar, " + PICTURE + " integer, " + IS_CHILD + " integer, " + IS_SHOW + " integer, " + ZUOYONG + " nvchar, "
			+ FENGLEI + " nvchar, " + PIC11 + " nvchar, " + PIC12 + " nvchar, " + PIC13 + " nvchar, " + PIC21 + " nvchar, "
			+ PIC22 + " nvchar, " + PIC23 + " nvchar, " + PIC31 + " nvchar, " + PIC32 + " nvchar, "
			+ PIC33 + " nvchar)";
	
	public static final String DROP_THING_TABLE = "drop table " + TABLE_NAME;
}
