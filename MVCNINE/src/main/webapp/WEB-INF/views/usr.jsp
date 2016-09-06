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
	<h4>User menu</h4>
	<form:form action="/adminPanel/usr" method="post" modelAttribute="usr">
		<form:hidden path="id" />
		<table>
			<tr>
				<td><form:select path="role">
						<c:forEach items="${roles}" var="role">
							<c:choose>
								<c:when test="${role.id eq usr.role.id}">
									<option value="${role.id}" selected="selected">${role.role}</option>
								</c:when>
								<c:otherwise>
									<option value="${role.id}">${role.role}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:errors path="username" /></td>
			</tr>
			<tr>
				<td><form:input path="username" placeholder="Enter username" /></td>
			</tr>
			<tr>
				<td><form:errors path="email" /></td>
			</tr>
			<tr>
				<td><form:input path="email" placeholder="Enter email" /></td>
			</tr>
			<tr>
				<td><form:errors path="password" /></td>
			</tr>
			<tr>
				<td><form:input path="password" placeholder="Enter password" /></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
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