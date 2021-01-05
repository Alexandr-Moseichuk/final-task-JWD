<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.12.2020
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <c:import url="style.jsp"/>
</head>
<body>
Регистрация происходит тут
<div class="container pt-3 my-3 border col-sm-4">

    <form action="registration" class="was-validated" method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="passwordCheck">Password check:</label>
            <input type="password" class="form-control" id="passwordCheck" placeholder="Enter password check" name="passwordCheck" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="lastName">Last name:</label>
            <input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="firstName">First name:</label>
            <input type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="secondName">Second name:</label>
            <input type="text" class="form-control" id="secondName" placeholder="Enter second name" name="secondName" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" rows="5" id="description" name="description"></textarea>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone number:</label>
            <input type="text" class="form-control" id="phoneNumber" placeholder="Enter phone number" name="phoneNumber" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="remember" required> I agree on blabla.
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Check this checkbox to continue.</div>
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Registration</button>
    </form>
</div>
</body>
</html>