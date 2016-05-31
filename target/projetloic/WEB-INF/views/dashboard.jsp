<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="taglib" uri="/WEB-INF/customtags.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" media="screen">
	
	<spring:message code="i18n.confirmdelete" var="confirm" />
	<script>var options = { confirm: "${confirm}" }</script>
</head>
<body>
	
	<mytag:header />
	<section id="main">
		<div class="container">
			<c:if test="${ successNumber > 0 }">
				<div class="alert alert-success">${ successNumber } <spring:message code="i18n.successlabel" /></div>
			</c:if>
			<h1 id="homeTitle">${ count } <spring:message code="i18n.computersfound" text="default text" /></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search" value="${ p.search }"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addcomputer"><spring:message code="i18n.addcomputer" text="Add computer" /></a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="i18n.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="i18n.computername" /> 
							<mytag:link target="home" label="" search="${p.search}" classes="glyphicon glyphicon-chevron-up" page="${ page.currentPage }" 
														limit="${p.elementsByPage}" 
														order="name" 
														sort="ASC" />
							<mytag:link target="home" label="" search="${p.search}" classes="glyphicon glyphicon-chevron-down" page="${ page.currentPage }" 
														limit="${p.elementsByPage}" 
														order="name" 
														sort="DESC" />
					    </th>
						<th><spring:message code="i18n.introduced" /> <mytag:link target="home" 
														label="" 
														search="${p.search}" 
														classes="glyphicon glyphicon-chevron-up"
														page="${ p.currentPage }" 
														limit="${p.elementsByPage}" 
														order="introduced" 
														sort="ASC" />
											<mytag:link target="home" 
														label="" 
														search="${p.search}" 
														classes="glyphicon glyphicon-chevron-down"
														page="${ p.currentPage }" 
														limit="${p.elementsByPage}" 
														order="introduced" 
														sort="DESC" /></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="i18n.discontinued" /> <mytag:link target="home" 
														label="" 
														search="${p.search}" 
														classes="glyphicon glyphicon-chevron-up" 
														page="${ p.currentPage }" 
														limit="${p.elementsByPage}" 
														order="discontinued" 
														sort="ASC" />
											  <mytag:link target="home" 
														label="" 
														search="${p.search}" 
														classes="glyphicon glyphicon-chevron-down" 
														page="${ p.currentPage }" 
														limit="${p.elementsByPage}" 
														order="discontinued" 
														sort="DESC" />
														</th>
						<!-- Table header for Company -->
						<th><spring:message code="i18n.company" />	<mytag:link target="home" 
														label="" 
														search="${p.search}" 
														classes="glyphicon glyphicon-chevron-up" 
														page="${ p.currentPage }" 
														limit="${p.elementsByPage}" 
														order="company" 
														sort="ASC" />
											  <mytag:link target="home" 
														label="" 
														search="${p.search}" 
														classes="glyphicon glyphicon-chevron-down" 
														page="${ p.currentPage }" 
														limit="${p.elementsByPage}" 
														order="company" 
														sort="DESC" />
														</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${p.elements}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb" id="${ computer.name }_id"
								class="cb" value="${ computer.id }"></td>
							<td><mytag:link target="editcomputer" id="${ computer.name }_name" datas="computerId=${computer.id}" label="${ computer.name }"  /></td>
							
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.companyName}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<mytag:pagination page="${ p }" />
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>

</body>
</html>
