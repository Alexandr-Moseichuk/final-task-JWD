<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.01.2021
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not approved</title>
</head>
<body>
<form action="../application/update.html" method="post">
    <div class="form-group">
        <%--            <label for="email">Email:</label>--%>
        <textarea class="form-control" placeholder="Comment" id="comment" name="comment"></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Обновить</button>
    <p class="text-danger"><c:out value="${updateFeedback}"/></p>
</form>
</body>
</html>

