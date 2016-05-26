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
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" media="screen">
	
	<spring:message code="i18n.nameerror" var="incorrectname" />
	<spring:message code="i18n.introducederror" var="incorrectdate" />
	<spring:message code="i18n.discontinuederror" var="incorrectdis" />
 	<script>
		var options = {
			name: "${incorrectname}",
			introduced: "${incorrectdate}",
			discontinued: "${incorrectdis}",
		}
 	</script>
</head>
<body>
    <mytag:header />

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="i18n.addcomputer" /></h1>
                    <c:if test="${ not empty errors.errors }">
						<div class="alert alert-danger">
							<ul>
							<c:forEach var="c" items="${ errors }">
								<li>${ c.value }</li>
							</c:forEach>
							</ul>
						</div>
					</c:if>
                    <div class="alert alert-danger res" style="display:none"></div>
                    <spring:message code="i18n.computername" var="computernameP" />
                    <spring:message code="i18n.introduced" var="introducedP" />
                    <spring:message code="i18n.discontinued" var="discontinuedP" />
                    <form id="form" action="addcomputer" method="POST">
                        <fieldset>
                            <div class="form-group name">
                                <label class="control-label" for="computerName"><spring:message code="i18n.computername" /></label>
                                <input type="text" name="name" value="${ name }" class="form-control text-danger" id="computerName" placeholder="${computernameP}">
                            	<div class="alert alert-danger errmsg" style="display:none;"></div>
                            </div>
                            <div class="form-group introduced">
                                <label class="control-label" for="introduced"><spring:message code="i18n.introduced" /> (dd-mm-yyyy)</label>
                                <input type="date" name="introduced" value="${ introduced }" class="form-control" id="introduced" placeholder="${introducedP }">
                            	<div class="alert alert-danger errmsg" style="display:none;"></div>
                            </div>
                            <div class="form-group discontinued">
                                <label class="control-label" for="discontinued"><spring:message code="i18n.discontinued" /> (dd-mm-yyyy)</label>
                                <input type="date" name="discontinued" value="${ discontinued }" class="form-control" id="discontinued" placeholder="${ discontinuedP }">
                            	<div class="alert alert-danger errmsg" style="display:none;"></div>
                            </div>
                            <div class="form-group company">
                                <label class="control-label" for="companyId"><spring:message code="i18n.company" /></label>
                                <select class="form-control" name="company" id="companyId" >
                                    <option value="0" ${ company == 0 ? 'selected' : '' }>--</option>
                                    <c:forEach items="${ listcompanies }" var="comp">
                                    	<option value="${comp.id}" ${ comp.id == company ? 'selected' : '' }>${ comp.name }</option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                        	<spring:message code="i18n.add" var="addVar" />
                            <input name="submit" type="submit" value="${ addVar }" class="btn btn-primary"></input>
                            or
                            <mytag:link target="home" classes="btn btn-default" label="Cancel"  />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/addComputer.js"></script>
    <c:if test="${errors.introduced != null}"><spring:message code="${errors.introduced}" var="errordate1" /></c:if>
    <c:if test="${errors.discontinued != null}"><spring:message code="${errors.discontinued}" var="errordate2" /></c:if>
    <c:if test="${errors.name != null}"><spring:message code="${errors.name}" var="errorname" /></c:if>
    <script>
    	$(function(){
    		var name = "${errorname}";
    		var introduced = "${errordate1}";
    		var discontinued = "${errordate2}";
    		if(name){
    			displayerror("name", name);
    		}
    		if(introduced){
    			displayerror("introduced", introduced);
    		}
    		if(discontinued){
    			displayerror("discontinued", discontinued);
    		}
    	});
    </script>

</body>
</html>