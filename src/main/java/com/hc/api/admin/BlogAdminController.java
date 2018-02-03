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

import com.hc.entity.Blog;
import com.hc.entity.PageBean;
import com.hc.lucene.BlogIndex;
import com.hc.service.BlogService;
import com.hc.util.ResponseUtil;
import com.hc.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by hc on 2017/2/2.
 * 管理员博客Controller层
 * @author hc
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController extends BaseController{

	@Autowired
	private BlogService blogService;

	// 博客索引
	private BlogIndex blogIndex=new BlogIndex();

	/**
	 * 添加或者修改博客信息
	 * @param blog
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(Blog blog)throws Exception{
		int resultTotal=0;
		if(blog.getId()==null){
			resultTotal=blogService.add(blog);
			blogIndex.addIndex(blog);
		}else{
			resultTotal=blogService.update(blog);
			blogIndex.updateIndex(blog);
		}
		Map<String,Object> result=new HashMap();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 分页查询博客信息
	 * @param page
	 * @param rows
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Blog s_blog)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(s_blog.getTitle()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Blog> blogList=blogService.list(map);
		Long total=blogService.getTotal(map);
		Map<String,Object> result=new HashMap();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(blogList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 删除博客信息
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
			blogService.delete(Integer.parseInt(idsStr[i]));
			blogIndex.deleteIndex(idsStr[i]); // 删除对应博客的索引
		}
		Map<String,Object> result=new HashMap();
		result.put("success", true);
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 通过ID查找实体
	 * @param id
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	@ResponseBody
	public Object findById(@RequestParam(value="id")String id)throws Exception{
		Blog blog=blogService.findById(Integer.parseInt(id));
		return blog;
	}



}
