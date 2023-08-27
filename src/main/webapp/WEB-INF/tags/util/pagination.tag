<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="phones" required="true" type="org.springframework.data.domain.Page" %>

<c:if test="${phones.totalElements > 1}">
    <ul class="pagination d-flex justify-content-center">
        <li class="page-item">
            <c:if test="${phones.hasPrevious()}">
                <a class="page-link" href="?page=${phones.previousOrFirstPageable().pageNumber}"><<</a>
            </c:if>
        </li>
        <c:forEach begin="0" end="${phones.totalPages - 1}" var="i">
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