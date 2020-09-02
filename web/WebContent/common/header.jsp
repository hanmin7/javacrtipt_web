<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<% String id = (String)session.getAttribute("id"); %>
<ul>
<% if(id==null) { %>
	<li><a href="/web/member/login.jsp">로그인</a>
<% } else { %>
	<%=id%>님<a href="/web/member/logout">로그아웃</a>
	<li><a href="/web/member/memberUpdate">정보수정</a>
<% } %>
	<li><a href="<%=application.getContextPath() %>/dept/DeptInsertFormServ">부서등록폼</a>
	<li><a href="/web/dept/deptSelectAll">부서전체조회</a>
	<li><a href="/web/member/memberInsert.do">회원가입</a>
	<li><a href="/web/member/memberSelectAll.do">회원전체조회</a>
	<li><a href="/web/board/boardInsert.do">보드입력</a>
	<li><a href="/web/board/boardSelectAll.do">보드전체조회</a>
</ul>
