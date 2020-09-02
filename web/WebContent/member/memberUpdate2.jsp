<%@page import="member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberUpdate</title>
<script>
	function inputCheck(){
		//id, pw 필수입력 체크
		if(frm.id.value == ""){
			window.alert("id 입력");
			frm.id.focus();
			return;
		}
		if(frm.pw.value == ""){
			window.alert("패스워드 입력");
			frm.pw.focus();
			return;
		}
		//job(select 태그) 선택 유무 확인
		if(frm.job.value == ""){
		//if(frm.job.selectedIndex == 0)  //위에꺼랑 이거 중에 골라 쓰면ㅇㅇ
			alert("job 선택");
			frm.job.focus();
			return;
		}
		//radio, checkbox 체크
		var gender=
			document.querySelectorAll("[name='gender']:checked").length;
		if(gender == 0) {
			alert("성별 선택");
			return false;
		}
		
		
		//회원가입폼 제출
		frm.submit();
		
		return true;
	}
</script>
</head>
<body>

<%@include file="/common/header.jsp" %>

<h3 class="page_title">내정보조회</h3>
<div class="regist">
<form name="frm" method="post" id="frm" 
		action="memberUpdate"
		onsubmit="return inputCheck()">
	<div>
		<label for="id">ID</label>
		<input type="text" id="id" name="id" value="${login.id }" readonly="readonly">
	</div>
	<div>
		<label for="pw">PW</label>
		<input type="password" id="pw" name="pw" value="${login.pw }">
	</div>
	<div>
		<label for="gender">성별</label>
		<input type="radio" id="female" name="gender" value="f" 
			<c:if test ="${login.gender=='f'}">checked="checked"</c:if>>
		<label for="female">여</label>
		<input type="radio" id="male" name="gender" value="m" 
			<c:if test ="${login.gender=='m'}">checked="checked"</c:if>>
		<label for="male">남</label>
	</div>
	<div>
		<label for="job">직업</label>
		<select id="job" name="job" size="4">
			<option value="">선택</option>
			<option value="developer" >프로그래머</option>
			<option value="DBA" >DBA</option>
		</select>
	</div>
	<div>
		<label for="reason">가입동기</label>
		<textarea name="reason" id="reason">${login.reason }</textarea>
	</div>
	<div>
		<label for="mail"> 메일수신여부</label>
		<input type="checkbox" name="mailyn" value="Y" 
			<c:if test ="${login.mailyn=='Y'}">checked="checked"</c:if>>
	</div>
	<div>
		<label for="hobby"> 취미</label>
		<input type="checkbox" name="hobby" value="read" >독서
		<input type="checkbox" name="hobby" value="game" >게임
		<input type="checkbox" name="hobby" value="ski" >스키
	</div>
	<div>
		<button type="reset">초기화</button>
		<!-- <button type="button" onclick="inputCheck()">등록</button> -->
		<button>input등록</button>
		<!-- form태그 안에 onsubmit="return inputCheck()"
		script에 밖에  return true; 해줌.
		-->

	</div>

</form>
</div>
</body>
</html>