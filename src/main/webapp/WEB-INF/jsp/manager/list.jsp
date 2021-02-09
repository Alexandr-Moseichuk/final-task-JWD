<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="manager.list.title" bundle="${rb}" /></title>
    <c:import url="../style.jsp"/>
</head>
<body class="bg-light">
<c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
<h3 class="text-center"><fmt:message key="manager.list.title" bundle="${rb}" /></h3>
    <div class="container">
        <div class="row justify-content-center">
            <jsp:useBean id="managerList" scope="request" type="java.util.List"/>
            <c:forEach var="manager" items="${managerList}">
                <c:choose>
                    <c:when test="${empty manager.userInfo.userFile}">
                        <c:set var="userPhoto" value="../img/icon-user.svg"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="userPhoto" value="../${manager.userInfo.userFile.path}"/>
                    </c:otherwise>
                </c:choose>
                <ctg:userCard photoSrc="${userPhoto}" user="${manager}">
                    <c:if test="${sessionScope.authorizedUser.role eq 'INFLUENCER'}">
                        <form action="subscribe.html?managerId=${manager.id}" method="post">
                            <button type="submit">
                                Подписаться
                            </button>
                        </form>
                    </c:if>
                    <a href="<c:url value="/user/profile.html?userId=${manager.id}"/>" class="btn btn-primary stretched-link">See Profile</a>
                </ctg:userCard>
            </c:forEach>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
