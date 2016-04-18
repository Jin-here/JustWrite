package com.vgaw.androidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vgaw.androidtest.bean.Man;
import com.vgaw.androidtest.bean.People;
import com.vgaw.androidtest.bean.Woman;
import com.vgaw.androidtest.holder.Holder;
import com.vgaw.androidtest.holder.ManHolder;
import com.vgaw.androidtest.holder.WomanHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<People> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 13; i++) {
            if (i % 2 == 0) {
                Man man = new Man();
                man.setName("亚当");
                man.setSex("男");
                man.setMan("我是男人！");
                dataList.add(man);
            } else {
                Woman woman = new Woman();
                woman.setName("夏娃");
                woman.setSex("女");
                woman.setWoman("我是女人！");
                dataList.add(woman);
            }
        }

        ((ListView) findViewById(R.id.lv)).setAdapter(new MyAdapter());
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null){
                if (getItemViewType(position) == 0){
                    holder = new ManHolder();
                }else {
                    holder = new WomanHolder();
                }
                convertView = holder.createView(MainActivity.this);
                convertView.setTag(holder);
            }
            holder = (Holder) convertView.getTag();
            holder.refreshView((People) getItem(position));
            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return position % getViewTypeCount();
        }
    }

}
