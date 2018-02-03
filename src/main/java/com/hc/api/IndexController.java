package com.hc.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hc.service.BloggerService;
import com.hc.service.MyBlogService;
import com.hc.util.BlogUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hc.entity.Blog;
import com.hc.entity.PageBean;
import com.hc.service.BlogService;
import com.hc.util.PageUtil;
import com.hc.util.StringUtil;

/**
 * Created by hc on 2017/2/2.
 * 主页Controller
 * @author hc
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private BlogService blogService;
    @Autowired
    private BloggerService bloggerService;
    @Autowired
	MyBlogService myBlogService;
	/**
	 * 请求主页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,@RequestParam(value="typeId",required=false)String typeId,@RequestParam(value="releaseDateStr",required=false)String releaseDateStr,HttpServletRequest request)throws Exception{

		myBlogService.VistorCount();

		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("typeId", typeId);
		map.put("releaseDateStr", releaseDateStr);
		List<Blog> blogList=blogService.list(map);

		for(Blog blog:blogList){
			BlogUtil.fillBlogImg(blog,"jpg");
		}

		for(Blog blog:blogList){
			BlogUtil.fillBlogImg(blog,"png");
		}

		mav.addObject("blogList", blogList);
		StringBuffer param=new StringBuffer(); // 查询参数
		if(StringUtil.isNotEmpty(typeId)){
			param.append("typeId="+typeId+"&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)){
			param.append("releaseDateStr="+releaseDateStr+"&");
		}
		mav.addObject("pageCode",PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()));
		mav.addObject("mainPage", "foreground/blog/list.jsp");
		mav.addObject("pageTitle","Java博客系统");
		mav.setViewName("mainTemp");
		return mav;
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/site")
	public ModelAndView download()throws Exception{
		ModelAndView mav=new ModelAndView();
        mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage", "foreground/system/download.jsp");
		mav.addObject("pageTitle","Java博客系统");
		mav.setViewName("mainTemp");
		return mav;
	}
}
