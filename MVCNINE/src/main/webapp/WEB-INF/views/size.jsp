<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Size</title>
</head>
<body>
	<h4>Size menu</h4>
	<form action="/adminPanel/size" method="post">
		<table>
			<tr>
				<td><input name="size" placeholder="New size"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
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