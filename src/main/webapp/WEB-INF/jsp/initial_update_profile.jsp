<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 08/04/2025
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update profile</title>
</head>
<body>
<h1>Update profile</h1>
<form action="" method="post">
    Username
    <input type="text" value="${userDTO.username}" name="username" placeholder="Username"><br>
    Email2
    <input type="email" value="${userDTO.email}" name="email" placeholder="Email"><br>
    Password
    <input type="password" name="password" placeholder="Password"><br>
    <button type="submit">Save changes</button>
</form>
</body>
</html>
