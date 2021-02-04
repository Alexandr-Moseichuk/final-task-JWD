<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="influencer.list.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body class="bg-light">
<c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
<h3 class="text-center">Список инфлюенсеров</h3>
<div class="container">
    <div class="row justify-content-center">
        <jsp:useBean id="influencerList" scope="request" type="java.util.List"/>
        <c:forEach var="influencer" items="${influencerList}">
            <ctg:userCard photoSrc="../../img/icon-user.svg" user="${influencer}">
                <a href="<c:url value="/user/profile.html?userId=${influencer.id}"/>" class="btn btn-primary stretched-link">See Profile</a>
            </ctg:userCard>
        </c:forEach>
    </div>
</div>
<c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
