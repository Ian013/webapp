<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<c:if test="${not empty error}"><div>${error}</div></c:if>

<table>
    <c:forEach var="course" items="${courses}">
    <table>
        <tr>
            <th>Name</th>
            <th></th>
            <th>Sample text</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.firstName} ${student.lastName}</td>
                <td><a href="/delete/${student.id}">Delete</a></td>
                <td><a href="/addMark/${student.id}"></a> </td>
            </tr>
        </c:forEach>
    </table>
    </c:forEach>
</table>

</body>
</html>