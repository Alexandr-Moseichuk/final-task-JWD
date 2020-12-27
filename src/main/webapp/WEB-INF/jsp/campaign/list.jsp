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
        <h4><c:out value="${campaign.title}"/></h4>
        Дата создания: <c:out value="${campaign.createDate.time}"/>
        <br>
        Дата начала:     <c:out value="${campaign.beginDate.time}"/>
        Дата завершения: <c:out value="${campaign.endDate.time}"/>
        <br>
        <h5>Описание:</h5>
        <c:out value="${campaign.description}"/>
        <h5>Требования:</h5>
        <c:out value="${campaign.requirement}"/>
        <h5>Бюджет:</h5> <c:out value="${campaign.budget}"/>
    </c:forEach>

</body>
</html>
