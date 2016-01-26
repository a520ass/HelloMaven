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
		$(".delete").click(function(){
			var href=$(this).attr("href");
			$("#deleteform").attr("action",href).submit();
			return false;
		});
	});
</script>
</head>
<body>
	
	<form action="" method="post" id="deleteform">
		<input type="hidden" name="_method" value="DELETE">
	</form>
	
	<c:if test="${empty requestScope.employees}">
		没有任何员工信息
	</c:if>
	<c:if test="${!empty requestScope.employees}">
		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th>ID</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Birth</th>
				<th>Department</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="emp" items="${requestScope.employees }">
				<tr>
					<td>${emp.id }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.birth }</td>
					<td>${emp.department.departmentName }</td>
					<td><a href="emp/${emp.id }">编辑</a></td>
					<td><a href="emp/${emp.id }" class="delete">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br><br>
	<a href="emp">Add new Employee</a>
</body>
</html>