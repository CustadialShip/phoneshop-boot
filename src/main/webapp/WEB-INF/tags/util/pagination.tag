<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ attribute name="entities" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="maxPages" required="true" type="java.lang.Integer" %>
<%@ attribute name="searchForm" required="false" type="com.expertsoft.phoneshop.dto.SearchFormDto" %>
<c:set var="searchParams" value="searchQuery=${searchForm.searchQuery}&fromPrice=${searchForm.fromPrice}&toPrice=${searchForm.toPrice}"/>

<c:set var="maxPages" value="${maxPages > entities.totalPages ? entities.totalPages : maxPages}"/>
<c:set var="lowerBound" value="${entities.pageable.pageNumber - (maxPages - 1) / 2}"/>
<c:set var="upperBound" value="${entities.pageable.pageNumber + (maxPages - 1) / 2}"/>
<c:choose>
    <c:when test="${lowerBound < 0}">
        <c:set var="end" value="${upperBound > entities.totalPages - 1 ? entities.totalPages - 1 : upperBound - lowerBound}"/>
    </c:when>
    <c:otherwise>
        <c:set var="end" value="${upperBound > entities.totalPages - 1 ? entities.totalPages - 1 : upperBound}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${upperBound > entities.totalPages - 1}">
        <c:set var="begin" value="${lowerBound < 0 ? 0 : lowerBound + (entities.totalPages - 1 - upperBound)}"/>
    </c:when>
    <c:otherwise>
        <c:set var="begin" value="${lowerBound < 0 ? 0 : lowerBound}"/>
    </c:otherwise>
</c:choose>

<c:if test="${entities.totalElements > 1}">
    <ul class="pagination d-flex justify-content-center">
        <li class="page-item">
            <util:url var="urlPrev" page="${entities.previousOrFirstPageable().pageNumber}"/>
            <c:if test="${entities.hasPrevious()}">
                <a class="page-link" href="${urlPrev}"><<</a>
            </c:if>
        </li>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <util:url var="url" page="${i}"/>
            <li class="${entities.pageable.pageNumber eq i ? "page-item active" : "page-item"}">
                <a class="page-link" href="${url}">${i + 1}</a>
            </li>
        </c:forEach>
        <li class="page-item">
            <util:url var="urlNext" page="${entities.nextOrLastPageable().pageNumber}"/>
            <c:if test="${entities.hasNext()}">
                <a class="page-link" href="${urlNext}">>></a>
            </c:if>
        </li>
    </ul>
</c:if>