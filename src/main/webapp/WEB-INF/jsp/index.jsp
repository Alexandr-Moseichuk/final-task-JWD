<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: digge
  Date: 12/31/2020
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="index.title" bundle="${rb}" /></title>
    <c:import url="style.jsp"/>
</head>
<body>
    <div class="container pt-3 my-3">
        <div class="row">
            <div class="col-md-8">
                <img class="img-fluid rounded" src="img/welcome_image.jpeg" alt="Welcome image">
            </div>
            <div class="col-md-4">
                <div class="container pt-3 my-3 border">
                    <c:import url="particles/login_form.jsp"/>
                </div>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
