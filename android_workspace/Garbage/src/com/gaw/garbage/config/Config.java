package com.gaw.garbage.config;

public class Config {
	////////////////////////���ݿ�/////////////////////////////
	///////////////////////��һ///////////////	
	public static int DB_VERSION = 1;
	public static final String DB_NAME = "garbage.db";
	
	public static final String TABLE_NAME = "fuck00";
	public static final String _ID = "_id";
	public static final String FILENAME = "filename";
	public static final String HASFILE = "hasfile";
	public static final String BRIEF = "brief";
	public static final String CONTEXT = "context";
	public static final String TIME = "time";
	
	public static final String CREATE_FUCK00_TABLE = "create table if not exists " + TABLE_NAME
			+ "(" + _ID + " integer primary key autoincrement, " + FILENAME + " ntext  NOT NULL, "
			+ HASFILE + " integer NOT NULL, " + BRIEF + " ntext NOT NULL, " + CONTEXT + " ntext NOT NULL, "
			+ TIME + " integer NOT NULL"+")";
	
	public static final long TIME_MAX = 99999999999999L;
	///////////////////////��һ///////////////
	
	///////////////////////��2///////////////
	//ʹ�õص㣺add   :�½��ݸ屣��û��Ŀ¼���½��ݸ屣����Ŀ¼
	//ʹ�õص㣺update:�Ѵ�ݸ崴��Ŀ¼
	//ģʽ��_id,filename,hasfile,brief,time(���磺06/01/2014)
	public static final String TABLE_NAME01 = "fuck01";
	
	public static final String CREATE_FUCK01_TABLE = "create table if not exists " + TABLE_NAME01
			+ "(" + _ID + " integer primary key autoincrement, " + FILENAME + " ntext NOT NULL, "
			+ HASFILE + " integer NOT NULL, " + BRIEF + " ntext, " + TIME + " ntext NOT NULL" + ")";
	///////////////////////��2///////////////
	
	////////////////////////���ݿ�/////////////////////////////
	
	
	////////////////////////�ݸ�ֽ����/////////////////////////////
	public static final int CONTEXT_LENGTH = 20;
	public static final int BRIEF_LENGTH = 13;
	////////////////////////�ݸ�ֽ����/////////////////////////////	
}
