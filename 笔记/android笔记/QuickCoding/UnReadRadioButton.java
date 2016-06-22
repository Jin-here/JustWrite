package com.hchl.financeteam.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.hchl.financeteam.R;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class UnReadRadioButton extends RadioButton {
    private String num = null;
    // 气泡半径
    private int radius;

    private Paint circlePaint;
    private Paint textPaint;

    public UnReadRadioButton(Context context) {
        this(context, null);
    }

    public UnReadRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnReadRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UnReadRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        radius = (int) context.getResources().getDimension(R.dimen.space_10);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(context.getResources().getDimension(R.dimen.text_size_10));
    }

    /**
     * 设置未读消息数目
     * -1 为清除气泡
     * @param num
     */
    public void setUnRead(int num){
        if (num == 0){
            this.num = null;
        }else
        if (num > 99){
            this.num = "99+";
        }else {
            this.num = String.valueOf(num);
        }
        invalidate();
    }

    /**
     * 绘制气泡
     */
    private void drawCircle(Canvas canvas){
        canvas.drawCircle(getWidth() - 2 * radius, radius, radius, circlePaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (num != null){
            drawCircle(canvas);
            Rect rect = new Rect();
            textPaint.getTextBounds(num, 0, num.length(), rect);
            canvas.drawText(num, getWidth() - radius * 2, radius + rect.height() / 2, textPaint);
        }
    }
}
