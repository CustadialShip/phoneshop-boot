<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Login" showMenu="false">
    <div class="row mb-3">
        <common:back/>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <form method="post">
                        <div class="form-group row">
                            <label for="username" class="col-sm-3 col-form-label">
                                Username:
                            </label>
                            <div class="col-sm-8">
                                <input type="text" id="username" name="username" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-3 col-form-label">
                                Password:
                            </label>
                            <div class="col-sm-8">
                                <input type="password" id="password" name="password" class="form-control"/>
                            </div>
                        </div>
                        <sec:csrfInput />
                        <c:if test="${param.error eq true}">
                            <div>
                                <span class="alert alert-danger">
                                    Something went wrong. Please try again
                                </span>
                            </div>
                        </c:if>
                        <div class="row justify-content-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                        <div class="row justify-content-center">
                            <a href="/oauth2/authorization/github">
                                Login via GitHub >>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</common:page>