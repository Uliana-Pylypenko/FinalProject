<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 04/04/2025
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome</h1>
<c:if test="${empty userDTO}">
    <a href="/login">Log in</a><br>
    <a href="/register">Registration</a><br>
</c:if>

<c:if test="${userDTO.admin}">
    <a href="/admin/home">Admin profile</a><br>
</c:if>

<c:if test="${not empty userDTO && ! userDTO.admin}">
    <a href="/user/home">User profile</a><br>
</c:if>

<a href="/place">Places</a><br>
<a href="/event">Events</a><br>

<c:if test="${not empty userDTO}">
    <a href="/logout">Logout</a>
</c:if>
</body>
</html>
