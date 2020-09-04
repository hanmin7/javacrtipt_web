<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookieSelect.jsp</title>
</head>
<body>
	<%  
		Cookie[] cookieList = request.getCookies();
		if(cookieList != null){
			for(Cookie cookie : cookieList){
				//if(cookie.getName().equals("id")) {
					out.print(cookie.getName() + " : " + cookie.getValue());
				//}
			}
		}
	%>
	
	<hr>
	
	${cookie.id.name} : ${cookie.id.value}<br>
	${cookie.popupYn.name} : ${cookie.popupYn.value}<br>
	<c:if test="${cookie.popupYn.value == 'yes'}">
		<script>
			alert("popup");
		</script>
	</c:if>
	
</body>
</html>