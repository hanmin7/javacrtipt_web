<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberSelect</title>
</head>
<body>

	<h3 class="page_title">내 정보 조회</h3>
	<div><span class="label">아이디 : </span><span>chichi</span></div>
 	<div><span class="label">패스워드 : </span><span>1234</span></div>
 	<div><span class="label">직업 : </span><span>프로그래머</span></div>
 	<div><span class="label">가입동기 : </span><span>프로그램스터디</span></div>
 	<div><span class="label">성별 : </span><span>여</span></div>
 	<div><span class="label">메일수신여부 : </span><span>yes</span></div>
 	
 	<button type="button" id="btnPage">목록으로</button>  <!-- onclick="goPage()" 빼고  addEventListener로 작업-->
 	<script>
 		btnPage.addEventListener("click", goPage);
 	
 		function goPage(){
 			//history.back();
 			//location.href="memberAll.jsp"
 			//location.assign("memberAll.jsp");
 			history.go(-1);      //이전페이지로 이동
 		}
 	</script>
 	
</body>
</html>