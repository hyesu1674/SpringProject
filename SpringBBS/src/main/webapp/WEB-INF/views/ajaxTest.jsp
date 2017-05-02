<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script src="./resources/jquery-3.1.1.min.js"></script> -->
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<style>
	#modDiv {
		width : 300px;
		height : 100px;
		background-color : #83b14e;
		position : absolute;
		top : 50%;
		left : 50%;
		margin-top : -50px;
		margin-left : -150px;
		padding : 10px;
		z-index : 1000;
		box-shadow: 2px 2px 4px black;
		display: none;
	}
</style>
</head>
<body>
	<h2>Ajax Test Page</h2>
	
	<div>
		<div>
			<label>작성자 : </label>
			<input type="text" name="replyer" id="newReplier">
		</div>
		<div>
			<label>댓글 : </label>
			<input type="text" name="replyText" id="newReplyText">
		</div>
		<div>
			<button id="replyAddBtn">댓글 등록</button>
		</div>
	</div>
	
	<ul id="replies">
	</ul>
	
	<div id="modDiv">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replyText">
		</div>
		<div>
			<button id="replyModBtn">수정</button>
			<button id="replyDelBtn">삭제</button>
			<button id="closeBtn">닫기</button>
		</div>
	</div>
	
	<ul class="pagination">
	</ul>
	
	
	<script>
		var bno = 2;
		var currentPage = 1;
		getPageReplyList(1);
		
		function getAllReplies(){
			$.getJSON("replies/all/" + bno, function(data){
				console.log(data);
				var str = "";
				$(data).each(function(){
					str += "<li data-rno = '"+this.rno+"' class='replyLi'>" + this.rno + " : " + this.replyText + "<button>수정</button></li>";
				});
				$("#replies").html(str);
			});
		}
		
		// 댓글 수정창 버튼
		$("#replies").on("click", ".replyLi button", function(){
			var reply = $(this).parent();
			var rno = reply.attr("data-rno");
			var replyText = reply.text();
			
			$(".modal-title").html(rno);
			$("#replyText").val(replyText);
			$("#modDiv").show("slow");
		});
		
		// 댓글 추가 버튼
		$("#replyAddBtn").on("click", function(){
			var replyer = $("#newReplier").val();
			var replyText = $("#newReplyText").val();
			
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
						alert("등록되었습니다.");
						getPageReplyList(1);	// 새로운 글 등록 후 1페이지로 
					}
				}
			});
		});
		
		// 삭제버튼
		$("#replyDelBtn").on("click", function(){
			var ans = confirm("Are you sure to delete?");
			if(ans == false) return;
			var rno = $(".modal-title").html();
			
			$.ajax({
				type : "delete",
				url : "replies/"+rno,
				success : function(result){
					if(result == "SUCCESS")
						alert("삭제되었습니다.");
					$("#modDiv").hide("slow");
					getPageReplyList(currentPage);		// 삭제 후 현재페이지
				}
			});
		});
		
		// 닫기버튼
		$("#closeBtn").on("click", function(){
			$("#modDiv").hide("slow");
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
						alert("수정되었습니다.");
						$("#modDiv").hide("slow");
						// getAllReplies();
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
					str += "<li data-rno = '"+this.rno+"' class='replyLi'>" + this.rno + " : " + this.replyText + "<button>수정</button></li>";
				});
				$("#replies").html(str);
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
		
		//
		$(".pagination").on("click", "li a", function(){
			event.preventDefault();		// 이벤트 막기
			var replyPage = $(this).attr("href");
			getPageReplyList(replyPage);
		});
	</script>
</body>
</html>