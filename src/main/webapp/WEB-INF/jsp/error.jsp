
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="error.title" bundle="${rb}" /></title>
</head>
<body>
<c:import url="menu.jsp"/>
<jsp:useBean id="errorMessage" scope="request" type="java.lang.String"/>
<fmt:setLocale value="${ cookie.language.value }" scope="session"/>
<fmt:setBundle basename="localization.pagecontent" var="rb" />
<div>
    <h4><fmt:message key="error.label.message" bundle="${rb}" /></h4>
    <p>${errorMessage}</p>
</div>
</body>
</html>
