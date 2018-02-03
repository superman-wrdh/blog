<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
	.datas .img{
		display:block;
		clear:both;
	}

	.datas img{
		padding:10px;
		padding-left:0px;
		max-height: 150px;
		max-width:300px;
	}
</style>
<div class="">
		<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/list_icon.png"/>
		<font color="white">最新文章</font></div>
		<div class="">
			<ul>
			
			  <c:forEach var="blog" items="${blogList}">
			  	  <li style="margin-bottom: 30px">
					<p  style="" align="center"><a class="mytitle" href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title }</a></p>

				  	<span class="summary">摘要: ${blog.summary }...</span>
					  <%--暂时屏蔽该代码--%>
				  	  <%--<span class="img datas">
				  		<c:forEach var="image" items="${blog.imagesList }">
					  		<a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${image }</a>
					  		&nbsp;&nbsp;
				  		</c:forEach>
				  	</span>--%>
				  	<p align="right" class="myinfo">发表于 <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/> 阅读(${blog.clickHit}) 评论(${blog.replyHit}) </p>
				  </li>
				  <hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
			  </c:forEach>
			</ul>
		</div>
   </div>

<div>
	
	 <nav>
	  <ul class="pagination pagination-sm" style="background-color: #1B1717">
	    ${pageCode }
	  </ul>
	</nav>
	
 </div>