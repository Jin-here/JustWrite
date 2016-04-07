package com.vgaw.androidtest;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vgaw.androidtest.view.SlidingTabStrip;

/**
 * 需要v4包
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = ((ViewPager) findViewById(R.id.viewpager));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MyFragment.newInstance(String.valueOf(position));
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return String.valueOf(position);
            }
        });

        SlidingTabStrip slidingTabLayout = ((SlidingTabStrip) findViewById(R.id.sliding_tabs));
        slidingTabLayout.setCustomTabView(R.layout.pager_tab_item, 0);
        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setSelectedIndicatorColors(Color.RED);
        slidingTabLayout.setDividerColors(Color.WHITE);
        /*// 可代替以上两项设置
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return 0;
            }

            @Override
            public int getDividerColor(int position) {
                return 0;
            }
        });*/
        // 设置每一个Tab的布局，不设置则使用默认布局
        /*slidingTabLayout.setCustomTabView();*/

    }

    public static class MyFragment extends Fragment {
        private String context;

        public static MyFragment newInstance(String context) {

            Bundle args = new Bundle();
            args.putString("context", context);
            MyFragment fragment = new MyFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.context = getArguments().getString("context");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(android.R.layout.test_list_item, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            ((TextView)view.findViewById(android.R.id.text1)).setText(context);
        }
    }

}
