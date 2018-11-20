<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 19.11.2018
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/add-course" method="POST">
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
    <label>
        <input type="number" name="teacherLastName">
    </label>

    <input type="submit" value="submit">
</form>
</body>
</html>
