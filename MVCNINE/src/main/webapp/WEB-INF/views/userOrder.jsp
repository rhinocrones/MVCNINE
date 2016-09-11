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
	<h4>User order menu</h4>
	<form:form action="/adminPanel/userOrder" method="post"
		modelAttribute="userOrder">
		<form:hidden path="id" />
		<table>
			<tr>
				<td><form:select path="product">
						<c:forEach items="${products}" var="product">
							<c:choose>
								<c:when test="${product.id eq userOrder.product.id}">
									<option value="${product.id}" selected="selected">${product.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${product.id}">${product.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:select path="usr">
						<c:forEach items="${usrs}" var="usr">
							<c:choose>
								<c:when test="${usr.id eq userOrder.usr.id}">
									<option value="${usr.id}" selected="selected">${usr.username}</option>
								</c:when>
								<c:otherwise>
									<option value="${usr.id}">${usr.username}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>User order</th>
		</tr>
		<c:forEach items="${page.content}" var="userOrder">
			<tr>
				<td>${userOrder.id}</td>
				<td>${userOrder.usr.username}</td>
				<td>${userOrder.product.name}</td>
				<td>${userOrder.localDateTime}</td>
				<td><a
					href="/adminPanel/userOrder/delete/${userOrder.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}">delete</a></td>
				<td><a href="/adminPanel/userOrder/update/${userOrder.id}">update</a></td>
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
			<td><a href="?page=1&size=${page.size}&sort=usr.username">Username
					asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=usr.username,desc">Username
					desc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=product.name">Product
					name asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=product.name,desc">Product
					name desc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=localDateTime">Time
					 asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=localDateTime,desc">Time
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