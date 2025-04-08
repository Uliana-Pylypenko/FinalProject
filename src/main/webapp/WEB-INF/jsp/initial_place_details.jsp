<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place Details</title>
</head>
<body>

<h1>${current_place.name}</h1>
<c:if test="${empty current_details}">
    No details provided<br>
    <c:if test="${current_place.userId == userDTO.id}">
        Add details
    </c:if>
</c:if>

${current_details.country}<br>
${current_details.location}<br>
${current_details.address}<br>
${current_details.activites}<br>
${current_details.description}<br>

<c:if test="${current_place.userId == userDTO.id}">
    Update details
</c:if>

<h2>Events</h2>

<c:forEach items="${current_events}" var="event">
  ${event.date} ${event.time} ${event.title} <br>
</c:forEach>

<c:if test="${empty current_events}">
  No events<br>
</c:if>

<!-- cos co będzie się rozwijać przy kliknięciu -->
<a href="/geoapi/places/${current_place.id}/leisure">Show other places nearby</a>


</body>
</html>
