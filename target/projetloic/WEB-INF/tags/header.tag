<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header class="navbar navbar-inverse navbar-fixed-top">
	<ul class="nav navbar-nav navbar-left">
	<li class="container">
		<mytag:link target="home" classes="navbar-brand" label="Application - Computer Database"  />
	</li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
	<li class="dropdown">
         <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
           <img src="${pageContext.request.contextPath}/resources/css/${pageContext.response.locale}.png" class="flag" /> <spring:message code="i18n.lang" text="Language" /> <span class="caret"></span>
         </a>
             <ul class="dropdown-menu" style="z-index:99999">
             <li><a href="${pageContext.request.pathTranslated }?language=en"><img src="${pageContext.request.contextPath}/resources/css/en.png" class="flag" alt="English" /> English</a></li>
             <li><a href="${pageContext.request.pathTranslated }?language=fr"><img src="${pageContext.request.contextPath}/resources/css/fr.png" class="flag" alt="France" /> Français</a></li>
             <li role="separator" class="divider"></li>
             <li class="text-center"><spring:message code="i18n.current" />: ${pageContext.response.locale}</li>
             </ul>
        </li>
        </ul>
</header>