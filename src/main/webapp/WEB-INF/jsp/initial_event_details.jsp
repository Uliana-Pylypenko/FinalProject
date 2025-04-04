<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 04/04/2025
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Event details</title>
</head>
<body>
<h1>${event.title}</h1>
${event.date} ${event.time}<br>
${event.place.name} - ${event.place.placeDetails.country}, ${event.place.placeDetails.location}, ${event.place.placeDetails.address}<br>
${event.description}<br>

<h3>Other events in this place</h3>
<c:forEach items="${event.place.events}" var="event">
  ${event.date} ${event.time} ${event.title} ${event.place.placeDetails.location}<br>
</c:forEach>

<c:if test="${empty event.place.events}">
  No events<br>
</c:if>

</body>
</html>
