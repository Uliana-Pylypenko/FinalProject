<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Places</title>
</head>
<body>

<a href="filter??">Filter???</a><br>

<c:forEach items="${places}" var="place">
  ${place.category.name} ${place.name} <a href="/place-details/place-id/${place.id}">Details</a><br>
</c:forEach>

<a href="places/map?">Show as map</a>
</body>
</html>
