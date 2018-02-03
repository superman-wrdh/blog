package com.hc.mapper;

import com.hc.entity.LoginRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by hc on 2017/4/4.
 */
public interface LoginRecordMapper {
    List<LoginRecord> list(Map<String,Object> map);
    void insert (LoginRecord loginRecord);
    LoginRecord getById(int id);
    void delete(int id);
    int count();
}
