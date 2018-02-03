package com.hc.service;

import java.util.List;
import java.util.Map;

import com.hc.entity.Comment;

/**
 * 评论Service接口
 * @author Administrator
 *
 */
public interface CommentService {

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	 int add(Comment comment);

	/**
	 * 修改评论
	 * @param comment
	 * @return
	 */
	 int update(Comment comment);

	/**
	 * 查找用户评论信息
	 * @param map
	 * @return
	 */
	 List<Comment> list(Map<String,Object> map);


	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	 Long getTotal(Map<String,Object> map);

	/**
	 * 删除评论信息
	 * @param id
	 * @return
	 */
	 Integer delete(Integer id);
}
