package com.vgaw.androidtest.bean;

/**
 * Created by caojin on 2016/5/3.
 */
public class Group extends ContactBase {
    private String name;
    private String head_url;

    public Group(){
        setType(ContactBase.TYPE_GROUP);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }
}
