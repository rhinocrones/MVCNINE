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
				<td>
					<form action="/userPanel/userProduct" method="post">
						<table>
							<tr>
								<td><input type="submit" value="Submit"></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="/userPanel">Back to user panel</a>
	<hr>
	<p>
		<a href="/">Back to the index</a>
	</p>
</body>
</html>