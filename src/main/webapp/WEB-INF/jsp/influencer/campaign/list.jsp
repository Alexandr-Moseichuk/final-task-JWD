<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="advertiser.campaign.list.title" bundle="${rb}" /></title>
    <c:import url="../../style.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
<h3><fmt:message key="advertiser.campaign.list.title" bundle="${rb}" /></h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th></th>
        <th><fmt:message key="campaign.list.table.header.id" bundle="${rb}" /></th>
        <th><fmt:message key="campaign.list.table.header.title" bundle="${rb}" /></th>
        <th><fmt:message key="campaign.list.table.header.begin_date" bundle="${rb}" /></th>
        <th><fmt:message key="campaign.list.table.header.end_date" bundle="${rb}" /></th>
        <th><fmt:message key="campaign.list.table.header.edit_link" bundle="${rb}" /></th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="campaignSubList" scope="request" type="java.util.List"/>
    <c:forEach var="campaign" items="${campaignSubList}">
        <tr>
            <td><div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" class="form-check-input" name="selected" value="${campaign.id}">
                </label>
            </div></td>
            <td><c:out value="${campaign.id}"/></td>
            <td><c:out value="${campaign.title}"/></td>
            <td><fmt:formatDate type="both" value="${campaign.beginDate.time}" dateStyle="medium" timeStyle="medium"/></td>
            <td><fmt:formatDate type="both" value="${campaign.endDate.time}" dateStyle="medium" timeStyle="medium"/></td>
            <td>
                <a href="<c:url value="/campaign/page.html?campaignId=${campaign.id}"/>">
                    <fmt:message key="campaign.list.table.campaign_page_link" bundle="${rb}" />
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="row justify-content-center">
    <ctg:pagination currentPage="${currentPage}" lastPage="${lastPage}" url="${url}"/>
</div>
<%--    <ctg:pagination currentPage="${currentPage}" lastPage="${lastPage}" url="${url}"/>--%>
<c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
