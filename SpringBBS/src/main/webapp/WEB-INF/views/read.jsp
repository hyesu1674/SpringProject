<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
		#replyContainer {
			position: relative;
		}
		#modDiv {
			position : absolute;
			top : 20%;
			left : 30%;
			z-index : 1000;
			display: none;
		}
		.reply {
			display: inline-block;
			float: left;
		}
		.replyNum { width: 3em; }
		.replyWriter, .date { width: 10em; }
		.replyText { width: 30em; height: 3em; overflow: auto; margin-right: 10px; }
		.replyLi { margin-bottom: 2em; list-style-type: none; clear: both; }
		.box-footer { margin:10px 0px 10px; }
</style>
<script>
$(document).ready(function(){
	var formObj = $("form[role='form']");
	console.log(formObj);
	$(".modify").on("click", function(){
		formObj.attr("action", "modify");
		formObj.append($("#writer"));
		formObj.append($("#content"));
		formObj.append($("#title"));
		formObj.submit();
	});
	
	$(".remove").on("click", function(){
		formObj.attr("action", "remove");
		formObj.submit();
	});
	
	$(".list").on("click", function(){
		formObj.attr("action", "listPage");
		formObj.attr("method", "get");
		formObj.submit();
	});
});
</script>

</head>
<body>
<form role="form" action="modify" method="post">
	<input type='hidden' name='bno' value="${board.bno}">
	<input type='hidden' name='page' value="${criteria.page}">
	<input type='hidden' name='recordsPerPage' value="${criteria.recordsPerPage}">
	<input type='hidden' name='searchType' value="${criteria.searchType}">
	<input type='hidden' name='keyword' value="${criteria.keyword}">
</form>

<div style="margin : 10% 15%">
	<form role="form" method="post">
		<input type="hidden" name="bno" value="${board.bno}">
	</form>
   		<div class="form-group">
    		<label>Title : </label>
				<!-- board객체의 getTitle()메소드에 접근하는것 -->
			<input type="text" id="title" name="title" value="${board.title}" class="form-control">	
   		</div>
 			<div class="form-group">
			<label>Content : </label>
			<textarea id="content" name="content" rows="5"  class="form-control">${board.content}</textarea>	
		</div>
		<div class="form-group">
			<label>Writer : </label>	
			<input type="text" id="writer" name="writer" value="${board.writer}" class="form-control">
		</div>
	
		<div>
			<button type="submit" class="modify btn btn-default">Modify</button>
			<button type="submit" class="remove btn btn-default">Remove</button>
			<button type="submit" class="list btn btn-default">List</button>
		</div>
	
	<!-- 댓글 등록창 -->
	<div class="box box-success">
		<div class="box-header">
			<h3 class="box-title">ADD NEW REPLY</h3>
		</div>
	
		<div class="box-body">
			<label for="newReplyWriter">작성자 : </label>
			<input type="text" name="replyer" id="newReplyWriter"><br>
			<label for="newReplyText">댓글 : </label>
			<input type="text" name="replyText" id="newReplyText">
		</div>
	
		<div class="box-footer">
			<button id="replyAddBtn" class="btn btn-primary">ADD REPLY</button>
		</div>
	</div>
	
	<!-- 댓글 목록 -->
	<div id="replyContainer">
		<ul class="timeline">
			<li class="time-label"><span class="bg-green">댓글 목록</span></li>
		</ul>
		
		<!-- 댓글 수정창 -->
		<div id="modDiv">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body" data-rno>
						<p><input type="text" id="replyText" class="form-control"></p>
					</div>
					<div class="modal-footer">
						<button id="replyModBtn" class="btn btn-info">Modify</button>
						<button id="replyDelBtn" class="btn btn-danger">Delete</button>
						<button id="closeBtn" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="text-center">
			<ul class="pagination pagination-sm no-margin"></ul>
		</div>
	
	</div>
	
</div>

