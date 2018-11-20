<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<table>
    <table>
        <tr>
            <th>Name</th>
            <th>Courses</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.firstName} ${student.lastName}</td>
                <td>${student.courses}</td>
                <td><a href="/delete/${student.id}">Delete</a></td>
            </tr>
            </tr>
        </c:forEach>
    </table>
</table>
<p><a href="add-course">Add course</a> </p>

</body>
</html>