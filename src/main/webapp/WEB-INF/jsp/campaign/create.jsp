<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="registration.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-light">
<%--<div class="container pt-3 my-3 border col-4">--%>
    <div class="container p-3 my-3 border bg-white shadow-sm">
        <h4 class="text-center"><fmt:message key="registration.title" bundle="${rb}" /></h4>
        <form action="registration.html" class="row g-2 needs-validation" novalidate method="post">
            <div class="col-md-12 position-relative">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
                <div class="container">
                    <p class="text-danger"><c:out value="${titleError}"/></p>
                </div>
            </div>

            <div class="col-md-12 position-relative">
                <label for="description">Description:</label>
                <textarea class="form-control" rows="5" id="description" name="description"></textarea>
            </div>
            <div class="col-md-12 position-relative">
                <label for="requirements">Requirements:</label>
                <textarea class="form-control" rows="5" id="requirements" name="description"></textarea>
            </div>

            <div class="d-flex justify-content-center col-md-6 mt-2 mb-1">
                <input id="beginDate" width="276" />
                <script>
                    $('#beginDate').datepicker({
                        uiLibrary: 'bootstrap4'
                    });
                </script>
            </div>
            <div class="d-flex justify-content-center col-md-6 mt-2 mb-1">
                <input id="endDate" width="276" />
                <script>
                    $('#endDate').datepicker({
                        uiLibrary: 'bootstrap4'
                    });
                </script>
            </div>
            <div class="col-md-6 d-flex justify-content-start">
                <label for="phoneNumber">Budget:</label>
                <input type="text" class="form-control" id="phoneNumber" placeholder="Enter phone number" name="phoneNumber" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>

            <div class="container">
                <button type="submit" class="btn btn-primary btn-block">Registration</button>
            </div>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
