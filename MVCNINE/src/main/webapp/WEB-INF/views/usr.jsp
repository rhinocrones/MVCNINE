<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
</head>
<body>
	<h4>User menu</h4>
	<form action="/adminPanel/usr" method="post">
		<table>
			<tr>
				<td><select name="role">
						<c:forEach items="${roles}" var="role">
							<option value="${role.role}">${role.role}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input name="username" placeholder="User name"></td>
			</tr>
			<tr>
				<td><input name="email" placeholder="Email"></td>
			</tr>
						<tr>
				<td><input name="password" placeholder="Password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit new user"></td>
			</tr>
		</table>
	</form>
	<table>
		<tr>
			<th>User name</th>
		</tr>
		<c:forEach items="${usrs}" var="usr">
			<tr>
				<td>${usr.username}</td>
				<td>${usr.role.role}</td>
				<td>${usr.email}</td>
				<td>${usr.password}</td>
				<td><a href="/adminPanel/usr/delete/${usr.id}">delete</a></td>
				<td><a href="/adminPanel/usr/update/${usr.id}">update</a></td>
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