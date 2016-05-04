<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="true" type="java.lang.String" %>
<%@ attribute name="label" required="true" type="java.lang.String"%>
<%@ attribute name="classes" required="false" type="java.lang.String"%>
<%@ attribute name="datas" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="false" type="java.lang.String" %>
<%@ attribute name="limit" required="false" type="java.lang.String" %>
<%@ attribute name="search" required="false" type="java.lang.String" %>
<%@ attribute name="order" required="false" type="java.lang.String" %>
<%@ attribute name="sort" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>


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
	<c:when test="${ not empty search }">
		<c:set var="nSearch" value="&search=${ search }" />
	</c:when>
	<c:otherwise>
		<c:set var="nSearch" value="" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${ not empty order }">
		<c:set var="nOrder" value="&order=${ order }" />
		<c:choose>
			<c:when	test="${ not empty sort }">
				<c:set var="nSort" value="&sort=${ sort }" />
			</c:when>
			<c:otherwise>
				<c:set var="nSort" value="" />
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:set var="nOrder" value="" />
		<c:set var="nSort" value="" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${ target == '#' }">
		<a href="#">${ label }</a>
	</c:when>
	<c:otherwise>
		<a href="${ target }?${nDatas}${nPage}${nLimit}${nSearch}${nOrder}${nSort}" id="${ id }" class="${ nClass }">${ label }</a>
	</c:otherwise>
</c:choose>


