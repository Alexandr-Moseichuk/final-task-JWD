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
</head>
<body>
<c:set var="previousPage" value="${pageContext.request.requestURI}" scope="session"/>
<footer class="footer ">
    <div class="col">
        <div class="row  justify-content-center">
            <p class="mb-1 text-center">© 2020 ADLinker</p>
        </div>
        <div class="row justify-content-center">
            <ul class="list-inline">
                <li class="list-inline-item">
                    <a href="<c:url value='/lang.html?loc=en_US'/>">
                        <%--                <span class="flag-icon flag-icon-us"></span>--%>
                        ENG
                    </a>
                </li>
                <li class="list-inline-item">
                    <a href="<c:url value='/lang.html?loc=ru_RU'/>">
                        <%--                <span class="flag-icon flag-icon-ru"></span>--%>
                        RU
                    </a>
                </li>
            </ul>
        </div>
    </div>
</footer>
</body>
</html>
