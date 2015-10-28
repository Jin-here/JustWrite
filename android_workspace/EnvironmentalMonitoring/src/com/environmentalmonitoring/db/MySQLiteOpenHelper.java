package com.environmentalmonitoring.db;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.environmentalmonitoring.bean.History_Data;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "history_data.db";
	private static final int VERSION = 1;
	
	public static final String TABLE_NAME = "thing";
	public static final String _ID = "_id";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String TIME = "time";
	
	private String[] name = new String[]{"甲醛","pm2.5","co2","光照","温度","声音"};
	
	public static final String CREATE_THING_TABLE = "create table if not exists " + TABLE_NAME
			+ "(" + _ID + " integer primary key autoincrement, " + NAME + " ntext  NOT NULL, "
			+ VALUE + " double  NOT NULL, " + TIME + " integer NOT NULL)";
			
	public static final String DROP_THING_TABLE = "drop table " + TABLE_NAME;
	
	public MySQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_THING_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void insert(ArrayList<Double> data){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		long time = Long.parseLong(formatter.format(new Date()));
		
		for (int i = 0;i < data.size();i++){
			ContentValues cV = new ContentValues();
			cV.put(NAME, name[i]);
			cV.put(VALUE, data.get(i));
			cV.put(TIME, time);
			SQLiteDatabase sd = getReadableDatabase();
			sd.insert(TABLE_NAME, null, cV);
		}
	}
	
	public ArrayList<Double> chart_query(String name,long minus){
		ArrayList<Double> data = new ArrayList<Double>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		long limit_max = Long.parseLong(formatter.format(new Date()));
		long limit_min = limit_max - minus;
		SQLiteDatabase sd = getReadableDatabase();
		Cursor c = sd.query(TABLE_NAME, new String[]{VALUE}, NAME + "='" + name + "' and " + TIME + ">=" + limit_min + " and " + TIME + "<=" + limit_max, null, null, null, null);
		while (c.moveToNext()){
			data.add(c.getDouble(c.getColumnIndex(VALUE)));
		}
		return data;
	}
	
	/**
	 * 删除没用数据
	 * @param maxTime
	 */
	public void fresh(long maxTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		long limit = Long.parseLong(formatter.format(new Date())) - maxTime;
		
		SQLiteDatabase sd = getWritableDatabase();
		sd.delete(TABLE_NAME, TIME + "<" + limit, null);
	}
}
