<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/view.css" rel="stylesheet">
  <!-- 返回顶部调用 begin -->
  <link href="${pageContext.request.contextPath}/css/lrtk.css" rel="stylesheet" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script> 
  <script src="${pageContext.request.contextPath}/js/modernizr.js"></script>
<div class="data_list" style="background-color: #888888">
		<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/list_icon.png"/>
		搜索&nbsp;<font color="red">${q }</font>&nbsp;的结果 &nbsp;(总共搜索到&nbsp;<font color="red">${resultTotal}</font>&nbsp;条记录) </div>
		<div class="datas">
			<ul>
			
			 <c:choose>
					<c:when test="${blogList.size()==0 }">
						<div align="center" style="padding-top: 20px">未查询到结果，请换个关键字试试看！</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="blog" items="${blogList }">
					  	  <li style="margin-bottom: 20px">
						  	<span class="title"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html" target="_blank">${blog.title }</a></span>
						  	<span class="summary">摘要: ${blog.content }...</span>
						  	<span class="link"><a style="color:#52e113" href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">http://www.66super.cn/blog/articles/${blog.id}.html</a>&nbsp;&nbsp;&nbsp;&nbsp;发布日期：${blog.releaseDateStr }</span>
						  </li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
   </div>

<div>
	
	  <ul style="float:left">
	    ${pageCode }
	  </ul>
	
 </div>
 <script type="text/javascript" src="http://www.66super.com/js/neon_text.js"></script>
<script type="text/javascript">
$(document).ready(function () {
			
			$('.cus_faguang').neonText({
				textSize: '18px'				
			});
           
 })
 </script>