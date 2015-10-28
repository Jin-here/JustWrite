package com.vgaw.hibernate.pojo;

import java.util.Set;

/**
 * Created by Administrator on 2015/9/15.
 */
public class Group {
    private String id;

    private String name;
    private Set<User> members;

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

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
