package com.hc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc.mapper.BlogMapper;
import com.hc.entity.Blog;
import com.hc.service.BlogService;

/**
 * 博客Service实现类
 * @author Administrator
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Resource
	private BlogMapper blogMapper;

	public List<Blog> countList() {
		return blogMapper.countList();
	}

	public List<Blog> list(Map<String, Object> map) {
		return blogMapper.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogMapper.getTotal(map);
	}

	public Blog findById(Integer id) {
		return blogMapper.findById(id);
	}

	public Integer update(Blog blog) {
		return blogMapper.update(blog);
	}

	public Blog getLastBlog(Integer id) {
		return blogMapper.getLastBlog(id);
	}

	public Blog getNextBlog(Integer id) {
		return blogMapper.getNextBlog(id);
	}

	public Integer add(Blog blog) {
		return blogMapper.add(blog);
	}

	public Integer delete(Integer id) {
		return blogMapper.delete(id);
	}

	public Integer getBlogByTypeId(Integer typeId) {
		return blogMapper.getBlogByTypeId(typeId);
	}

    public List<Blog> getByClickHitTime(Map<String,Object> map){
    	return blogMapper.getBlogByClickHitTime(map);
	}

}
