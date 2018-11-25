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
            <th>Title</th>
            <th>Start</th>
            <th>End</th>
            <th>Teacher</th>
        </tr>
        <c:forEach var="course" items="${courses}">
            <tr id ="courseTable">
                <td>${course.name}</td>
                <td>${course.startDate}</td>
                <td>${course.endDate}</td>
                <td>${course.teacher.firstName} ${course.teacher.lastName}</td>
                <td><a href="/delete/${course.id}">Delete</a></td>
                </tr>
            </tr>
        </c:forEach>
    </table>
</table>
<p><a href="add-course">Add course</a> </p>

</body>
</html>