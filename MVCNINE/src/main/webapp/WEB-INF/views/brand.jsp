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
	<h4>Brand menu</h4>
	<form:form action="/adminPanel/brand" method="post" modelAttribute="brand">
		<form:hidden path="id"/>
		<table>
 			<tr>
 				<td><form:errors path="brand"/></td>
 			</tr>
			<tr>
				<td><form:input path="brand"/></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>Brand name</th>
		</tr>
		<c:forEach items="${brands}" var="brand">
			<tr>
				<td>${brand.brand}</td>
				<td><a href="/adminPanel/brand/delete/${brand.id}">delete</a></td>
				<td><a href="/adminPanel/brand/update/${brand.id}">update</a></td>
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