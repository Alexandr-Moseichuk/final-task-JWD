<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>User list</title>
</head>
<body>
    <c:import url="/WEB-INF/jsp/menu.jsp"/>
    <h3>Список пользователей</h3>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Mail</th>
                    <th>Role</th>
                    <th>Registration date</th>
                    <th>Status</th>
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
                        <td><c:out value="${user.registrationDate.time}"/></td>
                        <td><c:out value="${user.status}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
