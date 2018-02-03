package com.hc.entity.base;

import java.util.Date;

/**
 * Created by hc on 2017/4/3.
 */
public class VisitorRecordBase {
    private Integer id;
    private Date date;
    private Integer count;
    private String type;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Integer getCount() {
        return count;
    }

    public String getType() {
        return type;
    }
}
