package com.hc.mapper;

import com.hc.entity.VisitorRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hc on 2017/4/3.
 */
public interface VisitorRecordMapper {
    void insert(VisitorRecord visitorRecord);
    List<VisitorRecord> list(Map<String,Object> map);
    int count();
    int countAllVisitor();
    VisitorRecord getById(int id);
    VisitorRecord getByDate(@Param("date")Date date);
    void update(VisitorRecord visitorRecord);
}
