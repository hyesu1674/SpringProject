<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<style type="text/css">
#modDiv {
	width: 200px;
	height: 100px;
	background-color: lightgray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 100;
	box-shadow: 2px 2px 3px black;
	display: none;
}
</style>
</head>
<body>
	<h2>Ajax Test Page</h2>
	<div>
		<div>
			<label>�ۼ��� : </label> <input type="text" name="replyer"
				id="newReplyer" />
		</div>

		<div>
			<label>��� : </label> <input type="text" name="replyText"
				id="newReplyText" />
		</div>

		<div>
			<button id="replyAddBtn">��� ���</button>
		</div>
	</div>
	<ul id="replies"></ul>

	<div id="modDiv">
		<div class="modal-title"></div>
		<div>
			<input type='text' id='replyText' />
		</div>
		<div>
			<button id="replyModBtn">����</button>
			<button id="replyDelBtn">����</button>
			<button id="closeBtn">�ݱ�</button>
		</div>
	</div>

	<ul class="pagination">

	</ul>

	<script>
		var bno = 1;
		// getAllReplies();
		getPageReplyList(1);
		
		var currentPage = 1;
		
		$(".pagination").on("click", "li a", function(){
			event.preventDefault();
			
			var replyPage = $(this).attr("href");
			getPageReplyList(replyPage);
		});
		
		function getPageReplyList(page){
			
			currentPage = page;
			
			$.getJSON("replies/"+bno+"/"+page, function(data){
				
				var str = "";
                console.log(data.length);

                $(data.list)
                        .each(
                                function() {
                                    str += "<li data-rno='"+this.rno+"' class='replyLi'>"
                                            + this.rno
                                            + "�� ��� : "
                                            + this.replyText
                                            + '<button>����</button>'
                                            + "</li>";
                                }); // �迭 ������ �� each�� ���

                $("#replies").html(str);
                printPaging(data.criteria);
				
				
			});
			
		}
		
		
		function printPaging(criteria){
			
			var str ="";
			
			if(criteria.prev){
				str += "<li><a href='"+(criteria.startPage-1)+"'>"+
			              "<<"+"</a></li>";
			}
			
			for(var i = criteria.startPage; i <= criteria.endPage; i++){
				
				var strClass = criteria.page == i ? "class='active'" : "";
				str += "<li"+ strClass +"><a href='"+i+"'>" + i + "</a></li>";
				
			}
			
			if(criteria.next){
				str += "<li><a href='"+(criteria.endPage+1)+"'>"+
                          ">>"+"</a></li>";
			}
			
			$(".pagination").html(str);
			
		}
		
		
		$("#replyModBtn").on("click", function(){
			var rno = $(".modal-title").html();
			var replyText = $("#replyText").val();
			
			$.ajax({
				type : "PUT",
				url : "replies/"+rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PUT"
				},
				dataType : "text",
				data : JSON.stringify({
					"replyText" : replyText
				}),
				success : function(result){
					if(result == "SUCCESS"){
						alert("�����Ǿ����ϴ�.");
						$("#modDiv").hide("slow");
						getPageReplyList(currentPage);
					}
				}
				
			});
		});
		
		$("#replyDelBtn").on("click", function(){
			var ans = confirm("Are you sure to delete?");
			
			if(ans == false) return;
			
			var rno = $(".modal-title").html();
			
			$.ajax({
				type: "delete",
				url:"replies/"+rno,
				success: function(result){
					if(result == "SUCCESS" )
						alert("�����Ǿ����ϴ�.");
					$("#modDiv").hide("slow");
					getPageReplyList(currentPage);
			
				}
			});
		});
		
		$("#closeBtn").on("click", function(){
			$("#modDiv").hide("slow");
		});

		$("#replies").on("click", ".replyLi button", function(){
			
			  var li = $(this).parent();
			  var rno = li.attr("data-rno");
			  var replyText = li.text();
			  
			  alert(rno+" : "+ replyText);
			  
			  $(".modal-title").html(rno);
			  $("#replyText").val(replyText);
			  $("#modDiv").show("slow");
			  
	
			
		}); // ��ư�� �������� �ʱ� ������ �ڿ� ������. (��ư ������) 
		
		

		
		function getAllReplies() {
			$
					.getJSON(
							"replies/all/" + bno,
							function(data) {

								var str = "";
								console.log(data.length);

								$(data)
										.each(
												function() {
													str += "<li data-rno='"+this.rno+"' class='replyLi'>"
															+ this.rno
															+ "�� ��� : "
															+ this.replyText
															+ '<button>����</button>'
															+ "</li>";
												}); // �迭 ������ �� each�� ���

								$("#replies").html(str);

							}); // �ݹ��Լ� ���
		}

		$("#replyAddBtn").on("click", function() {
			var replyer = $("#newReplyer").val();
			var replyText = $("#newReplyText").val();

			$.ajax({
				type : "POST",
				url : "replies",
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					replyText : replyText
				}),
				success : function(result, status) {
					if (result == 'SUCCESS') {
						getPageReplyList(1);
					}
				}
			});
		});
	</script>
</body>
</html>