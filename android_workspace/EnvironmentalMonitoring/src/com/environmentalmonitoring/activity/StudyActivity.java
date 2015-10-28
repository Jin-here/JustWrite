package com.environmentalmonitoring.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class StudyActivity extends FragmentActivity {
	private static final int[] resource = new int[]{R.drawable.tab00, R.drawable.tab01,
		R.drawable.tab02, R.drawable.tab03};
	private static final String TAG = MainActivity.class.getSimpleName();

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_sample);
	MyFragmentStatePager adpter = new MyFragmentStatePager(getSupportFragmentManager());
	ColorAnimationView colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
	ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
	viewPager.setAdapter(adpter);
	
	colorAnimationView.setmViewPager(viewPager, resource.length);
    colorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        	
        }

        @Override
        public void onPageSelected(int position) {
        	if (position == 3){
        		Toast.makeText(getBaseContext(), "Welcome!", Toast.LENGTH_SHORT).show();
            	new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(600);
							startActivity(new Intent(StudyActivity.this,LoginActivity.class));
			            	finish();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
            	
            }
        	
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            
        }
    });
	// Four : Also ,you can call this method like this:
	// colorAnimationView.setmViewPager(viewPager,this,resource.length,0xffFF8080,0xff8080FF,0xffffffff,0xff80ff80);
}



public class MyFragmentStatePager
		extends FragmentStatePagerAdapter {

	public MyFragmentStatePager(FragmentManager fm) {
		super(fm);
	}

	@Override public Fragment getItem(int position) {
		return new MyFragment(position);
	}

	@Override public int getCount() {
		return resource.length;
	}
}

	public class MyFragment
		extends Fragment {
	private int position;

	public MyFragment(int position) {
		this.position = position;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ImageView imageView = new ImageView(getActivity());
		imageView.setImageResource(resource[position]);
		return imageView;
	}
}
}
