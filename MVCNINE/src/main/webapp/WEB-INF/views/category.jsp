<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category</title>
</head>
<body>
	<h4>Category menu</h4>
	<form action="/adminPanel/category" method="post">
		<table>
			<tr>
				<td><input name="category" placeholder="New category"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
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