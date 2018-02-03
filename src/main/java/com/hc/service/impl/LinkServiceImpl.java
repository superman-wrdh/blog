package com.hc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc.mapper.LinkMapper;
import com.hc.entity.Link;
import com.hc.service.LinkService;

/**
 * 友情链接Service实现类
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkMapper linkMapper;

	public int add(Link link) {
		return linkMapper.add(link);
	}

	public int update(Link link) {
		return linkMapper.update(link);
	}

	public List<Link> list(Map<String, Object> map) {
		return linkMapper.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return linkMapper.getTotal(map);
	}

	public Integer delete(Integer id) {
		return linkMapper.delete(id);
	}

}
