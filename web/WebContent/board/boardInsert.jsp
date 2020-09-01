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
			return;
		}
		if(frm.contents.value == ""){
			window.alert("내용 입력");
			frm.contents.focus();
			return;
		}
		
	}
</script>
</head>
<body>
<h3 class="page_title">게시글등록</h3>
<div class="regist">
<form name="frm" method="post" id="frm" 
		action="memberInsertProc.jsp"
		onsubmit="return inputCheck()">
	<div>
		<label for="writer">writer</label>
		<input type="text" id="writer" name="writer">
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
		<label for="file">Select a file:</label>
  		<input type="file" id="file" name="file">
	</div>
	<div>
		<button type="reset">초기화</button>
		<button type="button" onclick="inputCheck()">등록</button>
	</div>

</form>
</div>
</body>
</html>