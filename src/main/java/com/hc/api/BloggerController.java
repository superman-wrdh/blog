package com.hc.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.service.MyBlogService;
import com.hc.util.DateUtil;
import com.hc.util.RESUtil;
import com.hc.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hc.entity.Blogger;
import com.hc.service.BloggerService;
import com.hc.util.CryptographyUtil;

/**
 * Created by hc on 2017/2/2.
 * 博主Controller层
 * @author hc
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private MyBlogService myBlogService;

	/**
	 * 用户登录
	 * @param blogger
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();
		String key = RESUtil.getString("randSecretKey");
		String systemKey=CryptographyUtil.generateRandKey(RESUtil.getString("randSecretKeySalt"));
		System.out.println();

		//动态秘钥验证 输入全部动态秘钥，或者前6位或者万能秘钥
		if(!blogger.getSecretKey().equals(systemKey)){
			if(!blogger.getSecretKey().equals(systemKey.substring(0,6))){
				if(!blogger.getSecretKey().equals(key)){
					request.setAttribute("blogger", blogger);
					request.setAttribute("errorInfo", "动态秘钥输入错误");
					return "login";
				}
			}
		}

		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), "hc"));
		try{
			subject.login(token); // 登录验证
			myBlogService.createOneLoginRecord(request,"登录成功",blogger.getUserName());
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			myBlogService.createOneLoginRecord(request,"登录失败",blogger.getUserName());
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或密码错误！");
			return "login";
		}
	}

	/**
	 * 查找博主信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/site")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.addObject("pageTitle","java博客系统");
		mav.setViewName("mainTemp");
		return mav;
	}
}
