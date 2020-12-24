<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.12.2020
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login" method="post">
    <label for="mail">Имя пользователя:</label>
    <br/>
    <input type="text" id="mail" name="mail">
    <br/>
    <label for="password">Пароль:</label>
    <br/>
    <input type="password" id="password" name="password">
    <br/>
    <button type="submit">Войти</button>
</form>
</body>
</html>
