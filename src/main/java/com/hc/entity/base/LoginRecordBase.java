package com.hc.entity.base;

/**
 * Created by hc on 2017/4/4.
 */

import java.util.Date;

/**
 *
 idint(11) NOT NULL
 loginName varchar(32) NULL
 status varchar(32) NULL
 ip varchar(128) NULL
 date datetime NULL
 */
public class LoginRecordBase {
    private int id;
    private String loginName;
    private String status;
    private String ip;
    private Date date;

    public int getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getStatus() {
        return status;
    }

    public String getIp() {
        return ip;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
