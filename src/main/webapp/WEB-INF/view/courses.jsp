<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
<p> click</p>
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
        </c:forEach>
    </table>
</table>
<p><a href="add-course">Add course</a></p>

<!-- -->
<form action="${pageContext.request.contextPath}/courses" method="POST">
    <label>Title</label>
    <label>
        <input type="text" name="title">
    </label>
    <label>Start</label>
    <label>
        <input type="date" name="startDate">
    </label>
    <label>End</label>
    <label>
        <input type="date" name="endDate">
    </label>
    <label>Teacher</label>

        <c:forEach var="teacher" items="${teachers}">
            <label>
                <input type="radio"  name="teacher" value="${teacher.id}">
                 ${teacher.firstName} ${teacher.lastName}
            </label>
        </c:forEach>
        <input type="submit" value="submit">

</form>

</body>
</html>