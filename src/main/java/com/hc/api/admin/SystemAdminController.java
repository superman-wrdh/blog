package com.hc.api.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.hc.entity.Blog;
import com.hc.entity.BlogType;
import com.hc.entity.Blogger;
import com.hc.entity.Link;
import com.hc.service.BlogService;
import com.hc.service.BlogTypeService;
import com.hc.service.BloggerService;
import com.hc.service.LinkService;
import com.hc.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * Created by hc on 2017/2/2.
 * 管理员系统Controller层
 * @author hc
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController extends BaseController{

	@Autowired
	private BloggerService bloggerService;

	@Autowired
	private BlogTypeService blogTypeService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private LinkService linkService;

	/**
	 * 刷新系统缓存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	@ResponseBody
	public Object refreshSystem(HttpServletRequest request)throws Exception{
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		Blogger blogger=bloggerService.find(); // 查询博主信息
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);

		List<BlogType> blogTypeCountList=blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);

		List<Blog> blogCountList=blogService.countList(); // 根据日期分组查询博客
		application.setAttribute("blogCountList", blogCountList);

		List<Link> linkList=linkService.list(null); // 获取所有友情链接
		application.setAttribute("linkList", linkList);

		Map<String,Object> result = new HashMap<>();
		result.put("success", true);
		return ajaxSuccessReturnMap(result);
	}
}
