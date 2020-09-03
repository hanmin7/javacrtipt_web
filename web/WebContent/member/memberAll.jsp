<%@page import="member.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberAll</title>
</head>
<body>
<%@include file="/common/header.jsp" %>

	<h3 class="page_title">회원 전체조회</h3>
	<ul class="search">
		<li>메일수신여부</li>
		<li>성별</li>
		<li><button type="button">검색</button></li>
	</ul>

	<table border="1" id="members">
		<thead>
			<tr>
				<th>ID</th>
				<th>PW</th>
				<th>JOB</th>
				<th>가입동기</th>
				<th>성별</th>
				<th>메일수신여부</th>
				<th>취미</th>
				<th>가입일자</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="member" >
			<tr>
				<td><a href="memberSelect.jsp">${member.getId()}</a></td>
				<td>${member.getPw()}</td>
				<td>${member.getJob()}</td>
				<td>${member.getReason()}</td>
				<td>${member.getGender()}</td>
				<td>
					<c:if test="${member.getMailyn() == 'Y'}">${member.getMailyn()} <button>메일전송</button></c:if>
					<c:if test="${member.getMailyn() != 'Y'}">${member.getMailyn()} </c:if>
				</td>
				<td>${member.getHobby()}</td>
				<fmt:parseDate value="${member.getRegdate()}" pattern="yyyy-MM-dd HH:mm:ss" var="parseRegdate" />
				<td><fmt:formatDate value="${parseRegdate}" pattern="MM/dd"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>