<script>
	var bno = ${board.bno};
	var currentPage = 1;
	getPageReplyList(1);
	
	// 댓글 추가 버튼
	$("#replyAddBtn").on("click", function(){
		var replyer = $("#newReplyWriter").val();
		var replyText = $("#newReplyText").val();
		
		// 빈칸으로 댓글 입력 못하게 막기
		if(replyer.trim()==""){
			alert("작성자를 입력하세요"); return;
		}
		if(replyText.trim()==""){
			alert("댓글을 입력하세요"); return;
		}
		
		$.ajax({
			type : "post",
			url : "replies",
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : "text",
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replyText : replyText
			}),
			success : function(result){
				if(result == 'SUCCESS'){
					getPageReplyList(1);	// 새로운 글 등록 후 1페이지로 
				}
			}
		});
	});
	
	// 댓글 수정창 버튼
	$(".timeline").on("click", ".replyLi button", function(){
		var reply = $(this).parent();
		var rno = reply.attr("data-rno");
		var replyText = reply.find(".replyText").text();	// 선택한 댓글 내용만 가져오기
		
		$(".modal-title").html(rno);
		$("#replyText").val(replyText);
		$("#modDiv").show("slow");
	});
	
	// 댓글 수정 처리
	$("#replyModBtn").on("click", function(){
		var rno = $(".modal-title").html();
		var replyText = $("#replyText").val();
		
		$.ajax({
			type : "put",
			url : "replies/" + rno,	// 경로 앞에 / 있으면 안됨
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({replyText : replyText}),
			dataType : "text",
			success : function(result){
				console.log("result : " + result);
				if(result == "SUCCESS"){
					$("#modDiv").hide("slow");
					getPageReplyList(currentPage);		// 수정 후 현재 페이지
				}
			}
		});
	});
	
	// 댓글 페이지를 위한 ul 처리
	function getPageReplyList(page) {
		currentPage = page;
		$.getJSON("replies/" + bno + "/" + page, function(data){
			var str = "";
			$(data.list).each(function(){
				str += "<li data-rno = '"+this.rno+"' class='replyLi'>"+
							"<span class='reply replyNum'>" + this.rno + " : </span>"+
							"<span class='reply replyText'>" + this.replyText + "</span>"+
							"&nbsp;&nbsp;<span class='reply replyWriter'> 작성자 : "+ this.replyer +"</span>"+
							"<span class='reply date'> 수정일 : "+this.updateDate + "</span>"+
							"<button class='btn btn-default'>수정</button></li>";
			});
			$(".timeline").html(str);
			printPaging(data.criteria);
		});
	}
	
	function printPaging(criteria){
		var str = "";
		if(criteria.prev){
			str += "<li><a href='" + (criteria.startPage - 1) + "'>&laquo;</a></li>";
		}
		for(var i = criteria.startPage; i <= criteria.endPage; i++) {
			var strClass = criteria.page == i ? 'class=active' : '';
			str += "<li><a href='" + i + "'>" + i + "</a></li>";
		}
		if(criteria.next){
			str += "<li><a href='" + (criteria.endPage + 1) + "'>&raquo;</a></li>";
		}
		$(".pagination").html(str);
	}
	
	$(".pagination").on("click", "li a", function(){
		event.preventDefault();		// 이벤트 막기
		var replyPage = $(this).attr("href");
		getPageReplyList(replyPage);
	});
	
	// 삭제버튼
	$("#replyDelBtn").on("click", function(){
		var rno = $(".modal-title").html();
		
		$.ajax({
			type : "delete",
			url : "replies/"+rno,
			success : function(result){
				if(result == "SUCCESS"){
					$("#modDiv").hide("slow");
					getPageReplyList(currentPage);		// 삭제 후 현재페이지
				}
			}
		});
	});
	
	// 닫기버튼
	$("#closeBtn").on("click", function(){
		$("#modDiv").hide("slow");
	});
</script>

</body>
</html>