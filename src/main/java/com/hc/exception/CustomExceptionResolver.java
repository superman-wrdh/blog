package com.hc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest arg0,
										 HttpServletResponse arg1, Object arg2, Exception ex) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView();


		try {
			CusException cusException = (CusException) ex;
		} catch (Exception e) {
			// TODO: handle exception
		}
		//将错误信息传到页面
		String errormessage="系统错误";
		modelAndView.addObject("errormessage",errormessage);

		//指向错误页面
		modelAndView.setViewName("error");


		return modelAndView;
	}
}