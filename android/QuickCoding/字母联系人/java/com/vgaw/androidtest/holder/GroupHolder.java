package com.vgaw.androidtest.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vgaw.androidtest.R;
import com.vgaw.androidtest.bean.ContactBase;
import com.vgaw.androidtest.bean.Group;

/**
 * Created by caojin on 2016/5/3.
 */

/**
 * 群组item
 */
public class GroupHolder extends Holder{
    private ImageView iv_head;
    private TextView tv_name;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, null);
        iv_head = (ImageView) view.findViewById(R.id.iv_head);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        return view;
    }

    @Override
    public void refreshView(ContactBase item) {
        Group group = (Group) item;
        iv_head.setImageResource(R.mipmap.ic_launcher);
        tv_name.setText(group.getName());
    }
}
