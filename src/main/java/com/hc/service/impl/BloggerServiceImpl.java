package com.hc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc.mapper.BloggerMapper;
import com.hc.entity.Blogger;
import com.hc.service.BloggerService;

/**
 * 博主Service实现类
 * @author Administrator
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerMapper bloggerMapper;

	public Blogger find() {
		return bloggerMapper.find();
	}

	public Blogger getByUserName(String userName) {
		return bloggerMapper.getByUserName(userName);
	}

	public Integer update(Blogger blogger) {
		return bloggerMapper.update(blogger);
	}


}
