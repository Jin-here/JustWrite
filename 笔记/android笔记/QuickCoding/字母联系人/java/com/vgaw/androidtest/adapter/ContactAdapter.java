package com.vgaw.androidtest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vgaw.androidtest.bean.ContactBase;
import com.vgaw.androidtest.holder.FriendHolder;
import com.vgaw.androidtest.holder.GroupHolder;
import com.vgaw.androidtest.holder.Holder;
import com.vgaw.androidtest.holder.LabelHolder;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ContactBase> dataList;

    public ContactAdapter(Context mContext, ArrayList<ContactBase> dataList){
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public ContactBase getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            switch (getItemViewType(position)) {
                // 群组
                case ContactBase.TYPE_GROUP:
                    holder = new GroupHolder();
                    break;
                // index标签
                case ContactBase.TYPE_LABEL:
                    holder = new LabelHolder();
                    break;
                // 好友
                case ContactBase.TYPE_FRIEND:
                    holder = new FriendHolder();
                    break;
            }
            convertView = holder.createView(mContext);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        holder.refreshView((ContactBase) getItem(position));
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        if (getItemViewType(position) == ContactBase.TYPE_LABEL){
            return false;
        }
        return true;
    }
}