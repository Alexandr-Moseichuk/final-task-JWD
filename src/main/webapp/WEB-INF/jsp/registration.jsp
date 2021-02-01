<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.12.2020
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="registration.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body class="bg-light">
<%--<div class="container pt-3 my-3 border col-4">--%>
    <div class="container p-3 my-3 border bg-white shadow-sm">
        <h4 class="text-center"><fmt:message key="registration.title" bundle="${rb}" /></h4>
        <form action="registration.html" class="row g-2 form" method="post">
            <div class="col-md-12 position-relative">
                <label for="email"><fmt:message key="registration.form.label.email" bundle="${rb}" /></label>
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" oninput="validateEmail(this)">
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.email" bundle="${rb}" /></div>
                <div class="container">
                    <p id="emailError" class="text-danger"><c:out value="${emailError}"/></p>
                </div>
            </div>

            <div class="col-md-6 position-relative">
                <label for="password"><fmt:message key="registration.form.label.password" bundle="${rb}" /></label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" oninput="validatePassword(this)">
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.password" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${passwordError}"/></label>
            </div>
            <div class="col-md-6 position-relative">
                <label for="passwordCheck"><fmt:message key="registration.form.label.password_check" bundle="${rb}" /></label>
                <input type="password" class="form-control" id="passwordCheck" placeholder="Enter password check" name="passwordCheck" oninput="validateCheckPassword(this)">
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.check_password" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${passwordCheckError}"/></label>
            </div>
            <div class="col-md-12 position-relative">
                <label for="lastName"><fmt:message key="registration.form.label.last_name" bundle="${rb}" /></label>
                <input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName" oninput="validateFullName(this)">
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.full_name" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${lastNameError}"/></label>
            </div>
            <div class="col-md-6 position-relative">
                <label for="firstName"><fmt:message key="registration.form.label.first_name" bundle="${rb}" /></label>
                <input type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName" oninput="validateFullName(this)">
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.full_name" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${firstNameError}"/></label>
            </div>
            <div class="col-md-6 position-relative">
                <label for="secondName"><fmt:message key="registration.form.label.second_name" bundle="${rb}" /></label>
                <input type="text" class="form-control" id="secondName" placeholder="Enter second name" name="secondName" oninput="validateFullName(this)">
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.full_name" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${secondNameError}"/></label>
            </div>
            <div class="col-md-12 position-relative">
                <label for="userRole"><fmt:message key="registration.form.label.role" bundle="${rb}" /></label>
                <select id="userRole" name="userRole" class="custom-select" aria-label="Default select example">
                    <option selected value="1"><fmt:message key="registration.form.role.advertiser" bundle="${rb}" /></option>
                    <option value="2"><fmt:message key="registration.form.role.influencer" bundle="${rb}" /></option>
                    <option value="3"><fmt:message key="registration.form.role.manager" bundle="${rb}" /></option>
                </select>
            </div>
            <div class="col-md-12 position-relative">
                <label for="description"><fmt:message key="registration.form.label.description" bundle="${rb}" /></label>
                <textarea class="form-control" rows="5" id="description" name="description" oninput="validateDescription(this)"></textarea>
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.description" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${descriptionError}"/></label>
            </div>
            <div class="col-md-12 position-relative">
                <label for="phoneNumber"><fmt:message key="registration.form.label.phone" bundle="${rb}" /></label>
                <input type="text" class="form-control" id="phoneNumber" placeholder="Enter phone number" name="phoneNumber" oninput="validatePhoneNumber(this)">
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.phone" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${phoneError}"/></label>
            </div>
            <div class="col-md-12 position-relative">
                <label for="comment"><fmt:message key="registration.form.label.comment" bundle="${rb}" /></label>
                <textarea class="form-control" rows="5" id="comment" name="comment" oninput="validateComment(this)"></textarea>
                <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.comment" bundle="${rb}" /></div>
                <label class="text-danger"><c:out value="${commentError}"/></label>
            </div>
            <div class="col-md-12 ml-4 mt-2 mb-2 form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember"><fmt:message key="registration.form.label.checkbox" bundle="${rb}" />
                    <div class="valid-feedback"><fmt:message key="registration.feedback.valid" bundle="${rb}" /></div>
                    <div class="invalid-feedback"><fmt:message key="registration.feedback.invalid.checkbox" bundle="${rb}" /></div>
                </label>
            </div>
            <div class="container">
                <button type="submit" class="btn btn-primary btn-block"><fmt:message key="registration.form.submit_button" bundle="${rb}" /></button>
            </div>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
    <script src=<c:url value="/js/registration-form-validation.js"/>>
    </script>
</body>
</html>
