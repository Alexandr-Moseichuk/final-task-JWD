<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ cookie.language.value }" scope="session"/>
    <fmt:setBundle basename="localization.pagecontent" var="rb" />
    <title><fmt:message key="user.profile.title" bundle="${rb}" /></title>
    <c:import url="/WEB-INF/jsp/style.jsp"/>
</head>
<body class="bg-light">
    <c:import url="../particles/menu.jsp"/>
    <div class="container col-12 col-sm-6 mt-3">
        <div class="row  justify-content-center">
            <div class="col-4">
                <c:choose>
                    <c:when test="${empty user.userInfo.userFile.path}">
                        <img class="img-thumbnail img-fluid" src="../profile/icon-user.svg" alt="User photo"><br>
                    </c:when>
                    <c:otherwise>
                        <img class="img-thumbnail img-fluid" src="../${user.userInfo.userFile.path}" alt="User photo"><br>
                    </c:otherwise>
                </c:choose>

                <c:if test="${sessionScope.authorizedUser.id == user.id}">
                    <form action="upload_photo.html" method="post" enctype="multipart/form-data">
                        <input type="file" name="photo" size="100">
                        <input type="submit">
                    </form>
                </c:if>
            </div>
            <div class="col-8">
                <h3>${user.userInfo.lastName} <ctg:userRoleBadge userRole="${user.role}"/></h3>
                <h4>${user.userInfo.firstName} ${user.userInfo.secondName}</h4>
                <h4>${user.email}</h4>
            </div>
        </div>
        <dl class="row my-3">
            <dt class="col-2">
                Desc
            </dt>
            <dd class="col-10">
                <p>${user.userInfo.description}</p>
            </dd>
            <dt class="col-2">
                Phone
            </dt>
            <dd class="col-10">
                <h5>${user.userInfo.phoneNumber}</h5>
            </dd>
            <c:choose>
                <c:when test="${user.role eq 'INFLUENCER'}">
                    <dt class="col-2">
                        Manager
                    </dt>
                    <dd class="col-10">
                        <a href="<c:url value="/user/profile.html?userId=${manager.id}"/>">
                                ${manager.userInfo.lastName} ${manager.userInfo.firstName}
                        </a>
                    </dd>
                </c:when>
                <c:when test="${user.role eq 'MANAGER'}">
                    <dt class="col-2">
                        Influencers
                    </dt>
                    <dd class="col-10">
                        <c:forEach var="influencer" items="${influencerList}">
                            <a href="<c:url value="/user/profile.html?userId=${influencer.id}"/>">
                                    ${influencer.userInfo.lastName} ${influencer.userInfo.firstName}
                            </a>
                        </c:forEach>
                    </dd>
                </c:when>
            </c:choose>

        </dl>
    </div>
    <c:import url="/WEB-INF/jsp/particles/footer.jsp"/>
</body>
</html>
