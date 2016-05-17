package com.vgaw.androidtest.bean;

/**
 * Created by caojin on 2016/5/3.
 */
public class Friend extends ContactIndex{
    private String name;
    private String head_url;

    public Friend(){
        setType(ContactBase.TYPE_FRIEND);
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
