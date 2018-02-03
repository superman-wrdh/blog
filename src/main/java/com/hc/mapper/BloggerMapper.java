package com.hc.mapper;

import com.hc.entity.Blogger;

/**
 * 博主Mapper接口
 * @author hc
 *
 */
public interface BloggerMapper {

	/**
	 * 查询博主信息
	 * @return
	 */
	 Blogger find();

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	 Blogger getByUserName(String userName);

	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	 Integer update(Blogger blogger);
}
