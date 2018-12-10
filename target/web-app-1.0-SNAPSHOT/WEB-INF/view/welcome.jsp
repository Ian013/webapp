<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 30.10.2018
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Welcome page</title>
    <link href="<c:url value="/WEB-INF/resources/style.css"/>" rel="stylesheet">
</head>
    <body>
        <p>Hello, ${pageContext.request.userPrincipal}</p>
        <p> <a href="courses">courses</a></p>
        <p> <a href="users">users</a> </p>
        <p>
            <c:if test="${pageContext.request.userPrincipal==null}">
                <a href="registerPage">register new user</a>
            </c:if>
        </p>
    </body>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('admin')}">
    <a href="users">ADMIN LINK!</a>
</c:if>
</html>
