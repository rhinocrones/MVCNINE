<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Role</title>
</head>
<body>
	<h4>Role menu</h4>
	<form action="/adminPanel/role" method="post">
		<table>
			<tr>
				<td><input name="role" placeholder="New role"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
	<table>
		<tr>
			<th>Role name</th>
		</tr>
		<c:forEach items="${roles}" var="role">
			<tr>
				<td>${role.role}</td>
				<td><a href="/adminPanel/role/delete/${role.id}">delete</a></td>
				<td><a href="/adminPanel/role/update/${role.id}">update</a></td>
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