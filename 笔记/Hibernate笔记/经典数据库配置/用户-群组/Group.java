package com.vgaw.hibernate.pojo;

import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
public class Group {
    private String id;

    private String name;
    private List<User> members;

    public Group(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
