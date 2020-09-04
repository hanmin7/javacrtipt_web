<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
	label { display: inline-block; width : 100px}
</style>
</head>
<body>
<%@include file="/common/header.jsp" %>

   <h1>사원등록</h1>
   <form action="empInsert" method="post">  
    <div><label>employeeId</label> <input name="employeeId"></div>
    <div><label>firstName</label> <input name="firstName"></div>
    <div><label>lastName</label> <input name="lastName"></div>
    <div><label>email</label> <input name="email"></div>
    <div><label>hireDate</label> <input name="hireDate"></div>
    <div><label>department_id</label>
    	<c:forEach items="${list}" var="deptList">
    	<input type="radio" name="dept" value="${deptList.department_id}">${deptList.department_name}
    	</c:forEach>
    </div>
    <div><label>jobId</label>
    	<select name="jobId">
    		<c:forEach items="${jobList}" var="jblist">
    		<option value="${jblist.job_id}">${jblist.job_title}</option>
    		</c:forEach>
    	</select></div>
    <div><label>manager_id</label>
    	<select name="manager_id">
    		<c:forEach items="${managerList}" var="mgrList" >
			<option value="${mgrList.employee_id}">${mgrList.first_name} ${mgrList.last_name}</option>
			</c:forEach>
    	</select></div>
    <button>등록</button>
    </form>
</body>
</html>