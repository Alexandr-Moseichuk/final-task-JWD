<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="campaign.edit.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

    <script src="https://unpkg.com/gijgo@1.9.13/js/messages/messages.ru-ru.js" type="text/javascript"></script>
</head>
<body class="bg-light">
<c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
<div class="container p-3 my-3 border bg-white shadow-sm">
    <h4 class="text-center">${campaign.title}</h4>
    <div class="col-md-12 position-relative">
        <p><fmt:message key="campaign.list.label.creation_date" bundle="${rb}" /> <fmt:formatDate value="${campaign.createDate.time}" pattern="dd.MM.yyyy"/></p>
    </div>
    <div class="col-md-12 position-relative">
        <label for="description"><fmt:message key="campaign.list.label.description" bundle="${rb}" /></label>
        <textarea class="form-control" rows="5" id="description" name="description" disabled>${campaign.description}</textarea>
    </div>
    <div class="col-md-12 position-relative">
        <label for="requirement"><fmt:message key="campaign.list.label.requirement" bundle="${rb}" /></label>
        <textarea class="form-control" rows="5" id="requirement" name="requirement" disabled>${campaign.requirement}</textarea>
    </div>
    <div class="col-md-6">
        <p><fmt:message key="campaign.list.label.begin_date" bundle="${rb}" /> <fmt:formatDate value="${campaign.beginDate.time}" pattern="dd.MM.yyyy"/></p>
    </div>
    <div class="col-md-6">
        <p><fmt:message key="campaign.list.label.end_date" bundle="${rb}" /> <fmt:formatDate value="${campaign.endDate.time}" pattern="dd.MM.yyyy"/></p>
    </div>
    <div class="col-md-12">
        <label for="budget"><fmt:message key="campaign.list.label.budget" bundle="${rb}" /></label>
        <input type="text" class="form-control" id="budget" name="budget" value="${campaign.budget}" required>
    </div>
</div>
<c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
