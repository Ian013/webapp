<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Courses</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/showCourses.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajax.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dropdownStyle.css">
    <style>
        .navbar {
            margin-bottom: 50px;
            border-radius: 0;
        }
        .jumbotron {
            margin-bottom: 0;
        }
        footer {
            background-color: #f2f2f2;
            padding: 25px;
        }
    </style>
</head>
<body>

<div class="jumbotron">
    <div class="container text-center">
        <h1 id="ajax">COURSES</h1>
        <p>Sample text</p>
    </div>
</div>
<c:if test="${not empty error}"><div class="error"><p>${error}</p></div></c:if>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#" id="showAllCourses">Home</a></li>
                <li><a href="#actualCoursesTable" id="actualCourses">Actual Courses</a></li>
                <li><a href="#allCoursesTable" id="allCourses">All Courses</a></li>
                <sec:authorize access="hasAuthority('teacher')">
                    <li><a href="#teacherCoursesTable" id="coursesForTeacher">Courses For Teacher</a></li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('admin')">
                    <li><a href="#allStudentsTable" id="allStudents">AllStudents</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal==null}">
                    <li><a href="loginPage"><span class="glyphicon glyphicon-user"></span> Log in</a></li>
                    <li><a href="registerPage"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal!=null}">
                    <li><a href="#myCoursesTable" id="showMyCourses"><span class="glyphicon glyphicon-user"></span> My Courses</a></li>
                    <li>
                       <a href="javascript:document.getElementById('logout').submit()">Logout</a>
                    </li>
                    <c:url value="/logout" var="logoutUrl" />
                    <form id="logout" action="${logoutUrl}" method="post" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<sec:authorize access="hasAuthority('student')">
        <c:if test="${empty coursesForStudent}">
        <p>You haven't chosen any course yet</p>
    </c:if>
        <c:if test="${not empty coursesForStudent}">
            <div id ="myCoursesTable" class="container">
                <h1>MyCourses</h1>
                <table class="table table-hover">
                    <tr>
                    <th>Title</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Teacher</th>
                    <th>Delete cours</th>
                    <th>My marks</th>
                </tr>
                <c:forEach var="course" items="${coursesForStudent}">
                    <tr id ="courseTable">
                        <td>${course.name}</td>
                        <td>${course.startDate}</td>
                        <td>${course.endDate}</td>
                        <td>${course.teacher.firstName} ${course.teacher.lastName}</td>
                        <td><a href="/deleteMyCourse/${course.id}">Delete</a></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
            </div>
        </c:if>
</sec:authorize>
<sec:authorize access="hasAuthority('teacher')">
        <c:if test="${not empty coursesForTeacher}">
            <div id ="teacherCoursesTable" class="container">
                <h1>Courses(for teacher)</h1>
            <table class="table table-hover">
                <c:forEach var="course" items="${coursesForTeacher}">
                    <table class="table table-hover">
                        <tr>
                            <th>${course.name}</th>
                            <th>${course.startDate} - ${course.endDate}</th>
                        </tr>
                    <c:forEach var="student" items="${course.users}">
                        <tr>
                            <td>${student.firstName} ${student.lastName}</td>
                            <td><a href="users/deleteStudent/${student.id}">Delete</a></td>
                            <td>
                                <form method="post" action="markStudent">
                                <label>Mark
                                    <input type="number" name="mark"  required="required" min="0" max="10">
                                </label>
                                    <input type ="hidden" name="courseId" value="${course.id}">
                                    <input type="hidden" name="studentId" value="${student.id}">
                                <input type="submit" value="submit">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </c:forEach>
            </table>
        </div>
        </c:if>
</sec:authorize>
<div class="container" id="allCoursesTable">
    <div class="row">
        <h1>All courses</h1>
        <table class="table table-hover">
            <tr>
                <th>Title</th>
                <th>Start</th>
                <th>End</th>
                <th>Teacher</th>
            </tr>
        <c:forEach items="${courses}" var="course">
            <tr id ="courseTable">
                <td><a href="/addCourse/${course.id}">${course.name} <c:if test="${course.startDate<currentDate}">(Already started)</a></c:if></td>
                <td>${course.startDate}</td>
                <td>${course.endDate}</td>
                <td>${course.teacher.firstName} ${course.teacher.lastName}</td>
                <sec:authorize access="hasAuthority('admin')">
                    <td><a href="/deleteCourse/${course.id}">Delete</a></td> </sec:authorize>
            </tr>
        </c:forEach>
        </table>
            <sec:authorize access="hasAuthority('admin')">
                <form action="${pageContext.request.contextPath}/addNewCourse" method="POST">
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
            </sec:authorize>
    </div>
    </div>
    <div class = "container" id="actualCoursesTable">
        <div class="row">
            <h1>Actual courses</h1>
            <table class="table table-hover">
                <tr>
                    <th>Title</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Teacher</th>
                </tr>
                <c:forEach items="${courses}" var="course">
                    <c:if test="${course.startDate>currentDate}">
                    <tr id ="courseTable">
                        <td><a href="/addCourse/${course.id}">${course.name}</a></td>
                        <td>${course.startDate}</td>
                        <td>${course.endDate}</td>
                        <td>${course.teacher.firstName} ${course.teacher.lastName}</td>
                    </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
<sec:authorize access="hasAuthority('admin')">
    <div class="container" id="allStudentsTable">
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
                    <td>{student.marks}</td>
                </tr>
            </c:forEach>
        </table>
        </c:forEach>
    </div>
</sec:authorize>
<br>
<br>

<footer class="container-fluid text-center">
    <p>Footer</p>
</footer>

</body>
</html>
