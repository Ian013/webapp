<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
        <title>All students</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<c:if test="${not empty error}"><div>${error}</div></c:if>
<div class="container">
<table class="table table-hover">
    <c:forEach var="course" items="${courses}">
    <table class="table table-hover">
        <tr>
            <th>${course.name}</th>
            <th>${course.startDate} - ${course.endDate}</th>
        </tr>
        <c:forEach var="student" items="${course.users}">
            <tr>
                <td>${student.firstName} ${student.lastName}</td>

                <td><a href="users/deleteStudent/${student.id}">Delete</a></td>
                <td><a href="/addMark/${student.id}+${course.id}"></a> Mark</td>
            </tr>
        </c:forEach>
    </table>
    </c:forEach>
</table>
</div>
</body>
</html>