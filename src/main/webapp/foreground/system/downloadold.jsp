<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js"></script>
	<link href="${pageContext.request.contextPath}/css/show_windows.css" rel="stylesheet">
	
	
	
    <link href="css/lrtk.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/neon_text.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/raphael.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.uled.js"></script>
    
    <script src="${pageContext.request.contextPath}/js/modernizr.js"></script>
	<script src="${pageContext.request.contextPath}/js/l-byl.js"></script>
	<script src="${pageContext.request.contextPath}/js/l-by-l.min.js"></script>
	
	<script type="text/javascript">
        $(document).ready(function () {
            $(".word1").lbyl({
                content: "本站使用J2EE开发；",
                speed: 100,
                type: 'show',
                finished: function(){
                    $('.word10').lbyl({
                        content:".........................   Loading 20%   .........................",
                        speed: 10,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });
			
			$(".word2").lbyl({
                content: "使用Spring4+Springmvc+Mybatis3架构，",
                speed: 100,
                type: 'show',
                finished: function(){
                    $('.word10').lbyl({
                        content:".........................   Loading 50%   .........................",
                        speed: 100,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });

            $(".word3").lbyl({
                content: "采用MySQL数据库；",
                speed: 100,
                type: 'show',
                finished: function(){
                    $('.word10').lbyl({
                         content:".........................   Loading 20%   .........................",
                        speed: 150,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });
			
			 $(".word4").lbyl({
                content: "使用Maven3管理项目,使用Lucene作为全文检索，支持restful风格；",
                speed: 10,
                type: 'show',
                finished: function(){
                    $('.word10').lbyl({
                       content:".........................   Loading x%   .........................",
                        speed: 150,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });
			
			
			
			
            $(".word5").lbyl({
                content: "本站大量使用了JQuery特效插件,前台网页使用主流的Bootstrap3 UI框架；后台管理使用主流易用的EasyUI轻量级框架；",
                speed: 100,
                type: 'show',
                finished: function(){
                    $('.word6').lbyl({
                        content:"数据库连接池使用的是阿里巴巴的Druid；",
                        speed: 150,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });

            $(".word7").lbyl({
                content: "在线编辑器使用了百度的UEditor，支持单图，多图上传，支持截图上传，支持多种语言代码高亮特性；",
                speed: 100,
                type: 'show',
                finished: function(){
                    $('.word8').lbyl({
                        content:"开发时间2016-7-27",
                        speed: 150,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });
			
			
			$(".word9").lbyl({
                content: "版本v6.0 最近更新于2016-09-08",
                speed: 100,
                type: 'show',
                finished: function(){
                    $('.word10').lbyl({
                        content:".........................   Loading 30%   .........................",
                        speed: 10,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });
			
			$(".word11").lbyl({
                content:"....................................................................................................................................",
                speed: 100,
                type: 'show',
                finished: function(){
                    $('.word10').lbyl({
                        content:"....................................................................................................................................",
                        speed: 5,
                        type: 'fade',
                        fadeSpeed: 500
                    })
                } // Finished Callback
            });
			
			
			 $("p span").neonText({
				 textSize: '16px'
			 });
			
        })
    </script>
	
	<style>
		p{color:#ffffff;
		font:bold 16px/26px arial;
		}		
	</style>
<div class="data_list" style="background-color:#2b2b2b;border: 1px solid #1e1b29">
	<div class="data_list_title" style="border-bottom: 0px solid #E5E5E5;">
		<img src="${pageContext.request.contextPath}/static/images/download_icon.png"/>
		关于本站
	</div>
	<div>
	<p>
    <br/>
</p>

<p class="word11">
   
</p>

<p class="word1">
   
</p>
<p>
    <br/>
</p>
<p class="word2">
   
</p>
<p class="word3">
    
</p>
<p class="word4">
    
<p class="word5">
    
</p>
<p class="word6">
    
</p >
<p class="word7">
    
</p>
<p>
    <br/>
</p>
<p class="word8">
  
</p>
<p class="word9">
    
</p>

<p class="word10">
    
</p>

	</div>
</div>
