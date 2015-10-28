package com.environmentalmonitoring.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ChartView extends View {

	//x���ֵ
	private int x_max = 0;
	//x��Сֵ
	private int x_min = 0;
	//y���ֵ
	private int y_max = 0;
	//y��Сֵ
	private int y_min = 0;
	//�Ƿ���ʾ��󾯽�ֵ
	private boolean warn_max_show = false;
	//�Ƿ���ʾ��С����ֵ
	private boolean warn_min_show = false;
	//��󾯽�ֵ
	private int warn_max = 0;
	//��С����ֵ
	private int warn_min = 0;
	//����һ����ʾ����
	private int row_count = 0;
	
	//�Ƿ��ܷŴ�
	private boolean isBig = false;
	//�Ƿ�����С
	private boolean isSmall = false;
	//һ�ηŴ����С����
	private int multiple = 1;
	//��� �Ŵ����С����
	private int change_count = 1;
	
	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void SimpleSetValue(int x_max,int x_min,int y_max,int y_min,int row_count){
		this.x_max = x_max;
		this.x_min = x_min;
		this.y_max = y_max;
		this.y_min = y_min;
		this.row_count = row_count;
	}
	
	public void setWarn_Max(int warn_max){
		this.warn_max = warn_max;
		this.warn_max_show = true;
	}
	
	public void setWarn_Min(int warn_min){
		this.warn_max = warn_min;
		this.warn_min_show = true;
	}
	
	public void setChange(boolean isBig,boolean isSmall,int multiple,int change_count){
		this.isBig = isBig;
		this.isSmall = isSmall;
		this.multiple = multiple;
		this.change_count = change_count;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		int height = canvas.getHeight();
		int width = canvas.getWidth();
		canvas.drawLines(new float[]{0,0, 0,height, width,height}, paint);
	}
	
}
