package com.hc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc.mapper.BlogTypeMapper;
import com.hc.entity.BlogType;
import com.hc.service.BlogTypeService;

/**
 * 博客类型Service实现类
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BlogTypeMapper blogTypeMapper;

	public List<BlogType> countList() {
		return blogTypeMapper.countList();
	}

	public List<BlogType> list(Map<String, Object> map) {
		return blogTypeMapper.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogTypeMapper.getTotal(map);
	}

	public Integer add(BlogType blogType) {
		return blogTypeMapper.add(blogType);
	}

	public Integer update(BlogType blogType) {
		return blogTypeMapper.update(blogType);
	}

	public Integer delete(Integer id) {
		return blogTypeMapper.delete(id);
	}

}
