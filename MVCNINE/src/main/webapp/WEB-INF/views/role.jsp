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
	<h4>Role menu</h4>
	<form:form action="/adminPanel/role" method="post" modelAttribute="role">
		<form:hidden path="id"/>
		<table>
 			<tr>
 				<td><form:errors path="role"/></td>
 			</tr>
			<tr>
				<td><form:input path="role"/></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>Role name</th>
		</tr>
		<c:forEach items="${page.content}" var="role">
			<tr>
				<td>${role.role}</td>
				<td><a href="/adminPanel/role/delete/${role.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}">delete</a>
				</td>
				<td><a href="/adminPanel/role/update/${role.id}">update</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
				<c:if test="${!page.isFirst()}">
					<td><a href="?page=${page.number}&size=${page.size}&sort=${param['sort']}">Previous</a></td>
				</c:if>
				<c:if test="${!page.isLast()}">
					<td><a href="?page=${page.number+2}&size=${page.size}&sort=${param['sort']}">Next</a></td>
				</c:if>
			</tr>
			<tr>
				<td><a href="?page=1&size=1&sort=${param['sort']}">1</a></td>
				<td><a href="?page=1&size=5&sort=${param['sort']}">5</a></td>
				<td><a href="?page=1&size=10&sort=${param['sort']}">10</a></td>
				<td><a href="?page=1&size=20&sort=${param['sort']}">20</a></td>
			</tr>
			<tr>
				<td><a href="?page=1&size=${page.size}&sort=role">Brand asc</a></td>
				<td><a href="?page=1&size=${page.size}&sort=role,desc">Brand desc</a></td>
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