package com.vgaw.androidtest;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ImageView iv_up;

    private float lastY = -1;
    private boolean isLastUp = false;

    private int lastItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        iv_up = (ImageView) findViewById(R.id.iv_up);

        iv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.smoothScrollToPosition(0);
            }
        });

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 上滑消失
                if ((firstVisibleItem > lastItemIndex || firstVisibleItem == 0) && !isLastUp) {
                    isLastUp = true;
                    animateUp(false);
                }
                // 下滑出现
                else if (firstVisibleItem < lastItemIndex && isLastUp) {
                    isLastUp = false;
                    animateUp(true);
                }
                lastItemIndex = firstVisibleItem;

            }
        });
        initListView();

    }

    private void animateUp(boolean isShow) {
        PropertyValuesHolder alphaHolder;
        PropertyValuesHolder yHolder;
        int translationY = iv_up.getHeight() + ((ViewGroup.MarginLayoutParams)iv_up.getLayoutParams()).bottomMargin;
        if (isShow) {
            alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0, 1);
            yHolder = PropertyValuesHolder.ofFloat("translationY", translationY, 0);
        } else {
            alphaHolder = PropertyValuesHolder.ofFloat("alpha", 1, 0);
            yHolder = PropertyValuesHolder.ofFloat("translationY", 0, translationY);
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv_up, alphaHolder, yHolder);
        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(400);
        animator.start();
    }

    private void initListView() {
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 0; i < 43; i++) {
            dataList.add(String.valueOf(i));
        }
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.test_list_item,
                android.R.id.text1, dataList));
    }

}
