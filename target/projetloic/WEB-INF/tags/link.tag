<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="true" type="java.lang.String" %>
<%@ attribute name="label" required="true" type="java.lang.String"%>
<%@ attribute name="classes" required="false" type="java.lang.String"%>
<%@ attribute name="datas" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="false" type="java.lang.String" %>
<%@ attribute name="limit" required="false" type="java.lang.String" %>


<c:choose>
	<c:when test="${ not empty classes }">
		<c:set var="nClass" value="${classes}" />
	</c:when>
	<c:otherwise>
		<c:set var="nClass" value="" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${ not empty page }">
		<c:set var="nPage" value="p=${page}" />
	</c:when>
	<c:otherwise>
		<c:set var="nPage" value="" />
	</c:otherwise>
</c:choose>


<c:choose>
	<c:when test="${ not empty limit and empty page }">
		<c:set var="nLimit" value="l=${limit}" />
	</c:when>
	<c:when test="${ not empty limit and not empty page }">
		<c:set var="nLimit" value="&l=${limit}" />
	</c:when>
	<c:otherwise>
		<c:set var="nLimit" value="" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${ not empty datas }">
		<c:set var="nDatas" value="${ datas }" />
	</c:when>
	<c:otherwise>
		<c:set var="nDatas" value="" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${ target == '#' }">
		<a href="#">${ label }</a>
	</c:when>
	<c:otherwise>
		<a href="${ target }?${nDatas}${nPage}${nLimit}" class="${ nClass }">${ label }</a>
	</c:otherwise>
</c:choose>


