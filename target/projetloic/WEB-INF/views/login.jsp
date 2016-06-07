<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="header.title" text="Computer Database" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link
			href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
			rel="stylesheet" media="screen">
		<link
			href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
			rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/resources/css/main.css"
			rel="stylesheet" media="screen">
	</head>
	<body>
		<mytag:header />
		
		<div class="container">
			<form id="login_form" action="${pageContext.request.contextPath}/login" method="post">
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">Invalid username and/or password.</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">You have been logged out.</div>
				</c:if>
				<p>
					<label for="username"><spring:message code="field_username" /></label>
					<input class="form-control" type="text" id="username" name="username" />
				</p>
				<p>
					<label for="password"><spring:message code="field_password" /></label>
					<input class="form-control" type="password" id="password"
						name="password" />
				</p>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" class="form-control" />
				<button type="submit" class="btn btn-primary">
					<spring:message code="button_login" />
				</button>
			</form>
		</div>	
		<script	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<script	src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
	</body>
</html>