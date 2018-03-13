package com.vgaw.androidtest.holder;

import android.content.Context;
import android.view.View;

import com.vgaw.androidtest.bean.ContactBase;

/**
 * Created by caojin on 2016/4/18.
 */
public abstract class Holder {

    public abstract View createView(Context context);

    public abstract void  refreshView(ContactBase item);
}
