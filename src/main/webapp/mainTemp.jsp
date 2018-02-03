<%--
  Created by IntelliJ IDEA.
  User: hc
  Date: 2016/10/6
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <meta charset="utf-8">
	<meta name="baidu-site-verification" content="prZpIfiwD7" />
    <title>超锅的博客</title>
	<link href="http://www.66super.com/favicon.ico" rel="SHORTCUT ICON">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
		<link href="favicon.ico" rel="SHORTCUT ICON">
		<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>

		
		  <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqueryui/jquery-ui.js"></script>
		  <link href="${pageContext.request.contextPath}/static/jqueryui/jquery-ui.css" rel="stylesheet">


		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/foreground/lunbotu/css/base.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/foreground/lunbotu/css/main.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/lunbotu/js/jquery.easing.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/lunbotu/js/main.js"></script>
	
	
	<script>
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "//hm.baidu.com/hm.js?76d4f677a8da6ba52638bbfebaa150c7";
			  var s = document.getElementsByTagName("script")[0]; 
			  s.parentNode.insertBefore(hm, s);
			})();
	</script>

   <%-- <script type="text/javascript">
        var system ={};
        var p = navigator.platform;
        system.win = p.indexOf("Win") == 0;
        system.mac = p.indexOf("Mac") == 0;
        system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
        if(system.win||system.mac||system.xll){
            //是电脑

        }else{
            //如果是手机,跳转到webapp
            window.location.href="http://www.66super.com/mobile";
        }
    </script>--%>

    <meta name="keywords" content="超锅的小小站v5" />
    <meta name="description" content="超锅的小小站v5" />
    <link href="css/styles.css" rel="stylesheet">
    <link href="css/animation.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/show_windows.css" rel="stylesheet">
    <link href="css/lrtk.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/neon_text.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/raphael.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.uled.js"></script>
    
    <script src="${pageContext.request.contextPath}/js/modernizr.js"></script>
</head>

<script type="text/javascript">
	
	 $(document).ready(function () {
           
			$('.cus_faguang').neonText({
				textSize: '18px'				
			});
			
			
			$('.cus_faguang2').neonText({
                textColor: '#a5a1a1',
                textSize: '18px',
                neonHighlight: '#aaFFFF',
                neonHighlightColor: '#aa00DE',
                neonHighlightHover: '#00aaaa',
                neonFontHover: '#aaaaaa'

            });
			
       //LED数码时间显示     
			var l2 = new uLED({
                id : "led2",
                type : "time",
                format : "hh:mm:ss",
                color : "#eee",
                bgcolor : "#000000",
                size : 2,
                rounded : 4
            });
		//结束 2016-10-14	
		
		
			//*************清除窗口*******
			 $("#span_confirm").click(function () {
				$("#show_win").css("display","none")
			})
						
			//*******************
		
		
		//*********************联系超锅****************************
		 $("#contact_me").click(function () {
				$("#show_win_sendmessage").css("display","block")
		})
		
		
		 $("#show_win_sendmessage").click(function () {
				$("#show_win_sendmessage").css("display","none")
			})
		
		//***********************************************
		
		
			
        })		
		
	function mysearch() {
      var searchword=$("#searchword").val();
      if(searchword.trim()=="" ||searchword.trim()==""){
        //alert("超锅提醒 关键字不能为空！请输入关键字搜索")
		//***************
			 $("#show_win").css("display","block")				
		//*******************
       return false;
      }else{
        $("form")[0].submit();
        return true;
      }
    }
</script>

<style>
	a:link {  text-decoration: none;  }
    a:visited {  text-decoration: none;  }
    a:hover {  text-decoration: none;  }
    a:active { text-decoration: none}

  .form_btn{
    width: 95%;
    height: 60px;
    margin:  10px auto;
  }
  .form_btn button{
    width: 90%;
    height: 100%;
    border: none;
    color: white;
    font-size: 14px;
    background: #473e3e;
    cursor: pointer;
  }
</style>

<body>
       <!--空字符串提示窗口-->
	<div class="pop" id="show_win">
    <div class="popMain">
        <div class="popTop"></div>
        <div class="popMiddle">
			<div style="height:39px;width:auto;background:#488ACC;font-size:26px;text-align:center" >超锅提醒</div>
            <p align="center">亲，你不能输入空！请输入关键词进行搜索</p>
        </div>
        <div class="popBottom">
            <span class="confirm" id="span_confirm" >确认</span>
        </div>
    </div>
	</div>
	 <!--提示窗口-->

 

<!--联系超锅-->
		<div class="pop" id="show_win_sendmessage">
				<div class="popMain">
        <div class="popTop"></div>
        <div class="popMiddle" align="center">
            <div style="height:39px;width:auto;background:#488ACC;font-size:26px;text-align:center" >联系超锅</div>
            <textarea style="font-size: 24px ;color: green;margin: 5px auto" rows="4" cols="48;" >输入你对超锅的留言</textarea>
            <button>发送</button> <button>清空</button>
        </div>
        <div class="popBottom">
            <span class="confirm" id="span_sent">确定</span>
        </div>
    </div>
		</div>
<!--联系超锅-->


