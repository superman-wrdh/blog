<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 超锅编写与2016-08-02 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超人的博客登录</title>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js"></script>
	<script src="${pageContext.request.contextPath}/foreground/login/js/md5.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/foreground/login/css/reset.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/foreground/login/css/common.css" />
	<script src="${pageContext.request.contextPath}/foreground/login/js/fun.js"></script>
	

  </head>
  
  <body>
    <!-- 用户登录 -->
    <div class="wrap login_wrap">
			<div class="content">
				
				<div class="logo"></div>
				
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							登录
						</div>
						<form action="${pageContext.request.contextPath}/blogger/login.do" method="post">
							
							<div class="form_text_ipt">
								<input id="name" name="userName" type="text" placeholder="账号/邮箱" > <%-- value="${blogger.userName }" --%>
							</div>
							<div class="ececk_warning"><span>用户名不能为空</span></div>
							<div class="form_text_ipt">
								<input id="pwd" name="password" type="password" placeholder="密码">
							</div>
							
							<div class="ececk_warning"><span>密码不能为空</span></div>

							<div class="form_text_ipt">
									<input id="varifi" name="secretKey" type="text" placeholder="动态秘钥" >
							</div>

							<div class="form_check_ipt">
								
							</div>
							<div class="form_btn">
								<button id="btok" type="button" onclick="login()">登录</button>
							</div>
							<div class="form_reg_btn" >
								<span><font color="red" id="error">${errorInfo }</font></span>
							</div>
						</form>
						<div class="other_login">
							<div class="left other_left" style="display:none">
								<span>其它登录方式</span>
							</div>
							<div class="right other_right" style="display:none">
								<a href="#">QQ登录</a>
								<a href="#">微信登录</a>
								<a href="#">微博登录</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    <!-- 用户登录 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/foreground/login/js/common.js" ></script>
  </body>
</html>
