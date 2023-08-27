<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="phones" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="maxPages" required="true" type="java.lang.Integer" %>

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
            <c:if test="${phones.hasPrevious()}">
                <a class="page-link" href="?page=${phones.previousOrFirstPageable().pageNumber}"><<</a>
            </c:if>
        </li>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <li class="${phones.pageable.pageNumber eq i ? "page-item active" : "page-item"}">
                <a class="page-link" href="?page=${i}">${i + 1}</a>
            </li>
        </c:forEach>
        <li class="page-item">
            <c:if test="${phones.hasNext()}">
                <a class="page-link" href="?page=${phones.nextOrLastPageable().pageNumber}">>></a>
            </c:if>
        </li>
    </ul>
</c:if>