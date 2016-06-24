package com.vgaw.androidtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.vgaw.androidtest.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class MainActivity extends Activity {
    private ListView lv_show;
    private ArrayList<ArrayList<String>> dataList = new ArrayList<>();
    private EasyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_show = (ListView) findViewById(R.id.lv_show);

        getData();
        adapter = new EasyAdapter(this, dataList) {
            @Override
            public EasyHolder getHolder(int type) {
                return new ParentHolder();
            }
        };
        lv_show.setAdapter(adapter);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                adapter.notifyDataSetChanged();
            }
        });
    }

    public class ParentHolder extends EasyHolder{
        private GridView gv;

        @Override
        public int getLayout() {
            return R.layout.item_parent;
        }

        @Override
        public View createView() {
            gv = (GridView) view.findViewById(R.id.gv);
            return view;
        }

        @Override
        public void refreshView(Object item, int position) {
            gv.setAdapter(new EasyAdapter(MainActivity.this, dataList.get(position)) {
                @Override
                public EasyHolder getHolder(int type) {
                    return new ChildHolder();
                }
            });
        }
    }

    public class ChildHolder extends EasyHolder{
        private TextView tv;
        @Override
        public int getLayout() {
            return R.layout.item_child;
        }

        @Override
        public View createView() {
            tv = (TextView) view.findViewById(R.id.tv);
            return view;
        }

        @Override
        public void refreshView(Object item, int position) {
            tv.setText(String.valueOf(item));
        }
    }

    private void getData(){
        dataList.clear();
        for (int i = 0;i < 7;i++){
            ArrayList<String> childList = new ArrayList<>();
            int limit = new Random().nextInt(7);
            for (int j = 0;j < limit;j++){
                childList.add(String.valueOf(j));
            }
            dataList.add(childList);
        }
    }
}
