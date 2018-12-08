<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <p>Hello, ${pageContext.request.userPrincipal}</p>
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
                <c:if test="${pageContext.request.isUserInRole('admin')}">
                    <li><a href="users">Users</a></li>
                </c:if>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal==null}">
                    <li><a href="loginPage"><span class="glyphicon glyphicon-user"></span> Log in</a></li>
                    <li><a href="registerPage"><span class="glyphicon glyphicon-shopping-cart"></span> Sign up</a></li>
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
                <div class="panel-footer">sample text</div>
            </div>
        </div>
        </c:forEach>
    </div>
</div><br><br>

<footer class="container-fluid text-center">
    <p>Footer</p>
    <form class="form-inline">Get deals:
        <input type="email" class="form-control" size="50" placeholder="Email Address">
        <button type="button" class="btn btn-danger">Sign Up</button>
    </form>
</footer>

</body>
</html>
