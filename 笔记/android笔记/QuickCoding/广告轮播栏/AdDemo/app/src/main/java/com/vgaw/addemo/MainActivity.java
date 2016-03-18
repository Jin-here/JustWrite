package com.vgaw.addemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
                .showImageForEmptyUri(R.drawable.no_pic) // resource or drawable
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(config);

        ArrayList<String> list = new ArrayList<>();
        list.add("http://i.lesmao.com/i/les/T/MiiTao/001/001_001_2n3_h.jpg");
        list.add("http://i.lesmao.com/i/les/T/MiiTao/001/001_002_yqh_h.jpg");
        list.add("http://i.lesmao.com/i/les/T/MiiTao/001/001_003_d76_h.jpg");
        AdView ad = (AdView) findViewById(R.id.ad);
        ad.setOnAdClickListener(new AdView.OnAdClickListener() {
            @Override
            public void onAdClicked(int position) {
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
        ad.insertAd(list, R.drawable.point_off, R.drawable.point_on);

    }
}
