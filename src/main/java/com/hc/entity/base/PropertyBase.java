package com.hc.entity.base;

import java.util.List;

/**
 * Created by hc on 2017/4/5.
 * 记录工程的配置信息
 */
public class PropertyBase {
    private int id;
    private String key;//配置key
    private String value;//配置值
    private String values;//配置值的选项值字符串
    private String description;//描述

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getValues() {
        return values;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
