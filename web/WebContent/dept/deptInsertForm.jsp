<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptInsertForm.jsp</title>
</head>
<body>
	<form action="deptInsert" method="post">
		부서번호<input name="department_id"><br>
		부서명<input name="department_name"><br>
		지역<select name="location_id">
			<option value="">선택</option>
				<c:forEach items="${locationList}" var="locList" >
				<option value="${locList.location_id}">${locList.city}</option>
				</c:forEach>
			</select>
		<br>
		매니저<select name="manager_id">
			<option value="">선택</option>
				<c:forEach items="${managerList}" var="mgrList" >
				<option value="${mgrList.employee_id}">${mgrList.first_name} ${mgrList.last_name}</option>
				</c:forEach>
			</select>
		<button>등록</button>
	</form>
</body>
</html>