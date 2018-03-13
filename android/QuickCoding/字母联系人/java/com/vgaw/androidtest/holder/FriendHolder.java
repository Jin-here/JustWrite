package com.vgaw.androidtest.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vgaw.androidtest.R;
import com.vgaw.androidtest.bean.ContactBase;
import com.vgaw.androidtest.bean.Friend;

/**
 * Created by caojin on 2016/5/3.
 */

/**
 * 好友item
 */
public class FriendHolder extends Holder {
    private ImageView iv_head;
    private TextView tv_name;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend, null);
        iv_head = (ImageView) view.findViewById(R.id.iv_head);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        return view;
    }

    @Override
    public void refreshView(ContactBase item) {
        Friend friend = (Friend) item;
        iv_head.setImageResource(R.mipmap.ic_launcher);
        tv_name.setText(friend.getName());
    }
}
