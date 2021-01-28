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

    <script src="https://unpkg.com/gijgo@1.9.13/js/messages/messages.ru-ru.js" type="text/javascript"></script>
</head>
<body class="bg-light">
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <div class="container p-3 my-3 border bg-white shadow-sm">
        <h4 class="text-center"><fmt:message key="registration.title" bundle="${rb}" /></h4>
        <form action="create.html" class="row g-2 needs-validation" id="create-campaign" novalidate method="post">
            <div class="form-group col-md-12 position-relative">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
                <div class="container">
                    <p class="text-danger"><c:out value="${titleError}"/></p>
                </div>
            </div>
            <div class="form-group col-md-12 position-relative">
                <label for="description">Description:</label>
                <textarea class="form-control" rows="5" id="description" name="description"></textarea>
                <div class="container">
                    <p class="text-danger"><c:out value="${descriptionError}"/></p>
                </div>
            </div>
            <div class="form-group col-md-12 position-relative">
                <label for="requirement">Requirement:</label>
                <textarea class="form-control" rows="5" id="requirement" name="requirement"></textarea>
                <div class="container">
                    <p class="text-danger"><c:out value="${requirementError}"/></p>
                </div>
            </div>
            <div class="form-group d-flex justify-content-center col-md-6">
                <label for="beginDate">Begin date:</label>
                <input id="beginDate" name="beginDate" width="200" readonly>
            </div>
            <div class= "form-group d-flex justify-content-center col-md-6">
                <label for="endDate">End date:</label>
                <input id="endDate" name="endDate" width="200" readonly>
            </div>
            <div class="container">
                <p class="text-danger"><c:out value="${dateError}"/></p>
            </div>
            <div class="form-group col-md-12">
                <label for="budget">Budget:</label>
                <input type="text" class="form-control" id="budget" placeholder="Enter budget" name="budget" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
                <div class="container">
                    <p class="text-danger"><c:out value="${budgetError}"/></p>
                </div>
            </div>
            <div class="form-group col-md-12 d-flex justify-content-start">
                <input type = "file" name = "uploadFile" size = "100"/>
            </div>
        </form>
        <div class="container">
            <button type="submit" class="btn btn-primary btn-block" form="create-campaign">Registration</button>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>

    <script>
        function f2c(x) {
            return String(x).toLowerCase().replace(/_/, '-');
        }
        let locale = f2c('${ cookie.language.value }');

        $('#beginDate').datepicker({
            locale: locale,
            format: 'dd.mm.yyyy',
            uiLibrary: 'bootstrap4'
        });
        $('#endDate').datepicker({
            locale: locale,
            format: 'dd.mm.yyyy',
            uiLibrary: 'bootstrap4'
        });
    </script>
</body>
</html>
