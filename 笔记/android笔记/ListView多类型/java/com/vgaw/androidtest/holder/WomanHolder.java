package com.vgaw.androidtest.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vgaw.androidtest.R;
import com.vgaw.androidtest.bean.People;
import com.vgaw.androidtest.bean.Woman;

/**
 * Created by caojin on 2016/4/18.
 */
public class WomanHolder extends Holder {
    public TextView tv_name;
    public TextView tv_sex;
    public TextView tv_woman;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.women, null);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_sex = (TextView) view.findViewById(R.id.tv_sex);
        tv_woman = (TextView) view.findViewById(R.id.tv_woman);
        return view;
    }

    @Override
    public void refreshView(People item) {
        Woman woman = (Woman) item;
        tv_name.setText(woman.getName());
        tv_sex.setText(woman.getSex());
        tv_woman.setText(woman.getWoman());
    }
}
