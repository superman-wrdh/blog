package com.hc.api.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hc.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hc.entity.Comment;
import com.hc.entity.PageBean;
import com.hc.service.CommentService;
import com.hc.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员评论Controller层
 * @author hc
 *
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController extends BaseController{

	@Autowired
	private CommentService commentService;

	/**
	 * Created by hc on 2017/2/2.
	 * 分页查询评论信息
	 * @param page
	 * @param rows
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,@RequestParam(value="state",required=false)String state)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("state", state); // 评论状态
		List<Comment> commentList=commentService.list(map);
		Long total=commentService.getTotal(map);
		Map<String,Object> result = new HashMap<>();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(commentList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 删除评论信息
	 * @param ids
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam(value="ids")String ids)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			commentService.delete(Integer.parseInt(idsStr[i]));
		}
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 评论审核
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review")
	@ResponseBody
	public Object review(@RequestParam(value="ids")String ids,@RequestParam(value="state")Integer state)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Comment comment=new Comment();
			comment.setState(state);
			comment.setId(Integer.parseInt(idsStr[i]));
			commentService.update(comment);
		}
		Map<String,Object> result = new HashMap<>();
		result.put("success", true);
		return ajaxSuccessReturnMap(result);
	}
}
