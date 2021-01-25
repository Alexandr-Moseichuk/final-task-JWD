<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="influencer.list.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body>
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <h3 class="text-center">Список инфлюенсеров</h3>
    <div class="container">
        <div class="row justify-content-center">
            <jsp:useBean id="influencerList" scope="request" type="java.util.List"/>
            <c:forEach var="influencer" items="${influencerList}">
                <div class="card ml-2 mr-2 mt-2 mb-2" style="width:300px">
                    <img class="card-img-top" src="../img/icon-user.svg" alt="Card image" style="width:100%">
                    <div class="card-body">
                        <h4><c:out value="${influencer.userInfo.lastName}"/></h4>
                        <h5><c:out value="${influencer.userInfo.firstName} ${influencer.userInfo.secondName}"/></h5>
                        <p class="card-text"><c:out value="${influencer.userInfo.description}"/></p>
                        <a href="#" class="btn btn-primary stretched-link">See Profile</a>
                    </div>
                    <div class="card-footer">
                        <h6><c:out value="${influencer.userInfo.phoneNumber}"/></h6>
                        <h6><c:out value="${influencer.registrationDate.time}"/></h6>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
