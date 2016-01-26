<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
	});
</script>
</head>
<body>
	<br>
	${requestScope.username}登陆成功了<br>
	<shiro:hasAnyRoles name="user">
	<a href="${ctx }/user/info">user 权限的 page</a>
	</shiro:hasAnyRoles>
	<br>
	<shiro:hasAnyRoles name="admin">
	<a href="${ctx }/admin/info">admin 权限的 page</a>
	</shiro:hasAnyRoles>
	<br>
	<a href="${ctx }/logout">退出登录</a>
	<br>
	<form action="test/testFileUpload" method="post" enctype="multipart/form-data">
		File:<input type="file" name="file">
		Desc:<input type="text" name="desc">
		<input type="submit" value="sumit">
	</form>
	<br>
</body>
</html>