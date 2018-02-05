<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>超锅博客登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/css/supersized.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/css/style.css">
        <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/login/js/supersized.3.2.7.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/login/js/supersized-init.js"></script>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body oncontextmenu="return false">

        <div class="page-container" style="/*border: 1px solid red*/;width: 430px;height: 380px;padding-top: 30px;background-image: url('http://www.66super.com/static/login/img/loginbg.png')">
            <h1>登录</h1>
            <form action="${pageContext.request.contextPath}/blogger/login.do" method="post">
				<div>
					<input type="text" name="userName" class="username" placeholder="用户名" autocomplete="off"/>
				</div>
                <div>
					<input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false" />
                </div>
                <%--<div>
                    <input type="text" name="secretKey" class="password" placeholder="动态秘钥" autocomplete="off"/>
                </div>--%>
                <div>
                    <span style="color: red">${errorInfo }</span>
                </div>
                <button id="submitButton" type="button">登录</button>
            </form>
            <!--<div class="connect">
                <p>If we can only encounter each other rather than stay with each other,then I wish we had never encountered.</p>
				<p style="margin-top:20px;">弹出文字</p>
            </div>-->
        </div>
		<div class="alert" style="display:none">
			<h2>消息</h2>
			<div class="alert_con">
				<p id="ts"></p>
				<p style="line-height:70px"><a class="btn">确定</a></p>
			</div>
		</div>

        <!-- Javascript -->

		<script type="text/javascript">
            $(document).ready(function () {
                $(".btn").click(function(){
                    is_hide();
                })
                var u = $("input[name=userName]");
                var p = $("input[name=password]");
                var rp = $("input[name=secretKey]");
                $("#submitButton").click(function () {

                    if(u.val() == '')
                    {
                        $("#ts").html("用户名不能为空~");
                        is_show();
                        return false;
                    }if(p.val() ==''){
                        $("#ts").html("密码不能为空~");
                        is_show();
                        return false;
                    }if(rp.val()==''){
                        $("#ts").html("动态秘钥不能为空~");
                        is_show();
                        return false;
                    }
                    else{
                        var reg = /^[0-9A-Za-z]+$/;
                        if(!reg.exec(u.val()))
                        {
                            $("#ts").html("用户名错误");
                            is_show();
                            return false;
                        }
                        $("form")[0].submit();
                    }
                });

                $(".connect p").eq(0).animate({"left":"0%"}, 600);
                $(".connect p").eq(1).animate({"left":"0%"}, 400);

                function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
                function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
            });

		</script>
    </body>

</html>

