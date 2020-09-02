<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<%=request.getAttribute("errormsg") %>

	<form name="frm" method="post" id="frm" 
		action="login">
	<div>
		<label for="id">ID</label>
		<input type="text" id="id" name="id">
	</div>
	<div>
		<label for="pw">PW</label>
		<input type="password" id="pw" name="pw">
	</div>
	<button>로그인</button>
	</form>
</body>
</html>