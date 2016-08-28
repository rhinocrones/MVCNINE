<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User order</title>
</head>
<body>
	<h4>User order menu</h4>
	<form action="/adminPanel/userOrder" method="post">
		<table>
			<tr>
				<td><select name="username">
						<c:forEach items="${usrs}" var="usr">
							<option value="${usr.username}">${usr.username}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><select name="name">
						<c:forEach items="${products}" var="product">
							<option value="${product.name}">${product.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit new user order"></td>
			</tr>
		</table>
	</form>
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