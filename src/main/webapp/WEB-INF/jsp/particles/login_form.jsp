<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: digge
  Date: 12/31/2020
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="login_form.title" bundle="${rb}" /></title>
</head>
<body>
<form action="login.html" method="post">
    <h5 class="text-center"><fmt:message key="login_form.label.sign_in" bundle="${rb}"/></h5>
    <div class="form-group">
        <%--            <label for="email">Email:</label>--%>
        <input type="email" class="form-control" placeholder="<fmt:message key="login_form.form.email" bundle="${rb}"/>" id="email" name="email">
    </div>
    <div class="form-group">
        <%--            <label for="password">Password:</label>--%>
        <input type="password" class="form-control" placeholder="<fmt:message key="login_form.form.password" bundle="${rb}"/>" id="password" name="password">
    </div>
    <div class="form-group form-check">
        <label class="form-check-label line">
            <input class="form-check-input" type="checkbox"> <fmt:message key="login_form.label.remember" bundle="${rb}" />
        </label>
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="login_form.button.login" bundle="${rb}" /></button>
    <a href="<c:url value='/registration.html'/>">
        <fmt:message key="login_form.label.registration" bundle="${rb}" />
    </a>
</form>
</body>
</html>
