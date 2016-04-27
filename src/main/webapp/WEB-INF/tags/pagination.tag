<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<%@ attribute name="page" required="true" type="com.excilys.computerdatabase.entity.Page" %>

<ul class="pagination">
	<li><a href="home?p=${page.currentPage > 1 ? page.currentPage - 1 : 1}&l=${page.elementsByPage}" aria-label="Previous"> <span
			aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach var="i" begin="${ page.currentPage < 3 ? 1 : page.currentPage - 2 }"
			 end="${ page.currentPage < 4 ? 5 : page.currentPage >= page.maxPages-2 ? page.maxPages : page.currentPage + 2 }">
		<li class="${ page.currentPage == i ? 'active' : '' }"><a href="home?p=${i}&l=${page.elementsByPage}">${i}</a></li>
	</c:forEach>
	<li><a href="home?p=${page.currentPage < page.maxPages ? page.currentPage + 1 : page.maxPages }&l=${page.elementsByPage}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>

<div class="btn-group btn-group-sm pull-right" role="group">
	<a href="home?p=1&l=10" class="btn btn-default ${ page.elementsByPage == 10 ? 'btn-primary' : '' }">10</a>
	<a href="home?p=1&l=50" class="btn btn-default ${ page.elementsByPage == 50 ? 'btn-primary' : '' }">50</a>
	<a href="home?p=1&l=100" class="btn btn-default ${ page.elementsByPage == 100 ? 'btn-primary' : '' }">100</a>
</div>