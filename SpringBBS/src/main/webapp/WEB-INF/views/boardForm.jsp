<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<form method="post" class="form-inline" style="margin: 10% 10%">
		<!-- action="create"를 지정해주지 않으면 submit을 눌렀을 때 자동으로 create로 간다. -->
		<div class="form-group">
		      작성자 : <input type="text" name="writer" class="form-control" /> <br>
		</div>
		<div class="form-group">
		      제목 : <input type="text" name="title" class="form-control"/> <br>
		</div>
		<div class="form-group">
		      내용 : <textarea name="content" class="form-control"></textarea> <br>
		</div>
		
		<input type="submit" value="제출" class="btn btn-default"/>
		<button id="list" class="btn btn-default">List</button>
	</form>
	<script>
	   $("#list").on("click", function(){
		   $("#form").attr("action", "listPage");
		   $("#form").attr("method", "get");
		   $("#form").submit();
	   });
	</script>
</body>
</html>