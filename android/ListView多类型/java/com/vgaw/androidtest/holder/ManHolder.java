package com.vgaw.androidtest.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vgaw.androidtest.R;
import com.vgaw.androidtest.bean.Man;
import com.vgaw.androidtest.bean.People;

/**
 * Created by caojin on 2016/4/18.
 */
public class ManHolder extends Holder {
    public TextView tv_name;
    public TextView tv_sex;
    public TextView tv_man;

    @Override
    public void refreshView(People item) {
        Man man = (Man) item;
        tv_name.setText(man.getName());
        tv_sex.setText(man.getSex());
        tv_man.setText(man.getMan());
    }

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.man, null);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_sex = (TextView) view.findViewById(R.id.tv_sex);
        tv_man = (TextView) view.findViewById(R.id.tv_man);
        return view;
    }


}
