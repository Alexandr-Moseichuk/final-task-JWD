<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: digge
  Date: 12/31/2020
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADLinker</title>
    <c:import url="style.jsp"/>
</head>
<body>
    <div class="container pt-3 my-3">
        <div class="row">
            <div class="col-md-8">
                <img class="img-fluid" src="img/welcome_image.jpeg" class="rounded" alt="Welcome image">
            </div>
            <div class="col-md-4">
                <div class="container pt-3 my-3 border">
                    <c:import url="particles/login_form.jsp"/>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
