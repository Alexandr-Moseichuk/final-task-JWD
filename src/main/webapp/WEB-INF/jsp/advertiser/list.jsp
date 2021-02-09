<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.01.2021
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="advertiser.list.title" bundle="${rb}" /></title>
    <c:import url="../style.jsp"/>
</head>
<body  class="bg-light">
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <h3 class="text-center">Список рекламодателей</h3>
    <div class="container">
        <div class="row justify-content-center">
            <jsp:useBean id="advertiserList" scope="request" type="java.util.List"/>
            <c:forEach var="advertiser" items="${advertiserList}">
                <c:choose>
                    <c:when test="${empty advertiser.userInfo.userFile}">
                        <c:set var="userPhoto" value="../img/icon-user.svg"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="userPhoto" value="../${advertiser.userInfo.userFile.path}"/>
                    </c:otherwise>
                </c:choose>
                <div class="card ml-2 mr-2 mt-2 mb-2" style="width:300px">
                    <img class="card-img-top" src="${userPhoto}" alt="Card image" style="width:100%">
                    <div class="card-body">
                        <h4><c:out value="${advertiser.userInfo.lastName}"/></h4>
                        <h5><c:out value="${advertiser.userInfo.firstName} ${advertiser.userInfo.secondName}"/></h5>
                        <p class="card-text"><c:out value="${advertiser.userInfo.description}"/></p>
                        <a href="<c:url value="/user/profile.html?userId=${advertiser.id}"/>" class="btn btn-primary stretched-link">See Profile</a>
                    </div>
                    <div class="card-footer">
                        <h6><c:out value="${advertiser.userInfo.phoneNumber}"/></h6>
                        <h6><c:out value="${advertiser.registrationDate.time}"/></h6>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
