
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ошибка!</title>
</head>
<body>
<c:import url="menu.jsp"/>
<jsp:useBean id="errorMessage" scope="request" type="java.lang.String"/>
<div>
    <h4>Сообщение:</h4>
    <p>${errorMessage}</p>
</div>
</body>
</html>
