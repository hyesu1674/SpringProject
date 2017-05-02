<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<form method="post" style="margin: 10% 10%">		
		<input type='hidden' name='page' value="${criteria.page}">
		<input type='hidden' name='recordsPerPage' value="${criteria.recordsPerPage}">
		<input type='hidden' name='searchType' value="${criteria.searchType}">
		<input type='hidden' name='keyword' value="${criteria.keyword}">
		<div class="form-group">
			제목 : <input type="text" name="title">	<br>
		</div>
		<div class="form-group">
			내용 : <textarea name="content"></textarea> <br>
		</div>
		<div class="form-group">
			작성자 : <input type="text" name="writer"> <br>
		</div>
		<input class="btn btn-default" type="submit">
		<button class="btn btn-default" id="list">List</button>
	</form>
	<script>
		$("#list").on("click", function(){
			$("form").attr("action", "listPage");
			$("form").attr("method", "get");
			$("form").submit();
		});
	</script>
</body>
</html>