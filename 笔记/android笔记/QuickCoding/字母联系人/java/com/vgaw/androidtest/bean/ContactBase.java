package com.vgaw.androidtest.bean;

/**
 * Created by caojin on 2016/5/3.
 */
public class ContactBase {
    public static final int TYPE_GROUP = 0;
    public static final int TYPE_LABEL = 1;
    public static final int TYPE_FRIEND = 2;

    private int type;

    public ContactBase(){}

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
