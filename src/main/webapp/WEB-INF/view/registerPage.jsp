<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>



<div class="container">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign up</h3>
                </div>
                <div class="panel-body">
                    <form:form method="POST"
                     action="/register"
                      modelAttribute="userForm">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                      <form:label path="firstName">FirstName</form:label>
                                        <form:input path="firstName"/></div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <form:label path="lastName">lastName</form:label>
                                    <form:input path="lastName"/></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="email">email</form:label>
                            <form:input path="email"/>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <form:label path="password">Password</form:label>
                                    <form:input path="password"/>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="passwordConfirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password">
                                </div>
                            </div>
                        </div>
                        <input  type="submit" value="submit" class="btn btn-info btn-block">
                </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="deprecated/footer.jsp" %>

