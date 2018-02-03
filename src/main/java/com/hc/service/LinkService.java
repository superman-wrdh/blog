package com.hc.service;

import java.util.List;
import java.util.Map;

import com.hc.entity.Link;

/**
 * 友情链接Service接口
 * @author Administrator
 *
 */
public interface LinkService {

	/**
	 * 添加友情链接
	 * @param link
	 * @return
	 */
	 int add(Link link);

	/**
	 * 修改友情链接
	 * @param link
	 * @return
	 */
	 int update(Link link);

	/**
	 * 查找友情链接信息
	 * @param map
	 * @return
	 */
	 List<Link> list(Map<String,Object> map);

	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	 Long getTotal(Map<String,Object> map);

	/**
	 * 删除友情链接
	 * @param id
	 * @return
	 */
	 Integer delete(Integer id);
}
