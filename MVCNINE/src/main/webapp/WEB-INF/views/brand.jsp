<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Brand</title>
</head>
<body>
	<h4>Brand menu</h4>
	<form action="/adminPanel/brand" method="post">
		<table>
			<tr>
				<td><input name="brand" placeholder="New brand"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
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