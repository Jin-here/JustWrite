package com.vgaw.hibernate.pojo;

import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
public class User {
    private String id;

    private String nickName;
    private String password;
    private List<Group> groups;

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
