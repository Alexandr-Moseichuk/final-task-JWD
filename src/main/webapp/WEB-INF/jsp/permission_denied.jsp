<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: digge
  Date: 1/13/2021
  Time: 12:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body class="bg-light">
    Доступ запрещен
    <a href="<c:url value='/' />">На главную</a>
    <a href="<c:url value='/logout.html'/>" >Выйти</a>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
