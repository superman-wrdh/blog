package com.hc.entity.base;

import java.util.Date;

/**
 * Created by hc on 2017/3/20.
 */
public class SearchWordBase {
    private int id;
    private String word;
    private int count;
    private String type;
    private Date time;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public Date getTime() {
        return time;
    }
}
