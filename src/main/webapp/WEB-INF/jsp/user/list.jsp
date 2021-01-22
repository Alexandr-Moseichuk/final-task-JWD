<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: digge
  Date: 1/2/2021
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="user.list.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body>
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <h3><fmt:message key="user.list.label.title" bundle="${rb}" /></h3>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th></th>
                    <th><fmt:message key="user.list.table.header.id" bundle="${rb}" /></th>
                    <th><fmt:message key="user.list.table.header.email" bundle="${rb}" /></th>
                    <th><fmt:message key="user.list.table.header.role" bundle="${rb}" /></th>
                    <th><fmt:message key="user.list.table.header.registration_date" bundle="${rb}" /></th>
                    <th><fmt:message key="user.list.table.header.status" bundle="${rb}" /></th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="userList" scope="request" type="java.util.List"/>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td><div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" value="">
                            </label>
                        </div></td>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.role}"/></td>
                        <td><fmt:formatDate type="both" value="${user.registrationDate.time}" dateStyle="medium" timeStyle="medium"/></td>
                        <td><c:out value="${user.status}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
