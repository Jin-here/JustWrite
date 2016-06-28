package com.vgaw.androidtest.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class ClockView extends View {
    private Paint mPaint;
    private float currentDegree = 0;

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(400, 400, 200, mPaint);
        canvas.save();
        for (int i = 0;i < 12;i++){
            canvas.rotate(30, 400, 400);
            canvas.drawLine(200, 400, 220, 400, mPaint);
            canvas.drawText(getTime(i), 230, 400, mPaint);
        }
        canvas.restore();
        canvas.drawLine(400, 400, 450, 450, mPaint);

        canvas.rotate(currentDegree, 400, 400);
        currentDegree += 6;
        canvas.drawLine(400, 250, 400, 400, mPaint);
        postInvalidateDelayed(1000);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private String getTime(int i){
        int result = 9 + i;
        if (result < 13){
            return String.valueOf(result);
        }else {
            return String.valueOf(result - 12);
        }
    }
}
