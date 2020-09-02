<%@page import="member.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			</tr>
		</thead>
		<tbody>
		<% 
			ArrayList<MemberVo> list = (ArrayList<MemberVo>)request.getAttribute("list");
			for(MemberVo member : list) {
				
		%>
			<tr>
				<td><a href="memberSelect.jsp"><%=member.getId() %></a></td>
				<td><%=member.getPw()%></td>
				<td><%=member.getJob()%></td>
				<td><%=member.getReason()%></td>
				<td><%=member.getGender()%></td>
				<td><%=member.getMailyn()%></td>
				<td><%=member.getHobby()%></td>
			</tr>
		<% } %>
		</tbody>
	</table>
</body>
</html>