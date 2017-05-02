<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>error!!!</h1>
	<h2>${exception.message}</h2>
	<c:forEach items="${exception.stackTrace}" var="stack">
		<h5>${stack.toString()}</h5>
	</c:forEach>
</body>
</html>