<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 04/04/2025
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
<h1>Your profile</h1>
${userDTO.username}<br>
${userDTO.email}<br>
${userDTO.password}<br>
update<br>

<h2>Your places</h2>
<c:forEach items="${userPlaceDTOS}" var="place">
   ${place.name} ${place.detailsDTO.country}<br>
</c:forEach>


<h2>Your events</h2>
<c:forEach items="${userPlaceDTOS}" var="place">
  <c:forEach items="${place.eventDTOS}" var="event">
    ${event.date} ${event.time} ${event.title} <br>
  </c:forEach>
</c:forEach>
</body>
</html>
