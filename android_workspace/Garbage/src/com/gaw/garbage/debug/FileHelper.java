package com.gaw.garbage.debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.gaw.garbage.bean.Garbage;

import android.os.Environment;
import android.util.Log;

public class FileHelper {
	private File dir;
	private FileOutputStream fos;

	private String PATH = Environment.getExternalStorageDirectory().getPath()
			+ "/com/gaw/garbage/debug/db/";
	private String filename = "";

	public FileHelper(String tablename) {
		// TODO Auto-generated constructor stub
		this.filename = tablename;
		dir = new File(PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public void record(Garbage garbage) {
		try {
			fos = new FileOutputStream(PATH + filename, true);
			String record = garbage.toString() + "\n";
			fos.write(record.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
