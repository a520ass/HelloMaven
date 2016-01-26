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
		$("#birth").datetimepicker({
			format:'yyyy-mm-dd',
			language: 'zh-CN',
			autoclose: true,
			todayBtn: true
		});
	});
</script>
</head>
<body>
	<form:form action="${ctx}/emp" method="POST" modelAttribute="employee">
		<c:if test="${employee.id==null }">
			LastName:<form:input path="lastName"/>
			<form:errors path="lastName"></form:errors>
		</c:if>
		<c:if test="${employee.id!=null }">
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT">
		</c:if>
		
		<br>
		Email:<form:input path="email"/>
		<form:errors path="email"></form:errors>
		<br>
		Birth:<form:input path="birth" readonly="true"/>
		<form:errors path="birth"></form:errors>
		<br>
		Department:<form:select path="department.id"
		items="${departments }" itemLabel="departmentName" itemValue="id"></form:select>
		<br>
		<input type="submit" value="提交">
	</form:form>
</body>
</html>