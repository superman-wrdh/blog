package com.hc.entity;

import com.hc.entity.base.LinkBase;

/**
 * 友情链接实体
 * @author Administrator
 *
 */
public class Link extends LinkBase{
    Link(){

    }
    public Link(Integer id, String linkName, String linkUrl, Integer orderNo){
        super(id,linkName,linkUrl,orderNo);
    }
}
