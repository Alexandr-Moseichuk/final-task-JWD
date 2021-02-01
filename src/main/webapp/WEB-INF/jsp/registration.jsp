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
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" oninput="validateEmail(this)">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
                <div class="container">
                    <p id="emailError" class="text-danger"><c:out value="${emailError}"/></p>
                </div>
            </div>

            <div class="col-md-6 position-relative">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" oninput="validatePassword(this)">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
                <div class="container">
                    <p class="text-danger"><c:out value="${passwordError}"/></p>
                </div>
            </div>
            <div class="col-md-6 position-relative">
                <label for="passwordCheck">Password check:</label>
                <input type="password" class="form-control" id="passwordCheck" placeholder="Enter password check" name="passwordCheck">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
                <div class="container">
                    <p class="text-danger"><c:out value="${passwordCheckError}"/></p>
                </div>
            </div>
            <div class="col-md-12 position-relative">
                <label for="lastName">Last name:</label>
                <input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName" oninput="validateFullName(this)">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="col-md-6 position-relative">
                <label for="firstName">First name:</label>
                <input type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName" oninput="validateFullName(this)">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="col-md-6 position-relative">
                <label for="secondName">Second name:</label>
                <input type="text" class="form-control" id="secondName" placeholder="Enter second name" name="secondName" oninput="validateFullName(this)">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="col-md-12 position-relative">
                <label for="userRole">Выберите роль:</label>
                <select id="userRole" name="userRole" class="custom-select" aria-label="Default select example">
                    <option selected value="1">Рекламодатель</option>
                    <option value="2">Инфлюенсер</option>
                    <option value="3">Менеджер</option>
                </select>
            </div>
            <div class="col-md-12 position-relative">
                <label for="description">Description:</label>
                <textarea class="form-control" rows="5" id="description" name="description" oninput="validateDescription(this)"></textarea>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="col-md-12 position-relative">
                <label for="phoneNumber">Phone number:</label>
                <input type="text" class="form-control" id="phoneNumber" placeholder="Enter phone number" name="phoneNumber" oninput="validatePhoneNumber(this)">
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="col-md-12 position-relative">
                <label for="comment">Comment for administrator:</label>
                <textarea class="form-control" rows="5" id="comment" name="comment" oninput="validateComment(this)"></textarea>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="col-md-12 ml-4 mt-2 mb-2 form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember"> I agree on blabla.
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Check this checkbox to continue.</div>
                </label>
            </div>
            <div class="container">
                <button type="submit" class="btn btn-primary btn-block">Registration</button>
            </div>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
    <script src=<c:url value="/js/registration-form-validation.js"/>>
    </script>
</body>
</html>
