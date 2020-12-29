<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <title>Список кампаний</title>
</head>
<body>
    <c:import url="/WEB-INF/jsp/menu.jsp"/>
    <h3>Список рекламных кампаний</h3>
    <jsp:useBean id="campaignList" scope="request" type="java.util.List"/>
    <fmt:setLocale value="ru-RU"/>
    <c:forEach var="campaign" items="${campaignList}">
<%--        <ctg:welcome-message campaign="${campaign}"/>--%>
        <div class="container">
            <h5><c:out value="${campaign.title}"/></h5>
            <p>Дата создания: <fmt:formatDate value="${campaign.createDate.time}" type="both"/></p>
            <p>
                Дата начала:     <fmt:formatDate value="${campaign.beginDate.time}" type="both"/>
                <br>
                Дата завершения: <fmt:formatDate value="${campaign.endDate.time}" type="both"/>
            </p>
            <h5>Описание:</h5>
            <p><c:out value="${campaign.description}"/></p>
            <h5>Требования:</h5>
            <p><c:out value="${campaign.requirement}"/></p>
            <h5>Бюджет:</h5>
            <p class="front-weight-bold"><c:out value="${campaign.budget}"/></p>
        </div>

    </c:forEach>

</body>
</html>
