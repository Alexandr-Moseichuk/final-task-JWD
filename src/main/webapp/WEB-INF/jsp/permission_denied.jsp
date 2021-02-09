<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="permission_denied.title" bundle="${ rb }" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body class="bg-light">
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <div class="d-flex justify-content-center">
        <h3><fmt:message key="permission_denied.title" bundle="${ rb }" /></h3>
    </div>
    <div class="d-flex justify-content-center">
        <div class="row">
            <a href="<c:url value='/' />"><fmt:message key="permission_denied.link.index" bundle="${ rb }" /></a>
            <a href="<c:url value='/logout.html'/>" ><fmt:message key="permission_denied.link.exit" bundle="${ rb }" /></a>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
