<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="taglib" uri="/WEB-INF/customtags.tld"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${ c.id }
                    </div>
                    <h1>Edit Computer</h1>

                    <form id="form" action="editcomputer" method="POST">
                    	<input type="hidden" name="id" id="id" value="${ c.id }" />
                        <fieldset>
                            <div class="form-group name">
                                <label class="control-label" for="computerName">Computer name</label>
                                <input type="text" name="name" value="${ not empty c.name ? c.name : name }" class="form-control text-danger" id="computerName" placeholder="Computer name">
                            </div>
                            <div class="form-group introduced">
                                <label class="control-label" for="introduced">Introduced date (dd-mm-yyyy)</label>
                                <input type="date" name="introduced" value="${ not empty c.introduced ? c.introduced : introduced }" class="form-control" id="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group discontinued">
                                <label class="control-label" for="discontinued">Discontinued date (dd-mm-yyyy)</label>
                                <input type="date" name="discontinued" value="${ not empty c.discontinued ? c.discontinued : discontinued }" class="form-control" id="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group company">
                                <label class="control-label" for="companyId">Company</label>
                                <select class="form-control" name="company" id="companyId" >
                                    <option value="0" ${ company == 0 ? 'selected' : '' }>--</option>
                                    <c:forEach items="${ listcompanies }" var="comp">
                                    	<option value="${comp.id}" ${ comp.id == c.companyId ? 'selected' : '' }>${ comp.name }</option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input name="submit" type="submit" value="Edit" class="btn btn-primary">
                            or
                            <mytag:link target="home" classes="btn btn-default" label="Cancel"  />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>