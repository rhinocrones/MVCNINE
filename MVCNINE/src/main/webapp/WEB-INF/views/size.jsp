<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h4>Size menu</h4>
	<form:form action="/adminPanel/size" method="post" modelAttribute="form">
		<form:hidden path="id"/>
		<table>
 			<tr>
 				<td><form:errors path="size"/></td>
 			</tr>
			<tr>
				<td><form:input path="size"/></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>Size name</th>
		</tr>
		<c:forEach items="${sizes}" var="size">
			<tr>
				<td>${size.size}</td>
				<td><a href="/adminPanel/size/delete/${size.id}">delete</a></td>
				<td><a href="/adminPanel/size/update/${size.id}">update</a></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="/adminPanel">Back to admin panel</a>
	<hr>
	<p>
		<a href="/">Back to the index</a>
	</p>
</body>
</html>