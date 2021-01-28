
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <c:if test="${not empty cookie.get('language')}" >
        <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    </c:if>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="error.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body class="bg-light">
    <c:import url="particles/menu.jsp"/>
    <jsp:useBean id="errorMessage" scope="request" type="java.lang.String"/>
    <div>
        <h4><fmt:message key="error.label.message" bundle="${rb}" /></h4>
        <p>${errorMessage}</p>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
