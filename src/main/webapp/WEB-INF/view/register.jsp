<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 08.12.2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="POST"
           action="/register"
           modelAttribute="userForm">
    <table>
        <tr>
            <td><form:label path="firstName">FirstName</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">lastName</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="email">
                email</form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="password">
                password</form:label></td>
            <td><form:input path="password"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
