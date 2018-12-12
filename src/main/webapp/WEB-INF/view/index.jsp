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
    <script src="${pageContext.request.contextPath}/js/getCoursesForStudent.js"></script>
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
        <h1>COURSES</h1>
        <p>Sample text</p>
    </div>
</div>

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
                <li class="active"><a href="#">Home</a></li>
                <li><a href="courses">All courses</a></li>
                <sec:authorize access="hasAuthority('admin')">
                    <li><a href="users">Users</a></li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('student')">
                    <li><a href="${pageContext.request.contextPath}/showCoursesForStudent">My Courses</a></li>
                </sec:authorize>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal==null}">
                    <li><a href="loginPage"><span class="glyphicon glyphicon-user"></span> Log in</a></li>
                    <li><a href="registerPage"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal!=null}">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
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
<div class="container">
    <div class="row">
        <c:forEach items="${courses}" var="course">
        <div class="col-sm-4">
            <div class="panel panel-primary">
                <div class="panel-heading">${course.name}</div>
                <div class="panel-body">{course.description}</div>
                <div class="panel-footer">
                    <a href="/addCourse/${course.id}">Add</a>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div><br>
<br>
<sec:authorize access="hasAuthority('student')">

<div id ="myCoursesTable">
    <h1>MyCourses</h1>
    <table>
        <tr>
            <th>Title</th>
            <th>Start</th>
            <th>End</th>
            <th>Teacher</th>
        </tr>
        <c:forEach var="course" items="${userCourses}">
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
</sec:authorize>

<footer class="container-fluid text-center">
    <p>Footer</p>
    <form class="form-inline">Get deals:
        <button type="button" class="btn btn-danger" id="showMyCourses">My Courses</button>
    </form>
</footer>

</body>
</html>
