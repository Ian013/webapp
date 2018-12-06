<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="WEB-INF/resources/javascript/loginPageScript.js"></script>
    <title>Title</title>
</head>
<body onload='document.loginForm.username.focus();'>
<h3>Login</h3>

<c:if test="${not empty error}"><div>${error}</div></c:if>
<c:if test="${not empty message}"><div>${message}</div></c:if>
<form name='login' action="<c:url value='/loginPage' />" method='POST'>
 <table>
        <tr>
            <td>UserName:</td>
            <td>
                <label>
                    <input type='text' name='username'>
                </label>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <label>
                    <input type='password' name='password' />
                </label>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form> <!--
  <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <form name='login' action="<c:url value='/loginPage' />" method='POST'>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name='username'>
                        </div>
                        <div class="form-group">
                            <label >Password</label>
                            <input type="password" class="form-control" name='password' id="password" placeholder="Password">
                        </div>
                        <div class="form-check">
                            <button class="btn btn-info" type="button" name="showpassword" id="showpassword" value="Show Password">Show password</button>
                            <button type="submit" name="submit" value="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
    </div>
-->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</body>
</html>