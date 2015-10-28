package com.environmentalmonitoring.config;

public class Config {
		/**************************ͨ��************************************************/
		//����ͨ�ŷ�ʽ��http����socket
		public static final String COMMUN_TYPE = "socket";
		//Э�����ͣ�jason����soap
		public static final String PROTOCOL_TYPE = "jason";
		
		//��������ַ
		//public static String HTTP_SERVER_IP = "127.0.0.1";
		//public static String HTTP_SERVER_IP = "192.168.1.210";
		public static String HTTP_SERVER_IP = "192.168.1.101";
		
		//��ʱʱ��
		public static final int TIME_OUT = 2000;
		
		//http�������˿�
		public static int HTTP_SERVER_PORT = 8000;
		//public static int HTTP_SERVER_PORT = 7777;
		
		//notification�������˿�
		public static final int NOTIFI_SERVER_PORT = 8892;
		
		/******************ָ��******************************************/
		public static final long GET_DATA_DELAY = 1000;//��ʱ1s
		
		/******************ָ��******************************************/
		
		
		
		/******************Message��ʶ***********************************/
		public static final int GET_VALUE_WHAT = 0x10;
		public static final int TIME_OUT_WHAT = 0x13;
		public static final int EXCEPTION_WHAT = 0x15;
		public static final int GET_STATUS_WHAT = 0x11;
		public static final int LOGIN_WHAT = 0x12;
		public static final int EXIT_WHAT = 0x14;
		public static final int REGIS_WHAT = 0x16;
		public static final int FIND_PSW_WHAT = 0x17;
		public static final int SET_PSW_WHAT = 0x18;
		/******************Message��ʶ***********************************/
		//����ͼ����棬��ʾһҳ��ʾ�����ݵ����
		public static final int CHART_MAX_POINT = 8;
		/**************************ͨ��***************************************************/
		
		/**************************TabHost************************************/
		public static final int PAGE_NUM = 4;
		public static final int START_PAGE = 0;
		/**************************TabHost************************************/
		
		/*************************����������**************************************/
		public static final String MACHINE00_NAME = "machine00";
		public static final String MACHINE01_NAME = "machine01";
		public static final String MACHINE02_NAME = "machine02";
		public static final String MACHINE03_NAME = "machine03";
		public static final String[] MACHINE_NAME = new String[]{MACHINE00_NAME,MACHINE01_NAME,
			MACHINE02_NAME,MACHINE03_NAME};
		/*************************����������**************************************/
		
		/*************************��ǩ**************************************/
		//key
		public static final String SENSOR00_MIN = "sensor00_min";
		public static final String SENSOR00_MAX = "sensor00_max";
		public static final String SENSOR01_MIN = "sensor01_min";
		public static final String SENSOR01_MAX = "sensor01_max";
		public static final String SENSOR02_MIN = "sensor02_min";
		public static final String SENSOR02_MAX = "sensor02_max";
		public static final String SENSOR03_MIN = "sensor03_min";
		public static final String SENSOR03_MAX = "sensor03_max";
		public static final String SENSOR04_MIN = "sensor04_min";
		public static final String SENSOR04_MAX = "sensor04_max";
		public static final String SENSOR05_MIN = "sensor05_min";
		public static final String SENSOR05_MAX = "sensor05_max";
		
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String RECODE = "recode";
		
		public static final String SERVER_IP = "server_ip";
		public static final String SERVER_PORT = "server_port";
		//value
		public static final float SENSOR00_MIN_VALUE = 0;
		public static final float SENSOR00_MAX_VALUE = (float) 67;
		public static final float SENSOR01_MIN_VALUE = 0;
		public static final float SENSOR01_MAX_VALUE = 75;
		public static final float SENSOR02_MIN_VALUE = 0;
		public static final float SENSOR02_MAX_VALUE = (float) 0.15;
		public static final float SENSOR03_MIN_VALUE = 150;
		public static final float SENSOR03_MAX_VALUE = 700;
		public static final float SENSOR04_MIN_VALUE = 10;
		public static final float SENSOR04_MAX_VALUE = 32;
		public static final float SENSOR05_MIN_VALUE = 0;
		public static final float SENSOR05_MAX_VALUE = 100;
		
		//key
		public static final String SENSOR00 = "sensor00";
		public static final String SENSOR01 = "sensor01";
		public static final String SENSOR02 = "sensor02";
		public static final String SENSOR03 = "sensor03";
		public static final String SENSOR04 = "sensor04";
		public static final String SENSOR05 = "sensor05";
		/*************************��ǩ**************************************/
		
		/*************************ָ��**************************************/
		//machine��
		public static final byte[] MACHINE00_OPEN = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x00,(byte)0xff,(byte)0x00,(byte)0x8c,(byte)0x3a};
		public static final byte[] MACHINE01_OPEN = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x01,(byte)0xff,(byte)0x00,(byte)0xdd,(byte)0xfa};
		public static final byte[] MACHINE02_OPEN = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x02,(byte)0xff,(byte)0x00,(byte)0x2d,(byte)0xfa};
		public static final byte[] MACHINE03_OPEN = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x03,(byte)0xff,(byte)0x00,(byte)0x7c,(byte)0x3a};
		
		//machine��
		public static final byte[] MACHINE00_CLOSE = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xcd,(byte)0xca};
		public static final byte[] MACHINE01_CLOSE = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x9c,(byte)0x0a};
		public static final byte[] MACHINE02_CLOSE = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x6c,(byte)0x0a};
		public static final byte[] MACHINE03_CLOSE = new byte[]{(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x3d,(byte)0xca};
		
		//��ȡʵʱ���� 01 03 00 00 00 17 05 C4
		public static final byte[] GET_SENSOR_DATA  = new byte[]{(byte)0x01,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x17,(byte)0x05,(byte)0xc4};
		//����timeout��
		//public static final byte[] GET_SENSOR_DATA  = new byte[]{(byte)0x00,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0c,(byte)0x45,(byte)0xcf};
		
		//��ȡmachine����״̬
		public static final byte[] GET_MACHINE_STATUS = new byte[]{(byte)0x01,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0c,(byte)0x45,(byte)0xcf};
		
		//����machine��ֵ
		public static final byte[] SET_MACHINE00_MAX = new byte[]{};
		public static final byte[] SET_MACHINE00_MIN = new byte[]{};
		
		public static final byte[] SET_MACHINE01_MAX = new byte[]{};
		public static final byte[] SET_MACHINE01_MIN = new byte[]{};
		
		public static final byte[] SET_MACHINE02_MAX = new byte[]{};
		public static final byte[] SET_MACHINE02_MIN = new byte[]{};
		
		public static final byte[] SET_MACHINE03_MAX = new byte[]{};
		public static final byte[] SET_MACHINE03_MIN = new byte[]{};
		
		//�����Ƿ��Զ�����
		public static final byte[] SET_AUTO_CONTROL = new byte[]{};
		/*************************ָ��**************************************/
}
