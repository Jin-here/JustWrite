package com.vgaw.androidtest.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vgaw.androidtest.R;
import com.vgaw.androidtest.bean.ContactBase;
import com.vgaw.androidtest.bean.Label;

/**
 * Created by caojin on 2016/5/3.
 */

/**
 * 联系人index标签item
 */
public class LabelHolder extends Holder {
    private TextView tv_index;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_label, null);
        tv_index = (TextView) view.findViewById(R.id.tv_index);
        return view;
    }

    @Override
    public void refreshView(ContactBase item) {
        Label label = (Label) item;
        tv_index.setText(label.getIndex());
    }
}
