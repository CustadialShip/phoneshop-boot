<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="phone" tagdir="/WEB-INF/tags/phone" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Phone list" showMenu="true">
    <div class="row justify-content-center font-italic mb-3">
        Found <c:out value="${phones.totalElements}"/> results!
    </div>
    <div class="row justify-content-center mb-3">
        <div class="col-4">
            <div class="card">
                <div class="card-body">
                    <form:form method="get" modelAttribute="searchForm" action="/phones">
                        <div class="row mb-3">
                            <form:label path="searchQuery" for="searchQuery" class="col-4">
                                Search query:
                            </form:label>
                            <form:input type="text" path="searchQuery" value="" class="col-8"/>
                        </div>
                        <div class="row mb-3">
                            <form:label path="fromPrice" for="fromPrice" class="col-4">
                                From price:
                            </form:label>
                            <form:input type="number" path="fromPrice" value="" class="col-8"/>
                        </div>
                        <div class="row mb-3">
                            <form:label path="toPrice" for="toPrice" class="col-4">
                                To price:
                            </form:label>
                            <form:input type="number" path="toPrice" value="" class="col-8"/>
                        </div>
                        <form:button class="btn btn-primary">
                            Search
                        </form:button>
                    </form:form>
                </div>
            </div>
            <div class="row justify-content-center">
                <a href="/phones">Reset search parameters</a>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${phones.totalElements > 0}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Image</th>
                    <th scope="col">Brand <util:sorting/></th>
                    <th scope="col">Model <util:sorting/></th>
                    <th scope="col">Price <util:sorting/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="phone" items="${phones.content}">
                    <phone:tile phone="${phone}"/>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>Oops! Nothing found for your request...</h2>
        </c:otherwise>
    </c:choose>
    <util:pagination phones="${phones}" maxPages="${maxPages}" searchForm="${searchForm}"/>
</common:page>