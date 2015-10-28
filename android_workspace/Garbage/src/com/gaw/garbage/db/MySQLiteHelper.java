package com.gaw.garbage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.gaw.garbage.bean.Garbage;
import com.gaw.garbage.config.Config;
import com.gaw.garbage.debug.FileHelper;
import com.gaw.garbage.fragment.Fragment00;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public MySQLiteHelper(Context context) {
		super(context, Config.DB_NAME, null, Config.DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(Config.CREATE_FUCK00_TABLE);
		db.execSQL(Config.CREATE_FUCK01_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	private Garbage garbage = Fragment00.garbage;

	/**
	 * 获取左边一个草稿 select * from tablename where id>nowId order by id asc
	 * limit 0 1;
	 * 
	 * @return
	 */
	public Garbage getLeftGarbage(long nowId) {
		// 执行动作之前，先把garbage传到garbage_last;
		Fragment00.garbage_last = new Garbage(Fragment00.garbage);

		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(Config.TABLE_NAME, null, Config._ID + ">"
				+ nowId, null, null, null, Config._ID + " asc", "1");
		if (c == null || c.getCount() == 0) {
			garbage.clear();
			Log.e("error", "getleftgarbage:null");
			return null;
		}
		if (c.getCount() > 1) {
			Log.e("error", "getleftgarbage:count>1");
			return null;
		}
		while (c.moveToNext()) {
			garbage.setId(c.getLong(c.getColumnIndex(Config._ID)));
			garbage.setFilename(c.getString(c.getColumnIndex(Config.FILENAME)));
			garbage.setHasFile(c.getShort(c.getColumnIndex(Config.HASFILE)) == 1 ? true
					: false);
			garbage.setBrief(c.getString(c.getColumnIndex(Config.BRIEF)));
			garbage.setContext(c.getString(c.getColumnIndex(Config.CONTEXT)));
			garbage.setTime(c.getLong(c.getColumnIndex(Config.TIME)));
		}
		return garbage;
	}

	/**
	 * 获取右边一个草稿 select * from tablename where id<nowId order by id desc limit 0 1;
	 * 
	 * @return
	 */
	public Garbage getRightGarbage(long nowId) {
		

		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(Config.TABLE_NAME, null, Config._ID + "<"
				+ nowId, null, null, null, Config._ID + " desc", "1");
		if (c == null || c.getCount() == 0) {
			Log.e("error", "getrightgarbage:null");
			return null;
		}
		if (c.getCount() > 1) {
			Log.e("error", "getrightgarbage:count>1");
			return null;
		}
		// 执行动作之前，先把garbage传到garbage_last;
		Fragment00.garbage_last = new Garbage(garbage);
		while (c.moveToNext()) {
			garbage.setId(c.getLong(c.getColumnIndex(Config._ID)));
			garbage.setFilename(c.getString(c.getColumnIndex(Config.FILENAME)));
			garbage.setHasFile(c.getShort(c.getColumnIndex(Config.HASFILE)) == 1 ? true
					: false);
			garbage.setBrief(c.getString(c.getColumnIndex(Config.BRIEF)));
			garbage.setContext(c.getString(c.getColumnIndex(Config.CONTEXT)));
			garbage.setTime(c.getLong(c.getColumnIndex(Config.TIME)));
		}
		return garbage;
	}

	/**
	 * 添加新草稿
	 * 
	 * @param garbage
	 */
	public long addGarbage(Garbage garbage) {
		ContentValues mCV = new ContentValues();
		mCV.put(Config.FILENAME, garbage.getFilename());
		mCV.put(Config.HASFILE, garbage.isHasFile()==true?1:0);
		mCV.put(Config.BRIEF, garbage.getBrief());
		mCV.put(Config.CONTEXT, garbage.getContext());
		mCV.put(Config.TIME, garbage.getTime());

		SQLiteDatabase db = getWritableDatabase();
		Log.e("error", "add-info:" + garbage.toString());
		return db.insert(Config.TABLE_NAME, null, mCV);
		
	}

	/**
	 * 修改草稿
	 * 
	 * @param garbage
	 */
	public void updateGarbage(Garbage garbage) {
		ContentValues mCV = new ContentValues();
		mCV.put(Config.FILENAME, garbage.getFilename());
		mCV.put(Config.HASFILE, garbage.isHasFile()==true?1:0);
		mCV.put(Config.BRIEF, garbage.getBrief());
		mCV.put(Config.CONTEXT, garbage.getContext());
		mCV.put(Config.TIME, garbage.getTime());

		SQLiteDatabase db = getWritableDatabase();
		db.update(Config.TABLE_NAME, mCV, Config._ID + "=" + garbage.getId(),
				null);
		Log.e("error", "update-info:" + garbage.toString());
	}
	
	/**
	 * 根据文件夹名称获取文件列表
	 * @param filename
	 * @return
	 */
	public Cursor getCatalog(String filename){
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(true, Config.TABLE_NAME, null, Config.FILENAME + "='" + filename + "'", null, null, null, null, null);
		if (c.getCount() == 0 || c == null){
			Log.e("error", "getcatalog:no file found");
			return null;
		}
		return c;
	}
	
	/**
	 * 根据文件ID获取文件，不可根据brief，可能不同context，但brief相同
	 * @param id
	 * @return
	 */
	public Garbage getGarbageById(long id){
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(Config.TABLE_NAME, null, Config._ID + "=" + id, null, null, null, null);
		if (c == null || c.getCount() == 0) {
			Log.e("error", "getcontextbyid:null");
			return null;
		}
		if (c.getCount() > 1) {
			Log.e("error", "getcontextbyid:count>1");
			return null;
		}
		//Garbage garbage = new Garbage();
		while (c.moveToNext()){
			garbage.setId(c.getLong(c.getColumnIndex(Config._ID)));
			garbage.setFilename(c.getString(c.getColumnIndex(Config.FILENAME)));
			garbage.setBrief(c.getString(c.getColumnIndex(Config.BRIEF)));
			garbage.setHasFile(c.getShort(c.getColumnIndex(Config.HASFILE)) == 1 ? true
					: false);
			garbage.setContext(c.getString(c.getColumnIndex(Config.CONTEXT)));
			garbage.setTime(c.getLong(c.getColumnIndex(Config.TIME)));
		}
		return garbage;
	}
	
	/**
	 * 全部取出 
	 */
	public Cursor getRootCatalog(){
		SQLiteDatabase db = getWritableDatabase();
		
		Cursor c = db.query(Config.TABLE_NAME01, null, null, null, null, null, null);		
		if (c == null || c.getCount() == 0){
			Log.e("error", "getrootcatalog:nodatafound");
			return null;
		}
		return c;
	}
	
	public long addCatalog(Garbage garbage){
		//记录到SD卡
		//FileHelper filehelper = new FileHelper("fuck01");
		//filehelper.record(garbage);
		
		
		//有filename,将filename和hasfile放入01表，没有则将id和hasfile和brief和time放入01表
		SQLiteDatabase db = getWritableDatabase();
		if (!"".equals(garbage.getFilename())){
			ContentValues mCV = new ContentValues();
			mCV.put(Config.FILENAME, garbage.getFilename());
			mCV.put(Config.HASFILE, garbage.isHasFile()==true?1:0);			
			return db.insert(Config.TABLE_NAME01, null, mCV);
		}else{
			ContentValues mCV = new ContentValues();
			mCV.put(Config.FILENAME, garbage.getId());
			mCV.put(Config.HASFILE, garbage.isHasFile()==true?1:0);
			mCV.put(Config.BRIEF, garbage.getBrief());
			mCV.put(Config.TIME, timePro(garbage.getTime()));
			return db.insert(Config.TABLE_NAME01, null, mCV);
		}
	}
	
	public void updateCatalog(Garbage garbage){
		//1：更新filename，hasfile = 1，根据旧的filename来更新新的filename;
		//2：更新brief，即虽然没有文件夹，但内容改变，hasfile = 0;
		//3：原本没有filename，后来又filename;
		SQLiteDatabase db = getWritableDatabase();
		if (!garbage.isHasFile()){
			ContentValues mCV = new ContentValues();
			mCV.put(Config.BRIEF, garbage.getBrief());
			mCV.put(Config.TIME, timePro(garbage.getTime()));			
			db.update(Config.TABLE_NAME01, mCV, Config.HASFILE + "= 0 and " + Config.FILENAME + "='" + garbage.getId() + "'", null);
		}
	}
	
	/**
	 * 内部函数，处理时间
	 * 将yyyyMMddHHmmss转化
	 * 为06/01/2014
	 * @param time
	 * @return
	 */
	private String timePro(long time){
		if (time == 0){
			Log.e("error", "timepro:timelength=1");
		}
		String raw = String.valueOf(time);
		String year = raw.substring(0, 4);
		String month = raw.substring(4, 6);
		String day = raw.substring(6, 8);
		return month + "/" + day + "/" + year;
	}
}
