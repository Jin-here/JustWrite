package com.vgaw.androidtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class ClockView extends View {
    private Paint mPaint;
    private Canvas mCopyCanvas;
    private Bitmap mCopyBitmap;
    private Bitmap mNowBitmap;
    private float currentDegree = 0;

    private final int DEFAULTSIZE = 400;
    private int size = -1;
    private float cx = -1;
    private float cy = -1;
    private float lengthOfKedu = -1;
    private float lengthOfHour = -1;
    private float lengthOfSecond = -1;

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(DEFAULTSIZE, MeasureSpec.EXACTLY);
        }
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT){
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(DEFAULTSIZE, MeasureSpec.EXACTLY);
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDynamicThing();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawBitmap(mNowBitmap, 0, 0, mPaint);
        postInvalidateDelayed(1000);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        size = Math.min(getWidth(), getHeight());
        cx = size / 2;
        cy = cx;
        lengthOfKedu = cx / 10;
        lengthOfHour = cx * 3 / 10;
        lengthOfSecond = cx * 2 / 3;

        mPaint.setTextSize(lengthOfKedu);
        drawStaticThing();
    }

    private void drawStaticThing(){
        mCopyBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCopyCanvas = new Canvas(mCopyBitmap);
        mCopyCanvas.drawCircle(cx, cy, cx, mPaint);
        mCopyCanvas.save();
        for (int i = 0;i < 12;i++){
            mCopyCanvas.rotate(30, cx, cy);
            mCopyCanvas.drawLine(0, cy, lengthOfKedu, cy, mPaint);
            mCopyCanvas.drawText(getTime(i), lengthOfKedu + 10, cy, mPaint);
        }
        mCopyCanvas.restore();
        mCopyCanvas.drawLine(cx, cy, cx + lengthOfHour, cy, mPaint);
    }

    private void drawDynamicThing(){
        mNowBitmap = Bitmap.createBitmap(mCopyBitmap);
        Canvas canvas = new Canvas(mNowBitmap);
        canvas.rotate(currentDegree, cx, cy);
        currentDegree += 6;
        canvas.drawLine(cx, cy - lengthOfSecond, cx, cy, mPaint);
    }

    private String getTime(int i){
        int result = 10 + i;
        if (result < 13){
            return String.valueOf(result);
        }else {
            return String.valueOf(result - 12);
        }
    }
}
