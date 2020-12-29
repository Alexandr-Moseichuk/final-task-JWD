<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.12.2020
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container">
        <a class="nav-link " href="<c:url value="/"/>">ADLinker</a>
    </div>
    <div class="container">
        <ul class="navbar-nav">
<%--            <li class="nav-item"></li>--%>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/campaign/list"/>">Рекламные кампании</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/influencer/list"/>">Инфлюенсеры</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/manager/list"/>">Менеджеры</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/advertiser/list"/>">Рекламодатели</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/login"/>">Вход</a>
            </li>
        </ul>
        <form class="form-inline" action="/action_page.php">
            <input class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-success" type="submit">Search</button>
        </form>

    </div>

</nav>
</body>
</html>