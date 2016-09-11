<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
		<c:forEach items="${page.content}" var="usr">
			<tr>
				<td>${usr.username}</td>
				<td>${usr.role.role}</td>
				<td>${usr.email}</td>
				<td>${usr.password}</td>
				<td><a href="/adminPanel/usr/delete/${usr.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}">delete</a>
				<td><a href="/adminPanel/usr/update/${usr.id}">update</a>
			</tr>
		</c:forEach>
		<tr>
			<c:if test="${!page.isFirst()}">
				<td><a
					href="?page=${page.number}&size=${page.size}&sort=${param['sort']}">Previous</a></td>
			</c:if>
			<c:if test="${!page.isLast()}">
				<td><a
					href="?page=${page.number+2}&size=${page.size}&sort=${param['sort']}">Next</a></td>
			</c:if>
		</tr>
		<tr>
			<td><a href="?page=1&size=1&sort=${param['sort']}">1</a></td>
			<td><a href="?page=1&size=5&sort=${param['sort']}">5</a></td>
			<td><a href="?page=1&size=10&sort=${param['sort']}">10</a></td>
			<td><a href="?page=1&size=20&sort=${param['sort']}">20</a></td>
		</tr>
		<tr>
			<td><a href="?page=1&size=${page.size}&sort=username">Username
					asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=username,desc">Username
					desc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=role.role">Role
					asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=role.role,desc">Role
					desc</a></td>
		</tr>
	</table>
	<table>
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
	</table>
	<hr>
	<a href="/adminPanel">Back to admin panel</a>
	<hr>
	<p>
		<a href="/">Back to the index</a>
	</p>
</body>
</html>