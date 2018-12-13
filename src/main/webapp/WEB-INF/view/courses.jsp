<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dropdownStyle.css">
</head>
<body>
<sec:authorize access="hasAuthority('admin')">
    <div class="container">
    <h1>All courses</h1>

    <table class="table table-hover">
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
                    <td><a href="/deleteCourse/${course.id}">Delete</a></td>
                </tr>
            </c:forEach>
    </table>

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

        <div class="dropdown">
            <button class="dropbtn">Teachers</button>
            <div class="dropdown-content">
                <c:forEach var="teacher" items="${teachers}">
                    <label>
                        <input type="radio"  name="teacher" value="${teacher.id}">
                            ${teacher.firstName} ${teacher.lastName}
                    </label>
                </c:forEach>
            </div>
        </div>
        <input type="submit" value="submit">
    </form>
    </div>
</sec:authorize>


<div class="container">
    <h1>My courses</h1>
    <c:if test="${not empty error}"><h3>${error}</h3></c:if>
<table class="table table-hover">
        <tr>
            <th>Title</th>
            <th>Start</th>
            <th>End</th>
            <th>Teacher</th>
        </tr>
        <c:forEach var="course" items="${myCourses}">
            <tr id ="courseTable">
                <td>${course.name}</td>
                <td>${course.startDate}</td>
                <td>${course.endDate}</td>
                <td>${course.teacher.firstName} ${course.teacher.lastName}</td>
                <td><a href="/deleteMyCourse/${course.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>