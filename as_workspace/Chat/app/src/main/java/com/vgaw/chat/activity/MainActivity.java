package com.vgaw.chat.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vgaw.chat.R;
import com.vgaw.chat.adapter.MyFragmentPagerAdapter;
import com.vgaw.chat.fragment.FragmentChat;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/19.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{
    private final int PAGE_NUM = 7;
    private HorizontalScrollView mHorizontalScrollView ;
    private LinearLayout mLinearLayout;
    private ViewPager pager;
    private ImageView mImageView;
    private int mScreenWidth;
    private int item_width;

    private int endPosition;
    private int beginPosition;
    private int currentFragmentIndex;
    private boolean isEnd;

    private String[] catagoryArray = new String[]{"聊天","購物","聚會","熱門話題","她/他","求助","客服"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv_view);
        mLinearLayout = (LinearLayout) findViewById(R.id.hsv_content);
        mImageView = (ImageView) findViewById(R.id.img1);
        pager = (ViewPager) findViewById(R.id.pager);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        item_width = (int) ((mScreenWidth / 4.0 + 0.5f));
        mImageView.getLayoutParams().width = item_width;

        // ��ʼ������
        initNav();
        // ��ʼ��viewPager
        initViewPager();
    }

    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        for (int i = 0;i < PAGE_NUM;i++){
            Bundle data = new Bundle();
            data.putString("text", (i+1)+"");
            Fragment fragment = new FragmentChat();
            fragment.setArguments(data);
            fragments.add(fragment);
        }
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(fragmentPagerAdapter);

        pager.addOnPageChangeListener(new MyOnPageChangeListener());
        pager.setCurrentItem(0);
    }

    private void initNav() {
        for (int i = 0 ; i < PAGE_NUM; i++) {
            RelativeLayout layout = new RelativeLayout(this);
            TextView view = new TextView(this);
            view.setText(catagoryArray[i]);
            RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            layout.addView(view, params);
            mLinearLayout.addView(layout, (int)(mScreenWidth/4 + 0.5f), 50);
            layout.setOnClickListener(this);
            layout.setTag(i);
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(final int position) {
            Animation animation = new TranslateAnimation(endPosition, position* item_width, 0, 0);

            beginPosition = position * item_width;

            currentFragmentIndex = position;
            if (animation != null) {
                animation.setFillAfter(true);
                animation.setDuration(0);
                mImageView.startAnimation(animation);
                mHorizontalScrollView.smoothScrollTo((currentFragmentIndex - 1) * item_width , 0);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            if(!isEnd){
                if(currentFragmentIndex == position){
                    endPosition = item_width * currentFragmentIndex +
                            (int)(item_width * positionOffset);
                }
                if(currentFragmentIndex == position+1){
                    endPosition = item_width * currentFragmentIndex -
                            (int)(item_width * (1-positionOffset));
                }

                Animation mAnimation = new TranslateAnimation(beginPosition, endPosition, 0, 0);
                mAnimation.setFillAfter(true);
                mAnimation.setDuration(0);
                mImageView.startAnimation(mAnimation);
                mHorizontalScrollView.invalidate();
                beginPosition = endPosition;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isEnd = false;
            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                isEnd = true;
                beginPosition = currentFragmentIndex * item_width;
                if (pager.getCurrentItem() == currentFragmentIndex) {
                    // δ������һ��ҳ��
                    mImageView.clearAnimation();
                    Animation animation = null;
                    // �ָ�λ��
                    animation = new TranslateAnimation(endPosition, currentFragmentIndex * item_width, 0, 0);
                    animation.setFillAfter(true);
                    animation.setDuration(1);
                    mImageView.startAnimation(animation);
                    mHorizontalScrollView.invalidate();
                    endPosition = currentFragmentIndex * item_width;
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        pager.setCurrentItem((Integer)v.getTag());
    }

}
