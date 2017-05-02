<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<style>
	.table { width: 600px;}
</style>
<body>
	<!-- jstl : JSP Standard Tag Library -->
	<form id="pageForm" class="form-inline" style="margin:1% 1%">
		<!-- 숨겨진 input 태그 -->
		<input type="hidden" name="page" value="${criteria.page}">	<!-- 현재 페이지 -->
		<input type="hidden" name="recordsPerPage" value="${criteria.recordsPerPage}">	<!-- 한번에 보여질 목록 수 -->
		<div class="form-group">
			<label>Search Type : </label>
			<select name="searchType" class="form-control">
				<option value="n" <c:out value="${criteria.searchType == null ? 'selected':''}"/>>
				---</option>
				<option value="t" <c:out value="${criteria.searchType eq 't' ? 'selected':''}"/>>
				Title</option>
				<option value="c" <c:out value="${criteria.searchType eq 'c' ? 'selected':''}"/>>
				Content</option>
				<option value="w" <c:out value="${criteria.searchType eq 'w' ? 'selected':''}"/>>
				Writer</option>
				<option value="tc" <c:out value="${criteria.searchType eq 'tc' ? 'selected':''}"/>>
				Title OR Content</option>
				<option value="cw" <c:out value="${criteria.searchType eq 'cw' ? 'selected':''}"/>>
				Content OR Writer</option>
				<option value="tcw" <c:out value="${criteria.searchType eq 'tcw' ? 'selected':''}"/>>
				Title OR Content OR Writer</option>
			</select>
		</div>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<div class="form-group">
			<label>Keyword : </label>
			<input type="text" name="keyword" value="${criteria.keyword}" class="form-control">
		</div>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<div class="form-group">
			<button id="searchBtn" class="btn btn-default">Search</button>
			<button id="newBtn" class="btn btn-default">New Board</button>
		</div>
	</form>
	
	
	<table class="table">
		<tr>
			<th>NO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th>VIEW COUNT</th>
		</tr>
		<!-- 목록 -->
		<c:forEach items="${list}" var="board">
			<tr>
				<td>${board.bno}</td>
				<td><a href="${board.bno}" class="title">${board.title}</a></td>
				<td>${board.writer}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}" /></td>
				<td>${board.viewcnt}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="container">
		<ul class="pagination">
			<c:if test="${criteria.prev}">
				<li><a href="${criteria.startPage-1}">&laquo;</a></li>
			</c:if>
			
			<c:forEach begin="${criteria.startPage}" end="${criteria.endPage}" var="idx">
				<!-- idx와 현재 페이지가 같다면 class를 줌 -->
				<li <c:out value="${idx==criteria.page ? 'class=active' : ''}"/>>
					<a href="${idx}">${idx}</a>
				</li>
			</c:forEach>
			
			<c:if test="${criteria.next}">
				<li><a href="${criteria.endPage+1}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
	
	

<script>
	$(".pagination li a").on("click", function(event){
		event.preventDefault();										// 실제 화면 이동을 막음
		
		var pageForm = $("#pageForm");								// pageForm의 제이쿼리 객체 저장
		var targetPage = $(this).attr("href");						// 이동할 페이지를 가져옴 
		pageForm.find("[name='page']").val(targetPage);				// 이동할 페이지를 저장
		pageForm.attr("action", "listPage").attr("method", "get");	// get방식으로 listPage로	
		pageForm.submit();											// 보냄
	});
	
	$(".title").on("click", function(event){
		event.preventDefault();
		
		var pageForm = $("#pageForm");
		var bno = $(this).attr("href");
		pageForm.attr("action", "read").attr("method", "get");
		pageForm.append("<input type='hidden' name='bno' value='"+ bno +"'>");
		pageForm.submit();
	});
	
	$("#searchBtn").on("click", function(){
		var pageForm = $("#pageForm");
		pageForm.attr("action", "listPage");
		pageForm.attr("method", "get");
		pageForm.submit();
	});
	
	$("#newBtn").on("click", function(){
		var pageForm = $("#pageForm");
		pageForm.attr("action", "create");
		pageForm.attr("method", "get");
		pageForm.submit();
	});
</script>
</body>
</html>