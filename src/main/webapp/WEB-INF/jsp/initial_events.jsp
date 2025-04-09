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

<a href="/">Back to home page</a><br>

<form action="" method="get">
    Start date
    <input type="date" name="start_date" value="${filter_start_date}">
    End date
    <input type="date" name="end_date" value="${filter_end_date}"><br>
    Country
    <input type="text" name="country" value="${filter_country}"><br>
    City
    <input type="text" name="city" value="${filter_city}"><br>
    <button type="submit">Filter</button>
</form>

<c:if test="${empty userDTO}">
    <a href="/login">Login or register to add a new event</a><br>
</c:if>

<c:if test="${not empty userDTO}">
    <a href="event/create">Add new event</a><br>
</c:if>

<c:forEach items="${events}" var="event">
  ${event.date} ${event.time} ${event.title} <a href="/event/${event.id}">Details</a><br>
</c:forEach>

<a href="events/map?">Show as map</a>

</body>
</html>
