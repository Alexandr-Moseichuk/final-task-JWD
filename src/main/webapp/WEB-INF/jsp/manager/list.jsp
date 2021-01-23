<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.01.2021
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список менеджеров</title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
<h3>Список менеджеров</h3>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <jsp:useBean id="managerList" scope="request" type="java.util.List"/>
            <c:forEach var="manager" items="${managerList}">
                <div class="container-sm p-3 my-3 border">
                    <div class="row">
                        <div class="col-sm-2">
                            PHOTO
                        </div>
                        <div class="col-sm-4">
                            <h6><c:out value="${manager.userInfo.lastName}"/></h6>
                            <h6><c:out value="${manager.userInfo.firstName}"/></h6>
                            <h6><c:out value="${manager.userInfo.secondName}"/></h6>
                        </div>
                    </div>
                    <span>
                    <p>
                        <c:out value="${manager.userInfo.description}"/>
                    </p>
                    <p>
                        Phone: <c:out value="${manager.userInfo.phoneNumber}"/>
                    </p>
                </span>
                    <span>
                    На сайте с <c:out value="${manager.registrationDate.time}"/>
                </span>
                    <c:if test="${sessionScope.authorizedUser.role eq 'INFLUENCER'}">
                        <form action="subscribe.html?managerId=${manager.id}" method="post">
                            <button type="submit">
                                Подписаться
                            </button>
                        </form>


                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div class="col-3"></div>
    </div>
</body>
</html>
