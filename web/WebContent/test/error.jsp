<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error.jsp</title>
</head>
<body>
에러발생. 관리자에게 문의바람. <br>
에러내용 : <%=exception.getMessage() %><br>
에러타입 : <%=exception.getClass().getName() %>
</body>
</html>