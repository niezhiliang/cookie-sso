package com.niezhiliang.cookie.sso.entity;

public class User {
    private Long userid;

    private String username;

    private String password;

    private String ip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public User() {
    }

    public User(Long userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public User(Long userid, String username, String password, String ip) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
