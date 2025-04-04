<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 04/04/2025
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Events</title>
</head>
<body>

<a href="filter??">Filter???</a><br>

<c:forEach items="${events}" var="event">
  ${event.date} ${event.time} ${event.title} ${event.place.placeDetails.location} <a href="/event/${event.id}">Details</a><br>
</c:forEach>

<a href="events/map?">Show as map</a>
</body>
</html>
