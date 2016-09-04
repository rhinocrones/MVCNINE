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
		<c:forEach items="${userOrders}" var="userOrder">
			<tr>
				<td>${userOrder.id}</td>
				<td>${userOrder.usr.username}</td>
				<td>${userOrder.product.name}</td>
				<td>${userOrder.localDateTime}</td>
				<td><a href="/adminPanel/userOrder/delete/${userOrder.id}">delete</a></td>
				<td><a href="/adminPanel/userOrder/update/${userOrder.id}">update</a></td>
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