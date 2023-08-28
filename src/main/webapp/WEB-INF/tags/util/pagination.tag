<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ attribute name="phones" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="maxPages" required="true" type="java.lang.Integer" %>
<%@ attribute name="searchForm" required="true" type="com.expertsoft.phoneshop.dto.SearchFormDto" %>

<c:set var="searchParams" value="searchQuery=${searchForm.searchQuery}&fromPrice=${searchForm.fromPrice}&toPrice=${searchForm.toPrice}"/>

<c:set var="maxPages" value="${maxPages > phones.totalPages ? phones.totalPages : maxPages}"/>
<c:set var="lowerBound" value="${phones.pageable.pageNumber - (maxPages - 1) / 2}"/>
<c:set var="upperBound" value="${phones.pageable.pageNumber + (maxPages - 1) / 2}"/>
<c:choose>
    <c:when test="${lowerBound < 0}">
        <c:set var="end" value="${upperBound > phones.totalPages - 1 ? phones.totalPages - 1 : upperBound - lowerBound}"/>
    </c:when>
    <c:otherwise>
        <c:set var="end" value="${upperBound > phones.totalPages - 1 ? phones.totalPages - 1 : upperBound}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${upperBound > phones.totalPages - 1}">
        <c:set var="begin" value="${lowerBound < 0 ? 0 : lowerBound + (phones.totalPages - 1 - upperBound)}"/>
    </c:when>
    <c:otherwise>
        <c:set var="begin" value="${lowerBound < 0 ? 0 : lowerBound}"/>
    </c:otherwise>
</c:choose>

<c:if test="${phones.totalElements > 1}">
    <ul class="pagination d-flex justify-content-center">
        <li class="page-item">
            <util:url var="urlPrev" page="${phones.previousOrFirstPageable().pageNumber}"/>
            <c:if test="${phones.hasPrevious()}">
                <a class="page-link" href="${urlPrev}"><<</a>
            </c:if>
        </li>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <util:url var="url" page="${i}"/>
            <li class="${phones.pageable.pageNumber eq i ? "page-item active" : "page-item"}">
                <a class="page-link" href="${url}">${i + 1}</a>
            </li>
        </c:forEach>
        <li class="page-item">
            <util:url var="urlNext" page="${phones.nextOrLastPageable().pageNumber}"/>
            <c:if test="${phones.hasNext()}">
                <a class="page-link" href="${urlNext}">>></a>
            </c:if>
        </li>
    </ul>
</c:if>