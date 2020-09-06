<%@page import="dept.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/common/header.jsp" %>

	<table border="1" id="members">
	
	
		<thead>
			<tr>
				<th>emp_id</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>email</th>
				<th>hire_date</th>
				<th>job_id</th>
				<th>manager_id</th>
				<th>department_id</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${empList}" var="emp" >
			<tr>
				<td><a href="empUpdate?employee_id=${emp.getEmployee_id()}">${emp.getEmployee_id()}</a></td>
				<td>${emp.getFirst_name()}</td>
				<td>${emp.getLast_name()}</td>
				<td>${emp.getEmail()}</td>
				<td>${emp.getHire_date()}</td>
				<td>${emp.getJob_id()}</td>
				<td>${emp.getManager_id()}</td>
				<td>${emp.getDepartment_id()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

</body>
</html>