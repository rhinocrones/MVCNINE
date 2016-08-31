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
	<h4>Category menu</h4>
	<form:form action="/adminPanel/category" method="post" modelAttribute="category">
		<form:hidden path="id"/>
		<table>
 			<tr>
 				<td><form:errors path="category"/></td>
 			</tr>
			<tr>
				<td><form:input path="category"/></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>Category name</th>
		</tr>
		<c:forEach items="${categories}" var="category">
			<tr>
				<td>${category.category}</td>
				<td><a href="/adminPanel/category/delete/${category.id}">delete</a>
				</td>
				<td><a href="/adminPanel/category/update/${category.id}">update</a>
				</td>
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