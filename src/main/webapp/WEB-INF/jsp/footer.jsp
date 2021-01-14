<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.12.2020
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.5.0/css/flag-icon.min.css" integrity="sha512-Cv93isQdFwaKBV+Z4X8kaVBYWHST58Xb/jVOcV9aRsGSArZsgAnFIhMpDoMDcFNoUtday1hdjn0nGp3+KZyyFw==" crossorigin="anonymous" />
</head>
<body>
<c:set var="previousPage" value="${pageContext.request.requestURI}" scope="session"/>
<div class="container-fluid bg-light">
    <nav class="navbar navbar-expand-lg navbar-light rounded">
        <div class="container" id="footerNavbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a href="<c:url value='/lang?loc=en_US'/>">
                        <span class="flag-icon flag-icon-us"></span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value='/lang?loc=ru_RU'/>">
                        <span class="flag-icon flag-icon-ru"></span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>
