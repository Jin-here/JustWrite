package com.vgaw.colored_status_bar.util;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

public class DeepGestureListener extends SimpleOnGestureListener {
    private int i = -1;
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        int verticalMinDistance = 20;
        int minVelocity = 0;

        float e1x = e1.getX();
        float e1y = e1.getY();
        float e2x = e2.getX();
        float e2y = e2.getY();


        if (Math.abs(e2y - e1y) < (e1x - e2x) && e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            i = 0;
        } else if (Math.abs(e2y - e1y) < (e2x - e1x) && e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            i = 1;
        } else if (Math.abs(e2x - e1x) < (e1y - e2y) && e1.getY() - e2.getY() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            i = 2;
        } else if (Math.abs(e2x - e1x) < (e2y - e1y) && e2.getY() - e1.getY() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            i = 3;
        }

        return false;
    }

    public int getResult(){
        return i;
    }
}
