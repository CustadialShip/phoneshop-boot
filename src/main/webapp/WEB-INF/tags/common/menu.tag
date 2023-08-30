<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
    <div class="col-1 offset-8">
        <c:if test="${not empty displayName}">
            <p>Hello, dear <b>${displayName}</b>!</p>
        </c:if>
    </div>
    <div class="col-3">
        <security:authorize access="not isAuthenticated()">
            <a href="<c:url value="/login"/>">
                <button type="button" class="btn btn-primary">Login</button>
            </a>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <form method=post action="<c:url value="/logout"/>">
                <input type="submit" class="btn btn-primary" value="Logout"/>
                <sec:csrfInput/>
            </form>
        </security:authorize>
    </div>
    <div class="col-4 offset-9">
        <security:authorize access="hasAuthority('ROLE_ADMIN')">
            <a href="<c:url value="/admin"/>">
                <p>Admin panel</p>
            </a>
        </security:authorize>
    </div>
</div>