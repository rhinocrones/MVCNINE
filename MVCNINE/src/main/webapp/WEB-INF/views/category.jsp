<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/adminPanel/category">Category</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/adminPanel/size">Size</a></li>
					<li><a href="/adminPanel/brand">Brand</a></li>
					<li><a href="/adminPanel/role">Role</a></li>
					<li><a href="/adminPanel/usr">User</a></li>
					<li><a href="/adminPanel/product">Product</a></li>
					<li><a href="/adminPanel/userOrder">User Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row-fluid">
	<div class="col-md-3 col-xs-12">
		<div class="col-md-12 col-xs-12">
			<form:form action="/adminPanel/category" class="form-inline"
				method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search" />
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
		</div>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="col-md-12 col-xs-12">
			<form:form action="/adminPanel/category" method="post"
				class="form-inline" modelAttribute="category">
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="category, id" />
				<div class="form-group">
					<form:input id="category" path="category"
						placeholder="Category name" class="form-control" />
					<label for="category"><form:errors path="category" /></label>
					<button type="submit" class="btn btn-primary">Create
						category</button>
				</div>
			</form:form>
		</div>
		<div class="col-md-4 col-xs-4">
			<h4>Category name</h4>
		</div>
		<div class="col-md-4 col-xs-4">
			<h4>Delete</h4>
		</div>
		<div class="col-md-4 col-xs-4">
			<h4>Update</h4>
		</div>
		<c:forEach items="${page.content}" var="category">
			<div class="col-md-4 col-xs-4">${category.category}</div>
			<div class="col-md-4 col-xs-4">
				<a
					href="/adminPanel/category/delete/${category.id}<custom:allParams/>">delete</a>
			</div>
			<div class="col-md-4 col-xs-4">
				<a
					href="/adminPanel/category/update/${category.id}<custom:allParams/>">update</a>
			</div>
		</c:forEach>
		<div class="col-md-12 col-xs-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
		</div>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="col-md-6 col-xs-6 text-center">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Sort <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<custom:sort innerHtml="Category asc" paramValue="category" />
					<custom:sort innerHtml="Category desc" paramValue="category,desc" />
				</ul>
			</div>
		</div>
		<div class="col-md-6 col-xs-6 text-center">
			<custom:size posibleSizes="1,2,5,10" size="${page.size}"
				title="Page size" />
		</div>
	</div>
</div>