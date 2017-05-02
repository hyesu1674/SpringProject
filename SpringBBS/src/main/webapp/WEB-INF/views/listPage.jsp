<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div style="margin: 15% 15%">
		<!-- jstl: JSP Standard Tag Library -->
		<form id="pageForm" class="form-inline" style="margin-bottom: 5px ">
			<input type="hidden" name="page" value="${criteria.page}"> <input
				type="hidden" name="recordsPerPage"
				value="${criteria.recordsPerPage}">
			<div class="form-group">
				<label>SearchType:</label> <select name="searchType"
					class="form-control">
					<option value="n">
						<c:out value="${criteria.searchType == null ? 'selected':'' }" />
						-------------------------------
					</option>
					<option value="t">
						<c:out value="${criteria.searchType eq 't' ? 'selected':'' }" />
						Title
					</option>
					<option value="c">
						<c:out value="${criteria.searchType eq 'c' ? 'selected':'' }" />
						Content
					</option>
					<option value="w">
						<c:out value="${criteria.searchType eq 'w' ? 'selected':'' }" />
						Writer
					</option>
					<option value="tc">
						<c:out value="${criteria.searchType eq 'tc' ? 'selected':'' }" />
						Title OR Content
					</option>
					<option value="cw">
						<c:out value="${criteria.searchType eq 'cw' ? 'selected':'' }" />
						Content OR Writer
					</option>
					<option value="tcw">
						<c:out value="${criteria.searchType eq 'tcw' ? 'selected':'' }" />
						Title OR Content OR Writer
					</option>
				</select>
			</div>
			<span>&nbsp&nbsp&nbsp&nbsp&nbsp</span>
			<div class="form-group">
				<label>Keyword:</label> <input type="text" name="keyword"
					value="${criteria.keyword}" class="form-control">
			</div>
			<div class="form-group">
				<button id="searchBtn" class="btn btn-default">Search</button>
				<button id="newBtn" class="btn btn-default">Write</button>
			</div>
		</form>
		<table class="table table-hover">
			<tr>
				<th>NO</th>
				<th>TITLE</th>
				<th>WRITER</th>
				<th>REGDATE</th>
				<th>VIEW COUNT</th>
			</tr>

			<c:forEach items="${list}" var="board">
				<tr>
					<td>${board.bno}</td>
					<td><a href='${board.bno}' class="title">${board.title}</a></td>
					<td>${board.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
							value="${board.regdate}" /></td>
					<td>${board.viewcnt}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="container" style="margin: 5% 5%;">
			<ul class="pagination">
				<c:if test="${criteria.prev}">
					<li><a href="${criteia.startPage-1}">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${criteria.startPage}" end="${criteria.endPage}"
					var="idx">
					<li <c:out value="${idx == criteria.page? 'class=active':''}"/>>
						<a href="${idx}"> ${idx} </a>
					</li>

				</c:forEach>
				
				<c:if test="${criteria.next}">
					<li><a href="${criteia.endPage+1}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
         var pageForm = $("#pageForm");
		$(".pagination li a").on("click", function() { // on() 이벤트 핸들러를 등록함.
			event.preventDefault(); // event 자바스크립트 내장 객체 , 기본으로 설정된 이벤트 실행을 막아준다.

			var targetPage = $(this).attr("href") // 현재 이벤트가 발생한 a 태그
			pageForm.find("[name=page]").val(targetPage);

			pageForm.attr("action", "listPage");
			pageForm.attr("mehtod", "get");
			pageForm.submit();

		});

		$(".title").on(
				"click",
				function() {
					event.preventDefault();
					var bno = $(this).attr("href");
					pageForm.attr("action", "read");
					pageForm.attr("mehtod", "get");
					$("<input type='text' name='bno' value='"+bno+"'>")
							.appendTo(pageForm);

					pageForm.submit();

				});

		$("#searchBtn").on("click", function() {
			pageForm.attr("action", "listPage");
			pageForm.attr("method", "get");
			pageForm.submit();
		});

		$("#newBtn").on("click", function() {
			pageForm.attr("action", "create");
			pageForm.attr("method", "get");
			pageForm.submit();
		});
	</script>
</body>
</html>