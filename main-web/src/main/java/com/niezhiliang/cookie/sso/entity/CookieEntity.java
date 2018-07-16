package com.niezhiliang.cookie.sso.entity;

import java.util.Date;

public class CookieEntity {
    private Date date;

    private String userid;

    private String ip;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
