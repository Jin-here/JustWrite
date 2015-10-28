package com.gaw.db;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.util.Log;

import com.gaw.datafactory.DataFactory;
import com.gaw.pojo.Thing;
import com.gaw.stastics.DBINFO;
import com.gaw.stastics.THING;


public class MyHelper extends SQLiteOpenHelper {

	public MyHelper(Context context) {
		super(context, DBINFO.DB_NAME, null, DBINFO.VERSION);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(THING.CREATE_THING_TABLE);
		
		DataFactory df = new DataFactory();
		int firstaddnum = df.getFirstaddnum();
		List<Thing> things = df.getThings_first();
		
		
		if (firstaddnum!=0){
			//首次添加数据
			for (int i = 0;i < firstaddnum;i++){
				ContentValues cv = new ContentValues();
				Thing thing = things.get(i);
				cv.put(THING.NAME, things.get(i).getName());
				cv.put(THING.JP, thing.getJp());
				cv.put(THING.PICTURE, thing.getPicture());
				cv.put(THING.IS_CHILD, thing.getIsChild());
				cv.put(THING.IS_SHOW, thing.getIsShow());
				cv.put(THING.ZUOYONG, thing.getZuoyong());
				cv.put(THING.FENGLEI, thing.getFenglei());
				cv.put(THING.PIC11, thing.getPic11());
				cv.put(THING.PIC12, thing.getPic12());
				cv.put(THING.PIC13, thing.getPic13());
				cv.put(THING.PIC21, thing.getPic21());
				cv.put(THING.PIC22, thing.getPic22());
				cv.put(THING.PIC23, thing.getPic23());
				cv.put(THING.PIC31, thing.getPic31());
				cv.put(THING.PIC32, thing.getPic32());
				cv.put(THING.PIC33, thing.getPic33());
				//保证数据不都为空，设置ischild为null;
				db.insert(THING.TABLE_NAME, THING.IS_CHILD, cv);
				
			}
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		/*db.execSQL(THING.DROP_THING_TABLE);
		onCreate(db);*/
		
		DataFactory df = new DataFactory();
		int updateaddnum = df.getUpdateaddnum();
		int xiugainum = df.getXiugainum();
		int shanchunum = df.getShanchunum();
		
		
		/**************************添加数据***************************/
		if (updateaddnum!=0){
			List<Thing> things = df.getThings_update();
			for (int i = 0;i < updateaddnum;i++){
				ContentValues cv = new ContentValues();
				Thing thing = things.get(i);
				cv.put(THING.NAME, things.get(i).getName());
				cv.put(THING.JP, thing.getJp());
				cv.put(THING.PICTURE, thing.getPicture());
				cv.put(THING.IS_CHILD, thing.getIsChild());
				cv.put(THING.IS_SHOW, thing.getIsShow());
				cv.put(THING.ZUOYONG, thing.getZuoyong());
				cv.put(THING.FENGLEI, thing.getFenglei());
				cv.put(THING.PIC11, thing.getPic11());
				cv.put(THING.PIC12, thing.getPic12());
				cv.put(THING.PIC13, thing.getPic13());
				cv.put(THING.PIC21, thing.getPic21());
				cv.put(THING.PIC22, thing.getPic22());
				cv.put(THING.PIC23, thing.getPic23());
				cv.put(THING.PIC31, thing.getPic31());
				cv.put(THING.PIC32, thing.getPic32());
				cv.put(THING.PIC33, thing.getPic33());
				//保证数据不都为空，设置ischild为null;
				db.insert(THING.TABLE_NAME, THING.IS_CHILD, cv);
				
			}
		}
		/**************************添加数据***************************/
		
		/**************************修改数据***************************/
		if (xiugainum!=0){
			List<Thing> things = df.Things_xiugai();
			for (int i = 0;i < xiugainum;i++){
				ContentValues cv = new ContentValues();
				Thing thing = things.get(i);
				String name = things.get(i).getName();
				cv.put(THING.NAME, things.get(i).getName());
				cv.put(THING.JP, thing.getJp());
				cv.put(THING.PICTURE, thing.getPicture());
				cv.put(THING.IS_CHILD, thing.getIsChild());
				cv.put(THING.IS_SHOW, thing.getIsShow());
				cv.put(THING.ZUOYONG, thing.getZuoyong());
				cv.put(THING.FENGLEI, thing.getFenglei());
				cv.put(THING.PIC11, thing.getPic11());
				cv.put(THING.PIC12, thing.getPic12());
				cv.put(THING.PIC13, thing.getPic13());
				cv.put(THING.PIC21, thing.getPic21());
				cv.put(THING.PIC22, thing.getPic22());
				cv.put(THING.PIC23, thing.getPic23());
				cv.put(THING.PIC31, thing.getPic31());
				cv.put(THING.PIC32, thing.getPic32());
				cv.put(THING.PIC33, thing.getPic33());
				//保证数据不都为空，设置ischild为null;
				db.update(THING.TABLE_NAME, cv, THING.NAME + "=?", new String[]{name});
				
			}
		}
		/**************************修改数据***************************/
		
		/**************************删除数据***************************/
		if (shanchunum!=0){
			List<Thing> things = df.Things_shanchu();
			for (int i = 0;i < shanchunum;i++){
				String name = things.get(i).getName();			
				
				db.delete(THING.TABLE_NAME, THING.NAME + "=?", new String[]{name});
				
			}
		}
		/**************************删除数据***************************/
	}
	
	
	/**
	 * 点击图片来查询是否有子合成
	 * @param pic
	 * @return
	 */
	public Cursor querybypic(int pic) {
		// TODO Auto-generated method stub
		SQLiteDatabase sd = getReadableDatabase();
		
		Cursor c = sd.query(THING.TABLE_NAME, null, THING.PICTURE + "= ?", new String[]{String.valueOf(pic)}, null, null, null);
		
		
		return c;
	}
	
	/**
	 * 点击autocompletetextview每个item,通过item的name进行查询
	 * @param name
	 * @return
	 */
	public Cursor querybyname(String name) {
		// TODO Auto-generated method stub
		SQLiteDatabase sd = getReadableDatabase();
		
		Cursor c = sd.query(THING.TABLE_NAME, null, THING.NAME + "= ?", new String[]{name}, null, null, null);
		
		
		return c;
	}
	
	/**
	 * 输入简拼搜索相关物品
	 * @param jp
	 * @return
	 */
	public Cursor autoquery(String jp) {
		// TODO Auto-generated method stub
		SQLiteDatabase sd = getReadableDatabase();
		Log.i("hello","hello");
		Cursor c = sd.query(THING.TABLE_NAME, null, "(" + THING.JP + " like ? or " + THING.FENGLEI + "=?)" + " and " + THING.IS_SHOW + "=1", new String[]{"%"+jp+"%",jp}, null, null, null);
		while (c.moveToNext()){
			
			
			Log.i("hello",c.getString(c.getColumnIndex(THING.JP)));
		}
		
		
		return c;
	}

}
