package com.aimer;

import java.io.Serializable;

public class User implements Serializable {
    private String uid;
    private String username;

    public User() {
    }

    public User(String uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
