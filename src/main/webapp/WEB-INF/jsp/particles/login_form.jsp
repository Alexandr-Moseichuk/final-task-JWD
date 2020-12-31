<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Login form</title>
</head>
<body>
<form action="login" method="post">
    <div class="form-group">
        <%--            <label for="email">Email:</label>--%>
        <input type="email" class="form-control" placeholder="Email" id="email" name="email">
    </div>
    <div class="form-group">
        <%--            <label for="password">Password:</label>--%>
        <input type="password" class="form-control" placeholder="Password" id="password" name="password">
    </div>
    <div class="form-group form-check col-sm-3">
        <label class="form-check-label">
            <input class="form-check-input" type="checkbox"> Remember
        </label>
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
    <a href=<c:url value="/registration"/>>Registration</a>
</form>
</body>
</html>
