<%@page import="dept.DeptVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptSelectAll.jsp</title>
</head>
<body>
	<table border="1">
	<% 
		ArrayList<DeptVo> list = (ArrayList<DeptVo>)request.getAttribute("list");
		for(DeptVo dept : list) {
	%>
		<tr><td><%=dept.getDepartment_id()%></td>
			<td><%=dept.getDepartment_name()%></td></tr>
	<% } %>
	</table>
</body>
</html>