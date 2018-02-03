package com.hc.api.admin;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hc.entity.Blogger;
import com.hc.service.BloggerService;
import com.hc.util.CryptographyUtil;
import com.hc.util.DateUtil;

/**
 * 管理员博主Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController extends BaseController{

	@Autowired
	private BloggerService bloggerService;

	/**
	 * Created by hc on 2017/2/2.
	 * 修改博主信息
	 * @param
	 * @param blogger
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(@RequestParam("imageFile") MultipartFile imageFile,Blogger blogger,HttpServletRequest request,HttpServletResponse response)throws Exception{
		if(!imageFile.isEmpty()){
			String filePath=request.getServletContext().getRealPath("/");
			String imageName=DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
			imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			blogger.setImageName(imageName);
		}
		int resultTotal=bloggerService.update(blogger);
		StringBuffer result=new StringBuffer();
		if(resultTotal>0){
			result.append("<script language='javascript'>alert('修改成功！');</script>");
		}else{
			result.append("<script language='javascript'>alert('修改失败！');</script>");
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(result.toString());
		out.flush();
		out.close();
	}

	/**
	 * 查询博主信息
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Object find()throws Exception{
		Blogger blogger=bloggerService.find();
		return blogger;
	}

	/**
	 * 修改博主密码
	 * @param newPassword
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	@ResponseBody
	public Object modifyPassword(String newPassword)throws Exception{
		Blogger blogger=new Blogger();
		blogger.setPassword(CryptographyUtil.md5(newPassword, "hc"));
		int resultTotal=bloggerService.update(blogger);
		Map<String,Object> result = new HashMap<>();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return ajaxSuccessReturnMap(result);
	}

	/**
	 * 注销
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String  logout()throws Exception{
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
