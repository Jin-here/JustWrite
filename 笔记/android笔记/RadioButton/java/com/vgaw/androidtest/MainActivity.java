package com.vgaw.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        //  以下两种方式均可，但不可在xml中设置android:check="true"，
        // 否则，该RadioButton会一直处于checked状态
        ((RadioButton)rg.getChildAt(0)).setChecked(true);
        //rg.check(rg.getChildAt(0).getId());
    }
}
