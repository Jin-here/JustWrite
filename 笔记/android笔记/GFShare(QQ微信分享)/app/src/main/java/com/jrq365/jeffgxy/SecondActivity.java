package com.jrq365.jeffgxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.tencent.tauth.Tencent;

import junit.framework.TestCase;

/**
 * Created by caojin on 2016/1/29.
 */
public class SecondActivity extends Activity {
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTencent = Tencent.createInstance("1104811677", this.getApplicationContext());
    }

    public void onClick(View v){
        new SharePop(this, mTencent).show(v);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (null != mTencent){
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
    }
}
