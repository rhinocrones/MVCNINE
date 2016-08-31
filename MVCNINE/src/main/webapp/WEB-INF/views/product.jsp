<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h4>Product menu</h4>
	<form action="/adminPanel/product" method="post">
		<table>
			<tr>
				<td><select name="category">
						<c:forEach items="${categories}" var="category">
							<option value="${category.category}">${category.category}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><select name="brand">
						<c:forEach items="${brands}" var="brand">
							<option value="${brand.brand}">${brand.brand}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><select name="size">
						<c:forEach items="${sizes}" var="size">
							<option value="${size.size}">${size.size}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input name="name" placeholder="Product name"></td>
			</tr>
			<tr>
				<td><input name="price" placeholder="0.00"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit new product"></td>
			</tr>
		</table>
	</form>
	<table>
		<tr>
			<th>Product name</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.category.category}</td>
				<td>${product.brand.brand}</td>
				<td>${product.size.size}</td>
				<td><a href="/adminPanel/product/delete/${product.id}">delete</a>
				</td>
				<td><a href="/adminPanel/product/update/${product.id}">update</a>
				</td>
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