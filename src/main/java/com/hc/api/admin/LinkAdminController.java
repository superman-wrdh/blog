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

import com.hc.entity.Link;
import com.hc.entity.PageBean;
import com.hc.service.LinkService;

import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hc on 2017/2/2.
 * 友情链接Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController extends BaseController{

	@Autowired
	private LinkService linkService;

	/**
	 * 分页查询友情链接信息
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
		List<Link> linkList=linkService.list(map);
		Long total=linkService.getTotal(map);
		Map<String,Object> result = new HashMap<>();
		JSONArray jsonArray=JSONArray.fromObject(linkList);
		result.put("rows", jsonArray);
		result.put("total", total);
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 添加或者修改友情链接信息
	 * @param link
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(Link link)throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(link.getId()==null){
			resultTotal=linkService.add(link);
		}else{
			resultTotal=linkService.update(link);
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
	 * 删除友情链接信息
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
			linkService.delete(Integer.parseInt(idsStr[i]));
		}
		Map<String,Object> result = new HashMap<>();
		result.put("success", true);
		return ajaxSuccessReturnMap(result);
	}
}