<header>
    <nav id="nav">
        <ul>
            <li><a href="http://www.66super.com" class="cus_faguang">网站首页</a></li>
            <li><a href="https://superman-wrdh.github.io" target="_blank" title="个人博客" class="cus_faguang">关于站长</a></li>
            <li><a href="${pageContext.request.contextPath}/site.html"  title="关于网站" class="cus_faguang">关于本站</a></li>
            <li><a href="http://www.66super.com/search" target="_blank" title="超锅快搜" class="cus_faguang">超锅快搜</a></li>
            <li><a href="http://www.66super.com/translate" target="_blank" title="超锅翻译" class="cus_faguang">超锅翻译</a></li>
            <li><a href="http://www.66super.com/download" target="_blank" title="作品大全" class="cus_faguang">下载</a></li>
            <li></li>
			
			
        </ul>
        <script src="js/silder.js"></script><!--获取当前页导航 高亮显示标题-->
		<div id="led2" style="/*border: 1px solid red */;width:100px;height:25px;background: #222; display: table;margin-top:8px"></div>
    </nav>
</header>

<!--header end-->
<div id="mainbody">
    <div class="info">
        <figure> <img src="${pageContext.request.contextPath}/images/art.jpg"  alt="Panama Hat">
            <figcaption><strong>做的到和做不到，</strong> 其实只在一念之间。心有多大！舞台就有多大！</figcaption>
        </figure>
        <div class="card">
            <h1>我的名片</h1>
            <p>网名：超人</p>
            <p>职业：java程序员</p>
            <p>电话：1341####360</p>
            <p>Email：1359931498@qq.com</p>
            <ul class="linkmore">
                <li><a href="#" class="talk" title="给我留言" class="cus_faguang" id="contact_me"></a></li>
                <li><a href="javascript:void(0)" class="address" title="联系地址"></a></li>
                <li><a href="javascript:void(0)" class="email" title="给我写信"></a></li>
                <li><a href="javascript:void(0)" class="photos" title="生活照片"></a></li>
                <li><a href="javascript:void(0)" class="heart" title="关注我"></a></li>
            </ul>
        </div>
    </div>
   <!--info end-->
	
    <div class="blank"></div>
    <div class="blogs">
        <ul class="bloglist">

            <!--文章列表-->
			<jsp:include page="${mainPage }"></jsp:include>

            <!--文章列表-->

            
        </ul>
        <!--bloglist end-->
        <aside>
			<div class="search">
                <form class="searchform" method="post" action="${pageContext.request.contextPath}/blog/q.html">
                    <input id="searchword" type="text" name="q">
					
					<div class="form_btn">
						<Button id="bt_search" type="button" onclick="mysearch()" >&nbsp;搜&nbsp;&nbsp;索&nbsp;</Button>
					</div>
                </form>
            </div>

            <%--阅读量前10的文章--%>
            <div class="tuijian">
                <h2>按阅读量</h2>
                <ol>
                    <c:forEach var="hotBlog" items="${BlogOrderByClickTimeList}" varStatus="status">
                        <li><span><strong>${ status.index + 1}</strong></span><a href="${pageContext.request.contextPath}/blog/articles/${hotBlog.id}.html">${hotBlog.title}&nbsp;(${hotBlog.clickHit})</a></li>
                    </c:forEach>
                </ol>
            </div>
            <%--阅读量--%>
				
            <div class="tuijian">
                <h2>按文章类别</h2>
                <ul>
                    <c:forEach var="blogTypeCount" items="${blogTypeCountList }">
							<li><span><a href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
					</c:forEach>
                </ul>
            </div>
           
            <div class="clicks">
                <h2>按日期</h2>
                <ol>
                    <c:forEach var="blogCount" items="${blogCountList }">
							<li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr }">${blogCount.releaseDateStr }(${blogCount.blogCount })</a></span></li>
					</c:forEach>
                </ol>
            </div>
			
            
			
            <div class="viny">
                <dl>
                    <dt class="art"><img src="${pageContext.request.contextPath}/images/artwork.png" alt="专辑"></dt>
                    <dd class="icon-song"><span></span>Kiss The Rain</dd>
                    <dd class="icon-artist"><span></span>歌手：李闰珉</dd>
                    <dd class="icon-album"><span></span>所属专辑：from the yellow room</dd>
                    <dd class="icon-like"><span></span><a href="/">喜欢</a></dd>
                    <dd class="music">
                        <audio src="http://www.66super.com/kisstherain.mp3" controls></audio>
                    </dd>
                    <!--也可以添加loop属性 音频加载到末尾时，会重新播放-->
                </dl>
            </div>
        </aside>
    </div>
    <!--blogs end-->
</div>
<!--mainbody end-->
<footer >
    <div class="footer-mid">
        <div class="links" style="min-height:150px;">
            <h2>友情链接</h2>
            <ul>
                <li style="float: left;list-style: none"><a href="/" >超锅个人博客 &nbsp;&nbsp;&nbsp;&nbsp;</a></li>

                <li style="float: left;list-style: none"><a href="http://www.66super.com" >超锅技术服务中心&nbsp;&nbsp;&nbsp;&nbsp;</a></li>

                <c:forEach var="link" items="${linkList }">
                        <li style="float: left;list-style: none"><span><a href="${link.linkUrl }" target="_blank">${link.linkName } &nbsp;&nbsp;</a></span></li>

                </c:forEach>
            </ul>
        </div>

    </div>
    <div class="footer-bottom">
        <p>鄂ICP备16015580号 Copyright © 2013-2018 超锅  <a href="http://www.66super.com">版权所有</a></p>
    </div>
</footer>
<!-- jQuery回顶部和建议 代码开始 -->
<div id="tbox"> <a id="togbook" href="javascript:void(0)"></a> <a id="gotop" href="javascript:void(0)"></a> </div>
<!-- 代码结束 -->
</body>
</html>
