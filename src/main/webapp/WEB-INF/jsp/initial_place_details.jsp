<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 04/04/2025
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place Details</title>
</head>
<body>
<h1>${placeDetails.place.name}</h1>
${placeDetails.country}<br>
${placeDetails.location}<br>
${placeDetails.address}<br>
${placeDetails.activites}<br>
${placeDetails.description}

<h2>Events</h2>

<c:forEach items="${placeDetails.place.events}" var="event">
  ${event.date} ${event.time} ${event.title} ${event.place.placeDetails.location}<br>
</c:forEach>

<c:if test="${empty placeDetails.place.events}">
  No events<br>
</c:if>

<!-- cos co będzie się rozwijać przy kliknięciu -->
<a href="/geoapi/places/${placeDetails.place.id}/leisure">Show other places nearby</a>


</body>
</html>
