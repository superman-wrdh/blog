package com.hc.mapper;

import com.hc.entity.Property;

import java.util.List;
import java.util.Map;

/**
 * Created by hc on 2017/4/5.
 */
public interface PropertyMapper {
    List<Property> list(Map<String,Object> map);
    void insert(Property property);
    Property getById(int id);
    void update(Property property);
    void delete(int id);
}
