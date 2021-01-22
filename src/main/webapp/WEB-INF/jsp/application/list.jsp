<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="application.list.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
<h3><fmt:message key="application.list.label.title" bundle="${rb}" /></h3>
<div class="container">
    <form action="action.html" method="post">
        <button type="submit" name="action" value="approve" class="btn btn-outline-success">
            <fmt:message key="application.list.table.button.approve" bundle="${rb}" />
        </button>
        <button type="submit" name="action" value="reject" class="btn btn-outline-danger">
            <fmt:message key="application.list.table.button.reject" bundle="${rb}" />
        </button>
        <table class="table table-striped">
            <thead>
            <tr>
                <th></th>
                <th><fmt:message key="application.list.table.header.id" bundle="${rb}" /></th>
                <th><fmt:message key="application.list.table.header.email" bundle="${rb}" /></th>
                <th><fmt:message key="application.list.table.header.role" bundle="${rb}" /></th>
                <th><fmt:message key="application.list.table.header.comment" bundle="${rb}" /></th>
                <th><fmt:message key="application.list.table.header.date" bundle="${rb}" /></th>
                <th><fmt:message key="application.list.table.header.user_info" bundle="${rb}" /></th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="applicationList" scope="request" type="java.util.List"/>
            <c:forEach var="application" items="${applicationList}">
                <tr>
                    <td><div class="form-check">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input" name="selected" value="${application.user.id}">
                        </label>
                    </div></td>
                    <td><c:out value="${application.userId}"/></td>
                    <td><c:out value="${application.user.email}"/></td>
                    <td><c:out value="${application.user.role}"/></td>
                    <td><c:out value="${application.comment}"/></td>
                    <td><fmt:formatDate type="both" value="${application.date.time}" dateStyle="medium" timeStyle="medium"/></td>
                    <td><c:out value="Тут должна быть ссылка на профиль"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
