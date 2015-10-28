package com.gaw.garbage.util;

import com.gaw.garbage.config.Config;

public class TextEditor {
	/**
	 * 判断内容是否修改过，只要不都是空格，返回true
	 * @param context
	 * @return
	 */
	public static boolean isAll32(String context){
		char[] raw = context.toCharArray();
		for (int i = 0;i < raw.length;i++){
			if (raw[i] == 32 || raw[i] == 13 || raw[i] == 10){
				continue;
			}else{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取文本的前几个字符串
	 * @param context
	 * @return
	 */
	public static String getBrief(String context){
		if ("".equals(context) || context == null){
			return "";
		}
		char[] raw = context.toCharArray();
		int i = 0;
		while (raw[i] == 32){i++;}		
		return new String(raw, i, (raw.length-i) <= 13?(raw.length-i):13);
	}
}
