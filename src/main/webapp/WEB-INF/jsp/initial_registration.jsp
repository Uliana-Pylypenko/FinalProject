<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 09/04/2025
  Time: 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="" method="post">
  Username
  <input type="text" name="username" placeholder="Username"><br>
  Email
  <input type="email" name="email" placeholder="Email"><br>
  Password
  <input type="password" name="password" placeholder="Password"><br>
  Repeat password
  <input type="password" name="password_check" placeholder="Repeat password"><br>
  <button type="submit">Register</button><br>

  ${error_message}
</form>
</body>
</html>
