package com.vgaw.sexygirl;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by caojin on 2016/4/29.
 */
public class AnimateUtils {
    private void updateWidthNHeight(View view, int width, int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }
}
