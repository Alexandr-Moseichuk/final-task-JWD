<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.12.2020
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container pt-3 my-3 border col-sm-4">
    <form action="login" method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
        </div>
        <div class="form-group form-check col-sm-3">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <a href="<c:url value="/registration"/>">Registration</a>
    </form>
</div>
</body>
</html>
