<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="campaign.list.title" bundle="${rb}" /></title>
    <c:import url="../style.jsp"/>
</head>
<body class="bg-light">
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <h3 class="text-center"><fmt:message key="campaign.list.label.title" bundle="${rb}"/></h3>
    <jsp:useBean id="campaignSubList" scope="request" type="java.util.List"/>
    <fmt:setLocale value="ru-RU"/>
    <div class="container">
        <c:forEach var="campaign" items="${campaignSubList}">
    <%--        <ctg:welcome-message campaign="${campaign}"/>--%>
            <div class="container my-3 border">
                <h5><c:out value="${campaign.title}"/></h5>
                <p><fmt:message key="campaign.list.label.creation_date" bundle="${rb}"/> <fmt:formatDate value="${campaign.createDate.time}" type="both" dateStyle="medium" timeStyle="medium"/></p>
                <p>
                    <fmt:message key="campaign.list.label.begin_date" bundle="${rb}"/> <fmt:formatDate value="${campaign.beginDate.time}" type="both" dateStyle="medium" timeStyle="medium"/>
                    <br>
                    <fmt:message key="campaign.list.label.end_date" bundle="${rb}"/> <fmt:formatDate value="${campaign.endDate.time}" type="both" dateStyle="medium" timeStyle="medium"/>
                </p>
                <h5><fmt:message key="campaign.list.label.description" bundle="${rb}"/></h5>
                <p><c:out value="${campaign.description}"/></p>
                <h5><fmt:message key="campaign.list.label.requirement" bundle="${rb}"/></h5>
                <p><c:out value="${campaign.requirement}"/></p>
                <h5><fmt:message key="campaign.list.label.budget" bundle="${rb}"/></h5>
                <p class="front-weight-bold"><c:out value="${campaign.budget}"/></p>
                <ul>
                    <c:forEach var="influencer" items="${campaign.influencerList}">
                        <a href="<c:url value="../user/profile.html?userId=${influencer.id}"/> ">
                            ${influencer.userInfo.lastName} ${influencer.userInfo.firstName}
                        </a>
                    </c:forEach>
                </ul>
                <c:if test="${sessionScope.authorizedUser.role eq 'INFLUENCER'}">
                    <form action="subscribe.html" method="post">
                        <input type="hidden" id="influencerId" name="influencerId" value="${sessionScope.authorizedUser.id}"/>
                        <input type="hidden" id="campaignId" name="campaignId" value="${campaign.id}"/>
                        <button type="submit">
                            <fmt:message key="campaign.list.button.subscribe" bundle="${rb}"/>
                        </button>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
        <div class="row justify-content-center">
            <ctg:pagination currentPage="${currentPage}" lastPage="${lastPage}" url="${url}"/>
        </div>
    </div>

    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
