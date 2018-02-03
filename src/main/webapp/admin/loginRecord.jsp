<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/GridManager.css">
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/GridManager.js"></script>
<title>最近10次登录记录</title>
<style>
	html, body{
		width: 100%;
		height: 100%;
		overflow-x:hidden;
		margin: 0px;
		padding: 0px;
	}
</style>
</head>

<body>
    <div style="text-align: center;font-size: 45px ;color: red">最近10次登录记录</div>
	<div class="dialog-demo">
		<table grid-manager="demoOne"></table>
	</div>
<script type="text/javascript" >
	$('table').GM({
		supportRemind: true
		,gridManagerName: 'test'
		,disableCache:true
		,height: '300px'
		,supportAjaxPage:true
		,supportSorting: true
		,ajax_url: 'http://www.66super.com/api/blog/loginRecode/10.do'
		,ajax_type: 'POST'
		,query: {pluginId: 1}
		,columnData: [{
			key: 'loginName',
			remind: 'the info',
			text: '登录名称',
			width: '120px',
			sorting: ''
		},{
			key: 'status',
			remind: 'the url',
			text: '登录状态',
			width: '120px'
		},{
			key: 'date',
			remind: 'the createDate',
			text: '登录时间',
			sorting: '',
			width: '120px'
		},{
			key: 'ip',
			remind: 'the name',
			width: '100px',
			text: '登录ip',
			sorting: 'up',
		}
		]
	});

</script>
</body>
</html>
