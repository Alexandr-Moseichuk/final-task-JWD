<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:url value="/login" var="loginPage" />
    <a href='<c:out value="${ loginPage }"/>'>На страницу логина</a>
    Список рекламных кампаний
    <jsp:useBean id="campaignList" scope="request" type="java.util.List"/>
    <c:forEach var="campaign" items="${campaignList}">
        <fmt:setLocale value="ru-RU"/>
        <h5><c:out value="${campaign.title}"/></h5>
        Дата создания: <c:out value="${campaign.createDate}"/>
        <br>
        Дата начала:     <c:out value="${campaign.beginDate}"/>
        Дата завершения: <c:out value="${campaign.endDate}"/>
        <br>
        <h3>Описание:</h3>
        <c:out value="${campaign.description}"/>
        <h3>Требования:</h3>
        <c:out value="${campaign.requirement}"/>
        <h3>Бюджет:</h3> <c:out value="${campaign.budget}"/>
    </c:forEach>

</body>
</html>
