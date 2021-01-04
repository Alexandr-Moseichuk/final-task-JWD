<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04.01.2021
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body>
    <c:import url="/WEB-INF/jsp/menu.jsp"/>
    <h3>Список инфлюенсеров</h3>
    <div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <jsp:useBean id="influencerList" scope="request" type="java.util.List"/>
        <c:forEach var="influencer" items="${influencerList}">
            <div class="container-sm p-3 my-3 border">
                <div class="row">
                    <div class="col-sm-2">
                        PHOTO
                    </div>
                    <div class="col-sm-4">
                        <h6><c:out value="${influencer.userInfo.lastName}"/></h6>
                        <h6><c:out value="${influencer.userInfo.firstName}"/></h6>
                        <h6><c:out value="${influencer.userInfo.secondName}"/></h6>
                    </div>
                </div>
                <span>
                <p>
                    <c:out value="${influencer.userInfo.description}"/>
                </p>
                <p>
                    Phone: <c:out value="${influencer.userInfo.phoneNumber}"/>
                </p>
            </span>
                <span>
                На сайте с <c:out value="${influencer.registrationDate.time}"/>
            </span>
            </div>

        </c:forEach>
    </div>
    <div class="col-3"></div>
    </div>
</body>
</html>
