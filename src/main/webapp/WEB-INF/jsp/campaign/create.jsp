<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="campaign.create.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

    <script src="https://unpkg.com/gijgo@1.9.13/js/messages/messages.ru-ru.js" type="text/javascript"></script>
</head>
<body class="bg-light">
    <c:import url="/WEB-INF/jsp/particles/menu.jsp"/>
    <div class="container p-3 my-3 border bg-white shadow-sm">
        <h4 class="text-center"><fmt:message key="campaign.create.title" bundle="${rb}" /></h4>
        <form action="create.html" class="row g-2 needs-validation" id="create-campaign" novalidate method="post">
            <div class="form-group col-md-12 position-relative">
                <label for="title"><fmt:message key="campaign.create.label.title" bundle="${rb}" /></label>
                <input type="text" class="form-control" id="title" placeholder="<fmt:message key="campaign.create.placeholder.title" bundle="${rb}" />" name="title" required>
                <div class="valid-feedback"><fmt:message key="form.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="form.feedback.empty" bundle="${rb}" /></div>
                <p class="text-danger"><c:out value="${titleError}"/></p>
            </div>
            <div class="form-group col-md-12 position-relative">
                <label for="description"><fmt:message key="campaign.create.label.description" bundle="${rb}" /></label>
                <textarea class="form-control" rows="5" id="description" name="description" placeholder="<fmt:message key="campaign.create.placeholder.description" bundle="${rb}" />"></textarea>
                <div class="valid-feedback"><fmt:message key="form.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="form.feedback.empty" bundle="${rb}" /></div>
                <p class="text-danger"><c:out value="${descriptionError}"/></p>
            </div>
            <div class="form-group col-md-12 position-relative">
                <label for="requirement"><fmt:message key="campaign.create.label.requirement" bundle="${rb}" /></label>
                <textarea class="form-control" rows="5" id="requirement" name="requirement" placeholder="<fmt:message key="campaign.create.placeholder.requirement" bundle="${rb}" />"></textarea>
                <div class="valid-feedback"><fmt:message key="form.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="form.feedback.empty" bundle="${rb}" /></div>
                <p class="text-danger"><c:out value="${requirementError}"/></p>
            </div>
            <div class="form-group d-flex justify-content-center col-md-6">
                <label for="beginDate"><fmt:message key="campaign.create.label.begin_date" bundle="${rb}" /></label>
                <input id="beginDate" name="beginDate" width="200" readonly>
                <div class="valid-feedback"><fmt:message key="form.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="form.feedback.empty" bundle="${rb}" /></div>
            </div>
            <div class= "form-group d-flex justify-content-center col-md-6">
                <label for="endDate"><fmt:message key="campaign.create.label.end_date" bundle="${rb}" /></label>
                <input id="endDate" name="endDate" width="200" readonly>
                <div class="valid-feedback"><fmt:message key="form.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="form.feedback.empty" bundle="${rb}" /></div>
            </div>
            <p class="text-danger"><c:out value="${dateError}"/></p>
            <div class="form-group col-md-12">
                <label for="budget"><fmt:message key="campaign.create.label.budget" bundle="${rb}" /></label>
                <input type="text" class="form-control" id="budget" name="budget" placeholder="<fmt:message key="campaign.create.placeholder.budget" bundle="${rb}" />" required>
                <div class="valid-feedback"><fmt:message key="form.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="form.feedback.empty" bundle="${rb}" /></div>
                <p class="text-danger"><c:out value="${budgetError}"/></p>
            </div>
<%--            <div class="form-group col-md-12 d-flex justify-content-start">--%>
<%--                <form method="post" enctype="multipart/form-data">--%>
<%--                    <label for="file_upload"><fmt:message key="campaign.create.label.file_upload" bundle="${rb}" /></label>--%>
<%--                    <input id="file_upload" type="file" name="uploadFile" size="100"/>--%>
<%--                </form>--%>
<%--            </div>--%>
            <input form="create-campaign" type="hidden" name="advertiserId" value="${sessionScope.authorizedUser.id}"/>
        </form>
        <div class="container">
            <button type="submit" class="btn btn-primary btn-block" form="create-campaign"><fmt:message key="campaign.create.form.submit_button" bundle="${rb}" /></button>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>

    <script>
        function f2c(x) {
            return String(x).toLowerCase().replace(/_/, '-');
        }
        let locale = f2c('${ cookie.language.value }');

        if (locale.length === 0) {
            locale = 'ru-ru';
        }

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
