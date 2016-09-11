<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h4>Product menu</h4>
	<form:form action="/adminPanel/product" method="post"
		modelAttribute="form">
		<form:hidden path="id" />
		<table>
			<tr>
				<td><form:select path="category">
						<c:forEach items="${categories}" var="category">
							<c:choose>
								<c:when test="${category.id eq form.category.id}">
									<option value="${category.id}" selected="selected">${category.category}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.id}">${category.category}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:select path="brand">
						<c:forEach items="${brands}" var="brand">
							<c:choose>
								<c:when test="${brand.id eq form.brand.id}">
									<option value="${brand.id}" selected="selected">${brand.brand}</option>
								</c:when>
								<c:otherwise>
									<option value="${brand.id}">${brand.brand}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:select path="size">
						<c:forEach items="${sizes}" var="size">
							<c:choose>
								<c:when test="${size.id eq form.size.id}">
									<option value="${size.id}" selected="selected">${size.size}</option>
								</c:when>
								<c:otherwise>
									<option value="${size.id}">${size.size}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:errors path="name" /></td>
			</tr>
			<tr>
				<td><form:input path="name" placeholder="Product name" /></td>
			</tr>
			<tr>
				<td><form:errors path="price" /></td>
			</tr>
			<tr>
				<td><form:input path="price" placeholder="000.00" /></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>Product name</th>
		</tr>
		<c:forEach items="${page.content}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.category.category}</td>
				<td>${product.brand.brand}</td>
				<td>${product.size.size}</td>
				<td><a
					href="/adminPanel/product/delete/${product.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}">delete</a>
				</td>
				<td><a href="/adminPanel/product/update/${product.id}">update</a>
				</td>
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
			<td><a href="?page=1&size=${page.size}&sort=name">Product
					name asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=name,desc">Product
					name desc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=price">Product
					price asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=price,desc">Product
					price desc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=category.category">Product
					category asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=category.category,desc">Product
					category desc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=brand.brand">Product
					brand asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=brand.brand,desc">Product
					brand desc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=size.size">Product
					size asc</a></td>
			<td><a href="?page=1&size=${page.size}&sort=size.size,desc">Product
					size desc</a></td>
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