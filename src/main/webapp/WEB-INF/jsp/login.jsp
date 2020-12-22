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
<FORM action="${loginUrl}" method="post">
    <LABEL for="mail">Имя пользователя:</LABEL>
    <br/>
    <INPUT type="text" id="mail" name="mail" value="${param.login}">
    <br/>
    <LABEL for="password">Пароль:</LABEL>
    <br/>
    <INPUT type="password" id="password" name="password">
    <br/>
    <BUTTON type="submit">Войти</BUTTON>
</FORM>
</body>
</html>
