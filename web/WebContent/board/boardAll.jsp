<%@page import="board.BoardVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardAll</title>
</head>
<body>
<%@include file="/common/header.jsp" %>

	<h3 class="page_title">보드 전체조회</h3>
<!-- 	<ul class="search">
		<li>메일수신여부</li>
		<li>성별</li>
		<li><button type="button">검색</button></li>
	</ul> -->

	<table border="1" id="boards">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성일자</th>
				<th>조회수</th>
				<th>첨부파일</th>
				<th>이미지</th>
			</tr>
		</thead>
		<tbody>
		<%
			ArrayList<BoardVo> list = (ArrayList<BoardVo>)request.getAttribute("list");
			for(BoardVo board : list) {
				
		%>
			<tr>
				<td><a href="boardSelect.jsp"><%=board.getNo() %></a></td>
				<td><%=board.getPoster()%></td>
				<td><%=board.getSubject()%></td>
				<td><%=board.getContents()%></td>
				<td><%=board.getLastpost()%></td>
				<td><%=board.getViews()%></td>
				<td><a href="filenameDownload.do?filename=<%=board.getFilename()%>"><%=board.getFilename()%></a></td>
				<td>

						<img src="../images/<%=board.getFilename()%>" style="width:100px">

				</td>
			</tr>
		<% } %>	
		</tbody>
	</table>
</body>
</html>