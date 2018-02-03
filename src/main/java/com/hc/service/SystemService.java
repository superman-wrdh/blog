package com.hc.service;

import com.hc.entity.Property;
import com.hc.mapper.PropertyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hc on 2017/4/5.
 */
@Service
public class SystemService {
    @Autowired
    PropertyMapper propertyMapper;

    public void add(Property property){
        propertyMapper.insert(property);
    }

    public void delete(Integer id){
        if(id!=null){
            propertyMapper.delete(id);
        }
    }

    public void update(Property property){
        if(property!=null){
            propertyMapper.update(property);
        }
    }
    public List<Property> list(Map<String,Object> map){
        if(map!=null){
            return propertyMapper.list(map);
        }else{
            return propertyMapper.list(null);
        }
    }
}
