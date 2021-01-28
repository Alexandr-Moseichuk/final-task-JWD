<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.12.2020
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
</head>
<body>
        <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
        <fmt:setBundle basename="localization.pagecontent" var="rb" />
        <nav class="navbar sticky-top navbar-expand-lg bg-dark navbar-dark">
            <div class="container justify-content-center">
                <a class="navbar-brand" href="<c:url value="/"/>">
                    <fmt:message key="brand.name" bundle="${ rb }" />
                </a>
            </div>
            <div class="container justify-content-center">
                <ul class="navbar-nav text-nowrap">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/campaign/list.html"/>">
                            <fmt:message key="menu.campaigns" bundle="${ rb }" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/influencer/list.html"/>">
                            <fmt:message key="menu.influencers" bundle="${ rb }" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/manager/list.html"/>">
                            <fmt:message key="menu.managers" bundle="${ rb }" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/advertiser/list.html"/>">
                            <fmt:message key="menu.advertisers" bundle="${ rb }" />
                        </a>
                    </li>
                </ul>
            </div>
            <div class="container justify-content-center">
                <ul class="navbar-nav text-nowrap">
                    <li class="nav-item dropdown">
                        <jsp:useBean id="authorizedUser" scope="session" type="by.moseichuk.adlinker.bean.User"/>
                        <jsp:useBean id="menuList" scope="session" type="java.util.List"/>
                        <a class="nav-link dropdown-toggle" href="#"  data-toggle="dropdown">
                            <c:out value="${authorizedUser.userInfo.lastName} ${authorizedUser.userInfo.firstName}"/>
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="/user/profile.html?userId=${authorizedUser.id}"/>">
                                <fmt:message key="menu.dropdown.profile" bundle="${ rb }" />
                            </a>
                            <c:forEach var="menuItem" items="${menuList}">
                                <a class="dropdown-item" href="<c:url value="${menuItem.url}"/>">
                                    <fmt:message key="${menuItem.name}" bundle="${rb}"/>
                                </a>
                            </c:forEach>
                            <a class="dropdown-item" href="<c:url value="/logout.html"/>">
                                <fmt:message key="menu.dropdown.exit" bundle="${ rb }" />
                            </a>
                        </div>
                    </li>
                </ul>
            </div>

        </nav>
</body>
</html>