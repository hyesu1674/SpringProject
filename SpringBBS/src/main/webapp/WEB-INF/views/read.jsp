<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>	
<style>
		.demo-list-item {
		  width: 300px;
		}
</style>
<script>
$(document).ready(function(){
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$(".modify").on("click", function(){
		
		formObj.append($("#title"));
        formObj.append($("#content"));
        formObj.append($("#writer"))
		formObj.attr("action", "modify");
		formObj.submit();
	});
	
	$(".remove").on("click", function(){
		formObj.attr("action", "remove");
		formObj.submit();
	});
	
	$(".listAll").on("click", function(){
		
		formObj.attr("action", "listPage");
		formObj.attr("method", "get");
		formObj.submit();
		
		
		// self.location = "listpage?page=${criteria.page}&recordsPerPage=${criteria.recordsPerPage}"; 
		/* formObj.attr("action", "listAll");
		formObj.attr("method", "get");
		formObj.submit(); */
	});
});
</script>
</head>
<body>
	<form role="form" method="post" action="modify">
		<input type="hidden" name="bno" value="${board.bno}">
		<input type="hidden" name="page" value="${criteria.page}">
		<input type="hidden" name="recordsPerPage" value="${criteria.recordsPerPage}">
		<input type="hidden" name="searchType" value="${criteria.searchType }" />
	</form>
	<ul class="demo-list-item mdl-list">
  		<li class="mdl-list__item">
    		<span class="mdl-list__item-primary-content">
				<label>Title : </label>
				<!-- board객체의 getTitle()메소드에 접근하는것 -->
				<input type="text" id="title" value="${board.title}" >	
			</span>
  		</li>
  		<li class="mdl-list__item">
		    <span class="mdl-list__item-primary-content">
				<label>Content : </label>
				<textarea id="content" rows="5" >${board.content}</textarea>	
			</span>
  		</li>
  		<li class="mdl-list__item">
    		<span class="mdl-list__item-primary-content">
				<label>Writer : </label>	
				<input type="text" id="writer" value="${board.writer}" >
	  		</span>
  		</li>
	</ul>
	
	
	<div>
		<button type="submit" class="modify mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Modify</button>
		<button type="submit" class="remove mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Remove</button>
		<button type="submit" class="listAll mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">List</button>
	</div>
</body>
</html>