package com.environmentalmonitoring.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ChartView extends View {

	//x最大值
	private int x_max = 0;
	//x最小值
	private int x_min = 0;
	//y最大值
	private int y_max = 0;
	//y最小值
	private int y_min = 0;
	//是否显示最大警戒值
	private boolean warn_max_show = false;
	//是否显示最小警戒值
	private boolean warn_min_show = false;
	//最大警戒值
	private int warn_max = 0;
	//最小警戒值
	private int warn_min = 0;
	//坐标一次显示个数
	private int row_count = 0;
	
	//是否能放大
	private boolean isBig = false;
	//是否能缩小
	private boolean isSmall = false;
	//一次放大或缩小倍数
	private int multiple = 1;
	//最大 放大或缩小几次
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
