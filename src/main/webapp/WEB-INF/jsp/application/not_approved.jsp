<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not approved</title>
</head>
<body class="bg-light">
    <form action="application/update.html" method="post">
        <div class="form-group">
            <textarea class="form-control" placeholder="Comment" id="comment" name="comment"><c:out value="${applicationComment}"/></textarea>
        </div>
        <p class="text-danger"><c:out value="${updateFeedback}"/></p>
        <button type="submit" class="btn btn-primary">Обновить</button>
        <a href="<c:url value='/logout.html'/>">Выход</a>

    </form>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>

