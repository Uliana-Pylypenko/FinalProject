<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add place</title>
</head>
<body>
<h1>Add place</h1>
<form action="" method="post">
  Name
  <input type="text" name="name" placeholder="Name" value="${current_place.name}"><br>
  Latitude
  <input type="text" name="latitude" placeholder="Latitude" value="${current_place.latitude}"><br>
  Longitude
  <input type="text" name="longitude" placeholder="Longitude" value="${current_place.longitude}"><br>
  Category<br>
  <select name="category">
    <option value="">- Category -</option>
  <c:forEach items="${categories}" var="category">
    <option value="${category.id}">${category.name}</option>
  </c:forEach>
  </select>
<%--  <c:forEach items="${categories}" var="category">--%>
<%--    ${category.name}--%>
<%--    <input type="radio" name="category" value="${category.id}"><br>--%>
<%--  </c:forEach>--%>
  <button type="submit">Save</button><br>
  ${error_message}
</form>
</body>
</html>
