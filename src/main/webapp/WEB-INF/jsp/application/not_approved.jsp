<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not approved</title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body class="bg-light">
    <div class="d-flex justify-content-center">
        <h3>Ваша заявка еще не одобрена!</h3>
    </div>
    <div class="d-flex justify-content-center">
        <div class="col-sm-6">
            <form action="application/update.html" method="post">
                <div class="form-group">
                    <textarea class="form-control" placeholder="Comment" id="comment" name="comment"><c:out value="${applicationComment}"/></textarea>
                </div>
                <p class="text-danger"><c:out value="${updateFeedback}"/></p>
                <button type="submit" class="btn btn-primary">Обновить</button>
                <a href="<c:url value='/logout.html'/>">Выход</a>

            </form>
            <form action="application/delete.html" method="post">
                <button type="submit" class="btn btn-primary">Удалить заявку</button>
            </form>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>

