package com.vgaw.colored_status_bar.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DateTools {
	//yyyy-MM-dd HH:mm:ss.SSSZ
	public static String getTimeS(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		return format.format(new Date());
	}
	
	public static long getTimeL(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		return Long.parseLong(format.format(new Date()));
	}
	
	/**
	 * �������磺[2015��,07��04��,17ʱ03��39��,������ ]
	 * @return
	 */
	public static ArrayList<String> getChilds(){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss�� E ");    
        String raw = dateformat.format(new Date());
        ArrayList<String> childs = new ArrayList<String>();
        childs.add(raw.substring(0, 5));
        childs.add(raw.substring(5, 11));
        childs.add(raw.substring(12, 21));
        childs.add(raw.substring(22, raw.length()));
        return childs;
	}
	
	/*public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
	}*/
}
