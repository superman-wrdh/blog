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

import com.hc.entity.BlogType;
import com.hc.entity.PageBean;
import com.hc.service.BlogService;
import com.hc.service.BlogTypeService;
import com.hc.util.ResponseUtil;

import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员博客类别Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController extends BaseController{

	@Autowired
	private BlogTypeService blogTypeService;

	@Autowired
	private BlogService blogService;

	/**
	 * Created by hc on 2017/2/2.
	 * 分页查询博客类别信息
	 * @param page
	 * @param rows
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<BlogType> blogTypeList=blogTypeService.list(map);
		Long total=blogTypeService.getTotal(map);
		Map<String,Object> result = new HashMap<>();
		JSONArray jsonArray=JSONArray.fromObject(blogTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 添加或者修改博客类别信息
	 * @param blogType
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(BlogType blogType)throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(blogType.getId()==null){
			resultTotal=blogTypeService.add(blogType);
		}else{
			resultTotal=blogTypeService.update(blogType);
		}
		Map<String,Object> result = new HashMap<>();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 删除博客类别信息
	 * @param ids
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam(value="ids")String ids)throws Exception{
		String []idsStr=ids.split(",");
		Map<String,Object> result = new HashMap<>();
		for(int i=0;i<idsStr.length;i++){
			if(blogService.getBlogByTypeId(Integer.parseInt(idsStr[i]))>0){
				result.put("exist", "博客类别下有博客，不能删除！");
			}else{
				blogTypeService.delete(Integer.parseInt(idsStr[i]));
			}
		}
		result.put("success", true);
		return ajaxSuccessReturnMap(result);
	}
}
