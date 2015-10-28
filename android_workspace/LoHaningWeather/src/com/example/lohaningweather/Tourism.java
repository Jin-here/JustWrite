package com.example.lohaningweather;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class Tourism extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toursit);
	}
	
	// 返回键退出
		// 返回键
		private long mExitTime;

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				if (System.currentTimeMillis() - mExitTime > 1000) {
					Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
					return true;
				} else {

					Tourism.this.finish();
					switchLocation s=new switchLocation();
					s.f.finish();
					LocationManage l=new LocationManage();
					l.f.finish();
					First f=new First();
					f.f.finish();
					MainActivity m=new MainActivity();
					m.f.finish();
					MoreDetail more=new MoreDetail();
					more.f.finish();
				
				}
			}
			return super.onKeyDown(keyCode, event);

		}
		
}
