package com.vgaw.androidtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> rawList = new ArrayList<>();
    private EasyAdapter adapter;
    private int limit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simulateData();

        ListView lv = (ListView) findViewById(R.id.lv);
        adapter = new EasyAdapter(MainActivity.this, rawList) {
            @Override
            public EasyHolder getHolder(int type) {
                if (type == 0){
                    return new PersonHolder();
                }
                return new ChildHolder();
            }

            @Override
            public int getItemViewType(int position) {
                if (position % limit == 0){
                    return 0;
                }
                return 1;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }
        };
        lv.setAdapter(adapter);

        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simulateData();
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 构建模拟数据
     */
    private void simulateData(){
        rawList.clear();
        limit = new Random().nextInt(7) + 1;
        for (int i = 0;i < 41;i++){
            if (i % limit == 0){
                Person p = new Person();
                p.setName("caojin" + i);
                p.setSex("man" + i);
                rawList.add(p);
            }else {
                Child c = new Child();
                c.setName("caojin" + i);
                c.setSex("man" + i);
                rawList.add(c);
            }
        }
    }

}
