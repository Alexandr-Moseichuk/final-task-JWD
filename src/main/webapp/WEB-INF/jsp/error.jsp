
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>404 Страница не найдена!</title>
</head>
<body>
404 Страница не найдена!
<jsp:useBean id="errorMessage" scope="request" type="java.lang.String"/>
Сообщение: ${errorMessage}
</body>
</html>
