package com.environmentalmonitoring.activity;

import java.lang.ref.WeakReference;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.ClientApp;
import com.environmentalmonitoring.dialog.FindPwdDialog;
import com.environmentalmonitoring.dialog.RegisDialog;
import com.environmentalmonitoring.dialog.SetIpDialog;
import com.environmentalmonitoring.thread.LoginThread;

public class LoginActivity extends FragmentActivity {

	private TextView set_ip_text_view;
	private TextView account_edit_text;
	private TextView password_edit_text;
	private CheckBox recode_pwd_check_box;
	private TextView forget_pwd_text_view;
	private Button login_button;
	private TextView register_text_view;
	private ClientApp app;
	public Handler mHandler = new MyHandler(this);

	private String username = "";
	private String password = "";

	// handler
	private static class MyHandler extends Handler {
		WeakReference<LoginActivity> mActivity;

		MyHandler(LoginActivity activity) {
			mActivity = new WeakReference<LoginActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			LoginActivity theActivity = mActivity.get();
			switch (msg.what) {
			case Config.REGIS_WHAT:
				if (msg.arg1 == 1) {
					Toast.makeText(theActivity,
							theActivity.getString(R.string.register_success),
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(theActivity,
							theActivity.getString(R.string.register_failed),
							Toast.LENGTH_SHORT).show();
				}
				break;
			case Config.LOGIN_WHAT:
				if (msg.arg1 == 1) {
					// if (username.equals("caojin") &&
					// password.equals("caojin")){
					if (theActivity.recode_pwd_check_box.isChecked()) {
						theActivity.app.setRecode(true);
						theActivity.app.setPassword(theActivity.password);
					} else {
						theActivity.app.setRecode(false);
						theActivity.app.setPassword("");
					}
					theActivity.app.setUsername(theActivity.username);
					theActivity.startActivity(new Intent(theActivity,
							MainActivity.class));
					theActivity.finish();
					// }
				} else {
					theActivity.account_edit_text.setText("");
					theActivity.password_edit_text.setText("");
					Toast.makeText(theActivity,
							theActivity.getString(R.string.login_failed),
							Toast.LENGTH_SHORT).show();
				}
				break;

			}

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login);

		app = (ClientApp) getApplication();
		if (app.isFirstRun()) {
			startActivity(new Intent(getBaseContext(), StudyActivity.class));
			app.setFirstRun();
			finish();
		}
		set_ip_text_view = (TextView) findViewById(R.id.set_ip_text_view);
		account_edit_text = (TextView) findViewById(R.id.account_edit_text);
		password_edit_text = (TextView) findViewById(R.id.password_edit_text);
		recode_pwd_check_box = (CheckBox) findViewById(R.id.recode_pwd_check_box);
		forget_pwd_text_view = (TextView) findViewById(R.id.forget_pwd_text_view);
		login_button = (Button) findViewById(R.id.login_button);
		register_text_view = (TextView) findViewById(R.id.register_text_view);

		set_ip_text_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SetIpDialog().show(getSupportFragmentManager(),
						SetIpDialog.TAG);
			}
		});

		account_edit_text.setText(app.getUsername());
		if (app.isRecode()) {
			password_edit_text.setText(app.getPassword());
			recode_pwd_check_box.setChecked(true);
		}

		// 登陆按钮
		login_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username = account_edit_text.getText().toString();
				password = password_edit_text.getText().toString();
				// 管理员登陆,不记住用户名密码
				//startActivity(new Intent(getBaseContext(), MainActivity.class));

				if (username.equals("") || username == null) {
					Toast.makeText(getBaseContext(),
							getString(R.string.username_not_empty),
							Toast.LENGTH_SHORT).show();
				}
				if (password.equals("") || password == null) {
					Toast.makeText(getBaseContext(),
							getString(R.string.password_not_empty),
							Toast.LENGTH_SHORT).show();
				} else {

					new LoginThread(username, password, getBaseContext(),
							mHandler).start();
				}
			}
		});

		forget_pwd_text_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new FindPwdDialog(mHandler).show(getSupportFragmentManager(),
						FindPwdDialog.TAG);
			}
		});

		register_text_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new RegisDialog().show(getSupportFragmentManager(),
						RegisDialog.TAG);
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		// super.onSaveInstanceState(outState);
	}
}
