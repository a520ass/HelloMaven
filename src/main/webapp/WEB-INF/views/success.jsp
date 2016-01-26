<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
	<p>跳转成功了。。。</p>
	<fmt:message key="i18n.username"></fmt:message>
	
	${requestScope.time}<br>
	${requestScope.names}<br>
	
	requestScope.car${requestScope.car}<br>
	sessionScope.car${sessionScope.car}<br>
	requestScope.testString${requestScope.testString}<br>
	sessionScope.testString${sessionScope.testString}<br>
	
	department${requestScope.department.departmentName}<br>
	
	<br><br>
	<fmt:message key="i18n.username"></fmt:message>
	<br><br>
	<fmt:message key="i18n.password"></fmt:message>
</body>
</html>