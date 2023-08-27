<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Phone details" showMenu="true">
    <div class="row mb-3">
        <common:back/>
    </div>
    <div class="row justify-content-center">
        <h2>${phone.brand}&nbsp;${phone.model}</h2>
    </div>
    <div class="container">
        <div class="row">
            <div class=”col-md-2”>
                <img src="<c:url value="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.image}"/>"
                     alt="Phone image"
                     class="image-responsive">
            </div>
            <div class=”col-md-6”>
                <div class="row">
                    <div class="col-md-4">Brand:</div>
                    <div class="col-md-8"><c:out value="${phone.brand}"/></div>
                </div>
                <div class="row">
                    <div class="col-md-4">Model:</div>
                    <div class="col-md-8"><c:out value="${phone.model}"/></div>
                </div>
                <div class="row">
                    <div class="col-md-4">Price:</div>
                    <div class="col-md-8"><c:out value="${phone.price}"/></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <h5>Description:</h5>
                <c:out value="${phone.description}"/>
            </div>
        </div>
    </div>
</common:page>