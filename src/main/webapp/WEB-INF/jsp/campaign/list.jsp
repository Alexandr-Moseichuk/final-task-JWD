<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <title>Список кампаний</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
    <c:import url="/WEB-INF/jsp/menu.jsp"/>
    <h3>Список рекламных кампаний</h3>
    <jsp:useBean id="campaignSubList" scope="request" type="java.util.List"/>
    <fmt:setLocale value="ru-RU"/>
    <c:forEach var="campaign" items="${campaignSubList}">
<%--        <ctg:welcome-message campaign="${campaign}"/>--%>
        <div class="container-sm p-3 my-3 col-6 border">
            <h5><c:out value="${campaign.title}"/></h5>
            <p>Дата создания: <fmt:formatDate value="${campaign.createDate.time}" type="both" dateStyle="medium" timeStyle="medium"/></p>
            <p>
                Дата начала:     <fmt:formatDate value="${campaign.beginDate.time}" type="both" dateStyle="medium" timeStyle="medium"/>
                <br>
                Дата завершения: <fmt:formatDate value="${campaign.endDate.time}" type="both" dateStyle="medium" timeStyle="medium"/>
            </p>
            <h5>Описание:</h5>
            <p><c:out value="${campaign.description}"/></p>
            <h5>Требования:</h5>
            <p><c:out value="${campaign.requirement}"/></p>
            <h5>Бюджет:</h5>
            <p class="front-weight-bold"><c:out value="${campaign.budget}"/></p>
        </div>
    </c:forEach>

    <div class="container">
        <ctg:pagination currentPage="${currentPage}" lastPage="${lastPage}" url="${url}"/>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
