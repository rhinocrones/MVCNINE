<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/resources/css/ingredientAmount.css">
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav">
					<li><a href="/adminPanel/category">Category</a></li>
					<li><a href="/adminPanel/size">Size</a></li>
					<li><a href="/adminPanel/brand">Brand</a></li>
					<li><a href="/adminPanel/role">Role</a></li>
					<li class="active"><a href="/adminPanel/usr">User</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/adminPanel/product">Product</a></li>
					<li><a href="/adminPanel/userOrder">User Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row-fluid">
		<div class="col-md-3 col-xs-12">
		<form:form action="/adminPanel/usr" class="form-inline" method="get"
			modelAttribute="filter">
			<custom:hiddenInputs excludeParams="roleIds, _roleIds" />
			<div class="form-group">
				<h4>Role</h4>
			</div>
			<div class="form-group">
				<form:checkboxes items="${roles}" path="role" itemLabel="role"
					itemValue="id" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<form:form class="form-inline" action="/adminPanel/usr" method="post"
			modelAttribute="usr">
			<form:hidden path="id" />
			<custom:hiddenInputs
				excludeParams="username, password, email, role, id" />
			<div class="form-group">
				<label for="username"><form:errors path="username" /></label>
				<form:input path="name" class="form-control" />
				<label for="password"><form:errors path="password" /></label>
				<form:input path="password" class="form-control" />
				<label for="email"><form:errors path="email" /></label>
				<form:input path="email" class="form-control" />
				<form:select path="role" items="${roles}" itemLabel="role"
					itemValue="id">
				</form:select>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>
	</div>
	<c:forEach items="${page.content}" var="usr">
		<div class="row">
			<div class="col-md-2">${usr.name}</div>
			<div class="col-md-2">${usr.password}</div>
			<div class="col-md-2">${usr.email}</div>
			<div class="col-md-2">${usr.role.role}</div>
			<div class="col-md-2">
				<a href="/adminPanel/usr/delete/${usr.id}<custom:allParams/>">delete</a>
			</div>
			<div class="col-md-2">
				<a href="/adminPanel/usr/update/${usr.id}<custom:allParams/>">update</a>
			</div>
		</div>
	</c:forEach>
	<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>
<div class="col-md-2 col-xs-12">
	<div class="col-md-6">
		<div class="dropdown">
			<button class="btn btn-primary dropdown-toggle" type="button"
				data-toggle="dropdown">
				Sort <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<custom:sort innerHtml="Role asc" paramValue="role.role" />
				<custom:sort innerHtml="Role desc" paramValue="role.role,desc" />
				<custom:sort innerHtml="Username asc" paramValue="username" />
				<custom:sort innerHtml="Username desc" paramValue="username,desc" />
				<custom:sort innerHtml="Email asc" paramValue="email" />
				<custom:sort innerHtml="Email desc" paramValue="email,desc" />
			</ul>
		</div>
	</div>
	<div class="col-md-6">
		<custom:size posibleSizes="1,2,5,10" size="${page.size}"
			title="Розмір сторінки" />
	</div>
</div>