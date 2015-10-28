package com.environmentalmonitoring.parse;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.bean.MachineStatus;
import com.environmentalmonitoring.db.MySQLiteOpenHelper;
import com.environmentalmonitoring.util.HexTransform;

public class Parse {
	public static MachineStatus BackStatusParse(String back){
		MachineStatus status = new MachineStatus();
		return status;
	}
	
	//�ڲ�����
	private static double baoliuxiaoshu(double d){
		return Math.round(d*100)/100.00;
	}
	
	//3��ȩ��λ
	//4��ȩ��λ
	//5pm2.5��λ
	//6pm2.5��λ
	//7co2��λ
	//8co2��λ
	//9���ո�λ
	//10���յ�λ
	//11�¶ȸ�λ
	//12�¶ȵ�λ
	//13������λ
	//14������λ
	public static ArrayList<Double> BackSensorDataParse(byte[] back){	
		double jiaquan = (back[3]&0xff)*256 + back[4]&0xff;
		double pm25    = (back[5]&0xff)*256 + back[6]&0xff;
		double co2    = (back[7]&0xff)*256 + back[8]&0xff;
		double light    = (back[9]&0xff)*256 + back[10]&0xff;
		double wengdu    = (back[11]&0xff)*256 + back[12]&0xff;
		double voice    = (back[13]&0xff)*256 + back[14]&0xff;		
		System.out.println(jiaquan+":"+pm25+":"+co2+":"+light+":"+wengdu+":"+voice);
		ArrayList<Double> data = new ArrayList<Double>();		
		data.add(chuli(jiaquan,0));
		data.add(chuli(pm25,1));
		data.add(chuli(co2,2));
		data.add(chuli(light,3));
		data.add(chuli(wengdu,4));
		data.add(chuli(voice,5));		
		
		return data;
	}
	
	private static double jiexihexchar(char c){
		int result = 0;
		if (c >= '0' && c <= '9'){
			result = Integer.parseInt(String.valueOf(c));
		}else{
			switch(c){
			case 'a':
				result = 10;
				break;
			case 'b':
				result = 11;
				break;
			case 'c':
				result = 12;
				break;
			case 'd':
				result = 13;
				break;
			case 'e':
				result = 14;
				break;
			case 'f':
				result = 15;
				break;
				default:
					result = 0;
			}
		}
		return result;
	}
	
	private static double chuli(double d,int i){
		switch (i){
		case 0:
			return d/100*0.12;//��ȩ
		case 1:
			return d;//pm25
		case 2:
			return d/10000;//co2
		case 3:
			return d;//light
		case 4:
			if (d < 2000){//wengdu
				return d/10;
			}else{
				//ȡ����һ
				return -(64*1024 - d);
			}
		case 5:
			return d;//voice
		}
		return 0;
	}
}
