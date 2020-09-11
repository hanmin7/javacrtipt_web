<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardInsert</title>
<script>
	function inputCheck(){
		// 필수입력 체크
		if(frm.subject.value == ""){
			window.alert("제목 입력");
			frm.subject.focus();
			return false;
		}
		if(frm.contents.value == ""){
			window.alert("내용 입력");
			frm.contents.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<%@include file="/common/header.jsp" %>

<h3 class="page_title">게시글등록</h3>
<div class="regist">
<form name="frm" method="post" id="frm" 
		action="boardInsert.do"
		onsubmit="return inputCheck()"
		enctype="multipart/form-data">
	
	<div>
		<label for="poster">writer</label>
		<input type="text" id="poster" name="poster">
	</div>
	<div>
		<label for="subject">subject</label>
		<input type="text" id="subject" name="subject">
	</div>

	<div>
		<label for="contents">contents</label>
		<textarea name="contents" id="contents"></textarea>
	</div>
	
	<div>
		<label for="filename">Select a file:</label>
  		<input type="file" id="filename" name="filename">
	</div>
	<div>
		<button type="reset">초기화</button>
		<button type="submit" >등록</button>
	</div>

</form>
</div>
</body>
</html>