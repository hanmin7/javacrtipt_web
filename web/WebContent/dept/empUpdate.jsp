<%@page import="dept.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empUpdate.jsp</title>
</head>
<body>
	<%@include file="/common/header.jsp" %>

   <h1>정보수정</h1>
   <form action="empUpdate" method="post">  
    <div><label>employeeId</label>
    	<input name="employee_id" value="${empVo.employee_id}" readonly="readonly"></div>
    <div><label>firstName</label> <input name="first_name" value="${empVo.first_name }"></div>
    <div><label>lastName</label> <input name="last_name" value="${empVo.last_name }"></div>
    <div><label>email</label> <input name="email" value="${empVo.email }"></div>
    <div><label>hireDate</label> <input name="hire_date" value="${empVo.hire_date}"></div>
    <div><label>department_id</label>
    	<input type="radio" name="department_id" value="">null
    	<c:forEach items="${list}" var="deptList">
    	<input type="radio" name="department_id" value="${deptList.department_id}"
    		<c:if test = "${empVo.department_id == deptList.department_id }"> checked="checked"</c:if>>${deptList.department_name}
    	</c:forEach>
    </div>
    <div><label>jobId</label>
    	<select name="job_id">
    		<c:forEach items="${jobList}" var="jblist">
    		<option value="${jblist.job_id}" 
    			<c:if test="${empVo.job_id == jblist.job_id }"> selected="selected"</c:if>>${jblist.job_title}</option>
    		</c:forEach>
    	</select></div>
    <div><label>manager_id</label>
    	<select name="manager_id">
    		<option value="">선택</option>
    		<c:forEach items="${managerList}" var="mgrList" >
			<option value="${mgrList.employee_id}"
				<c:if test="${empVo.manager_id == mgrList.employee_id }"> selected="selected"</c:if>>${mgrList.first_name} ${mgrList.last_name}</option>
			</c:forEach>
    	</select></div>
    <button>등록</button>
    </form>
</body>
</html>