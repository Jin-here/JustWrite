package com.vgaw.addemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by caojin on 2016/3/18.
 */
public class AdView extends FrameLayout{
    private int DEFAULT_IMAGE = R.mipmap.ic_launcher;
    private long DELAY = 3000;
    private float DENSITY = getResources().getDisplayMetrics().density;

    private int pointId = -1;
    private int chosenId = -1;
    private ViewPager vp_ad;
    private ArrayList<String> adUrlList = new ArrayList<>();
    private LinearLayout pointerLayout;
    private Handler handler = new Handler();

    public AdView(Context context) {
        this(context, null);
    }

    public AdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void insertAd(ArrayList<String> adUrlList, int pointId, int chosenId){
        if (adUrlList == null){
            return;
        }
        this.pointId = pointId;
        this.chosenId = chosenId;
        this.adUrlList.addAll(adUrlList);

        vp_ad = new ViewPager(getContext());
        vp_ad.setAdapter(new MyAdapter());
        addView(vp_ad, new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        vp_ad.addOnPageChangeListener(pageChangeListener);

        generatePoint();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        vp_ad.setCurrentItem(vp_ad.getCurrentItem() + 1);
                    }
                });
            }
        };
        timer.schedule(task, DELAY, DELAY);
    }



    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0;i < pointerLayout.getChildCount();i++){
                ((ImageView)pointerLayout.getChildAt(i)).setImageResource(position % adUrlList.size() == i ? chosenId : pointId);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private void generatePoint(){
        if (pointId == -1 || chosenId == -1){
            return;
        }
        pointerLayout = new LinearLayout(getContext());
        pointerLayout.setOrientation(LinearLayout.HORIZONTAL);
        pointerLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        for (int i = 0;i < adUrlList.size();i++){
            ImageView pointer = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins((int)(5 * DENSITY), 0, (int)(5 * DENSITY), (int)(10 * DENSITY));
            if (i == 0){
                pointer.setImageResource(chosenId);
            }else {
                pointer.setImageResource(pointId);
            }
            pointerLayout.addView(pointer, params);
        }
        addView(pointerLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL));
    }

    public ViewPager getViewPager(){
        return this.vp_ad;
    }

    public class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = new ImageView(getContext());
            ImageLoader.getInstance().displayImage(adUrlList.get(position % adUrlList.size()), iv);
            iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onAdClicked(position % 3);
                    }
                }
            });

            container.addView(iv);
            return iv;
        }
    }

    private OnAdClickListener listener;

    public void setOnAdClickListener(OnAdClickListener listener){
        this.listener = listener;
    }

    public interface OnAdClickListener{
        void onAdClicked(int position);
    }


}
