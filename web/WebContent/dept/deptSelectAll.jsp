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
		<tr><td><a href="deptSelect?department_id=<%=dept.getDepartment_id()%>"><%=dept.getDepartment_id()%></a></td>
						<!-- a태그에서 href에 url?파라미터값=  %값  넘겨주는값이 여러개일 때 & , 한글은 인코딩 필요 -->
			<td><%=dept.getDepartment_name()%></td></tr>
	<% } %>
	</table>
</body>
</html>