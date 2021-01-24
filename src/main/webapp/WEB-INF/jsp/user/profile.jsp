<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="user.profile.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body>
    <c:import url="../particles/menu.jsp"/>
    <div class="container my-3">
        <div class="row">
            <div class="col-2">
                <img class="img-thumbnail img-fluid" src="../img/icon-user.svg" alt="User photo">
            </div>
            <div class="col-10">
                <h3>${user.userInfo.lastName} <ctg:userRoleBadge userRole="${user.role}"/></h3>
                <h4>${user.userInfo.firstName} ${user.userInfo.secondName}</h4>
                <h4>${user.email}</h4>
            </div>
        </div>
        <dl class="row my-3">
            <dt class="col-2">
                Desc
            </dt>
            <dd class="col-10">
                <p>${user.userInfo.description}</p>
            </dd>
            <dt class="col-2">
                Phone
            </dt>
            <dd class="col-10">
                <h5>${user.userInfo.phoneNumber}</h5>
            </dd>


        </dl>
    </div>
</body>
</html>
