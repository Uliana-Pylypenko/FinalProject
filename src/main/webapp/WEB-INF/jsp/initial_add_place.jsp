<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 08/04/2025
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add place</title>
</head>
<body>
<h1>Add place</h1>
<form action="" method="post">
  Name
  <input type="text" name="name" placeholder="Name"><br>
  Latitude
  <input type="text" name="latitude" placeholder="Latitude"><br>
  Longitude
  <input type="text" name="longitude" placeholder="Longitude"><br>
  Category<br>
  <c:forEach items="${categories}" var="category">
    ${category.name}
    <input type="radio" name="category" value="${category.id}"><br>
  </c:forEach>
  <button type="submit">Save</button>
</form>
</body>
</html>
