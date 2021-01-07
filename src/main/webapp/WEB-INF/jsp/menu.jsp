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
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
                        <a class="nav-link" href="<c:url value="/campaign/list"/>">
                            <fmt:message key="menu.campaings" bundle="${ rb }" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/influencer/list"/>">
                            <fmt:message key="menu.influencers" bundle="${ rb }" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/manager/list"/>">
                            <fmt:message key="menu.managers" bundle="${ rb }" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/advertiser/list"/>">
                            <fmt:message key="menu.advertisers" bundle="${ rb }" />
                        </a>
                    </li>
                </ul>
            </div>
            <div class="container justify-content-center">
                <ul class="navbar-nav text-nowrap">
                    <li class="nav-item dropdown">
                        <jsp:useBean id="authorizedUser" scope="session" type="by.moseichuk.final_task_JWD.bean.User"/>
                        <jsp:useBean id="menuList" scope="session" type="java.util.List"/>
                        <a class="nav-link dropdown-toggle" href="#"  data-toggle="dropdown">
                            <c:out value="${authorizedUser.userInfo.lastName} ${authorizedUser.userInfo.firstName}"/>
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="/user/profile"/>">
                                <fmt:message key="menu.dropdown.profile" bundle="${ rb }" />
                            </a>
                            <c:forEach var="menuItem" items="${menuList}">
                                <a class="dropdown-item" href="<c:url value="${menuItem.url}"/>">
                                    <c:out value="${menuItem.name}"/>
                                </a>
                            </c:forEach>
                            <a class="dropdown-item" href="<c:url value="/exit"/>">
                                <fmt:message key="menu.dropdown.exit" bundle="${ rb }" />
                            </a>
                        </div>
                    </li>
                </ul>
            </div>

        </nav>
</body>
</html>