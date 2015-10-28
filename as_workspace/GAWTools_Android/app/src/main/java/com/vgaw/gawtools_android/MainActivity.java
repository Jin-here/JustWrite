package com.vgaw.gawtools_android;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.vgaw.gawtools_android.location.Locate;
import com.vgaw.gawtools_android.voice.GTextToVoice;
import com.vgaw.gawtools_android.weather.Weather;
import com.vgaw.gawtools_android.weather.catagory.Forecast;
import com.vgaw.gawtools_android.weather.catagory.Today;


public class MainActivity extends FragmentActivity {

    private Locate mLocate;
    private GTextToVoice gTextToVoice;

    private TextView show;
    private TextView locationTextView;
    private EditText sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (TextView) findViewById(R.id.show);
        locationTextView = (TextView) findViewById(R.id.location);
        sentence = (EditText) findViewById(R.id.sentence);

        /*************************获取天气***************************/
        //只需调用一次
        Weather weather = new Weather();
        show.setText(
                weather.getWeatherInfo("南京", Today.HIGHTEMP) +
                        weather.getWeatherInfo("南通", Forecast.HIGHTEMP, 1)
        );

        /**
         * catagory类解释：
         * Basic:获取基础信息：城市名，城市ID
         * ChuanYi:穿衣指数
         * FangShai:防晒指数
         * GanMao:感冒指数
         * LiangShai:晾晒指数
         * XiChe:洗车指数
         * YunDong:运动指数
         * Forecast:将来的天气
         * History:历史天气
         */

        /*************************定位***************************/
        mLocate = new Locate(getApplicationContext()) {
            @Override
            public void onReceive(String location, boolean isSuccess) {
                locationTextView.setText(location);
            }
        };
        mLocate.locate();

        /*************************发声***************************/
        SpeechUtility.createUtility(getBaseContext(), SpeechConstant.APPID + "=55c8b73d");

        gTextToVoice = new GTextToVoice(getBaseContext());

    }

    public void onButtonClick(View view){
        gTextToVoice.speak(sentence.getText().toString());
    }

}
