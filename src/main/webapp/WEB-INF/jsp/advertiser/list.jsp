<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Список рекламодателей</title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body>
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <h3>Список рекламных кампаний</h3>
    <jsp:useBean id="advertiserList" scope="request" type="java.util.List"/>
    <c:forEach var="advertiser" items="${advertiserList}">
        <div class="container-sm p-3 my-3 border">
            <div class="row">
                <div class="col-sm-2">
                    PHOTO
                </div>
                <div class="col-sm-4">
                    <h6><c:out value="${advertiser.userInfo.lastName}"/></h6>
                    <h6><c:out value="${advertiser.userInfo.firstName}"/></h6>
                    <h6><c:out value="${advertiser.userInfo.secondName}"/></h6>
                </div>
            </div>
            <span>
                <p>
                    <c:out value="${advertiser.userInfo.description}"/>
                </p>
                <p>
                    Phone: <c:out value="${advertiser.userInfo.phoneNumber}"/>
                </p>
            </span>
            <span>
                На сайте с <c:out value="${advertiser.registrationDate.time}"/>
            </span>
        </div>


    </c:forEach>
</body>
</html>
