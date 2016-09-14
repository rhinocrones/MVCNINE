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
	<h4>Size menu</h4>
	<form:form action="/adminPanel/size" method="post"
		modelAttribute="form">
		<form:hidden path="id" />
		<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'size' and parameter.key ne 'id'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
		</c:forEach>
		<table>
			<tr>
				<td><form:errors path="size" /></td>
			</tr>
			<tr>
				<td><form:input path="size" /></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<form:form action="/adminPanel/size" method="get"
		modelAttribute="filter">
		<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'search'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
		</c:forEach>
		<table>
			<tr>
				<td><form:input path="search" placeholder="search" /><input
					type="submit" value="ok"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>Size name</th>
		</tr>
		<c:forEach items="${page.content}" var="size">
			<tr>
				<td>${size.size}</td>
				<td><a
					href="/adminPanel/size/delete/${size.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a>
				</td>
				<td><a
					href="/adminPanel/size/update/${size.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td><a
				href="?page=1&size=1&sort=${param['sort']}&search=${param['search']}">1</a></td>
			<td><a
				href="?page=1&size=5&sort=${param['sort']}&search=${param['search']}">5</a></td>
			<td><a
				href="?page=1&size=10&sort=${param['sort']}&search=${param['search']}">10</a></td>
			<td><a
				href="?page=1&size=20&sort=${param['sort']}&search=${param['search']}">20</a></td>
		</tr>
		<tr>
			<td><a
				href="?page=1&size=${page.size}&sort=size&search=${param['search']}">Role
					asc</a></td>
			<td><a
				href="?page=1&size=${page.size}&sort=size,desc&search=${param['search']}">Role
					desc</a></td>
		</tr>
	</table>
	<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
	<hr>
	<a href="/adminPanel">Back to admin panel</a>
	<hr>
	<p>
		<a href="/">Back to the index</a>
	</p>
</body>
</html>