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
    <div class="container col-12 col-sm-6 mt-3">
        <div class="row  justify-content-center">
            <div class="col-4">
                <img class="img-thumbnail img-fluid" src="../img/icon-user.svg" alt="User photo">
            </div>
            <div class="col-8">
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
            <c:choose>
                <c:when test="${user.role eq 'INFLUENCER'}">
                    <dt class="col-2">
                        Manager
                    </dt>
                    <dd class="col-10">
                        <a href="<c:url value="/user/profile.html?userId=${manager.id}"/>">
                                ${manager.userInfo.lastName} ${manager.userInfo.firstName}
                        </a>
                    </dd>
                </c:when>
                <c:when test="${user.role eq MANAGER}">

                </c:when>
            </c:choose>

        </dl>
    </div>
</body>
</html>
