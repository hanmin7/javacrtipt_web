<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardClient.jsp</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	$(function(){
		//목록조회
		function boardList(){
			//$.ajax( url , {  })
			$.ajax( "../BoardSelectAllAjaxServ" , { 
					dataType : "json",
					success : function(datas){
						for(i=0; i<datas.length; i++) {
							$("<div>").append(datas[i].no)
									.append(datas[i].poster)
									.append(datas[i].subject)
									.data("no", datas[i].no)
									.append($("<button>").html("삭제").addClass("btnDel"))
									.appendTo($("#list"))
						}
					}
				})
		}
		//삭제버튼
		$("#list").on("click", ".btnDel", function(){
			no = $(this).parent().data("no");
			div = $(this).parent();
			$.ajax("../BoardDeleteAjaxServ", {
				//method : "get", //디폴트 겟. 생략가능
				dataType: "json",
				data : {no : no}, //"no="+no 이것보다 json구조가 더 편함 {no:no, name: ~}
				success: function(data){
					alert(data.no + "삭제완료");
					div.remove();
				}
			});
		});
		//저장버튼
		$("#btnSave").on("click", function(){
			$.ajax("../BoardInsertAjaxServ", {
				method : "post",
				dataType : "json",
				data : $("form").serialize(),
				success : function(data){
					$("<div>").append(data.no)
							.append(data.poster)
							.append(data.subject)
							.data("no", data.no)
							.append($("<button>").html("등록").addClass("btnSave"))
							.appendTo($("#list"))
				}
			});
		});

		
		boardList();
	});
</script>
</head>
<body>
	<!-- 목록 -->
	<div id="list"></div>
	<form>
		<input type="text" name="poster" placeholder="작성자" />
		<input type="text" name="subject" placeholder="제목" />
		<textarea rows="4" cols="50" name="contents" placeholder="내용" ></textarea>
		<button type="button" id="btnSave">등록</button>
	</form>
</body>
</html>