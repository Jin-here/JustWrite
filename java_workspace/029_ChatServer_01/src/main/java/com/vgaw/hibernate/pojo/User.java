package com.vgaw.hibernate.pojo;

import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
public class User {
    private String id;

    private String token;
    private String name;
    private String password;

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
