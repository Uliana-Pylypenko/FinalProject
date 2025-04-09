<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place Details</title>
</head>
<body>
<c:if test="${empty userDTO}">
    <a href="/login">Log in</a><br>
    <a href="/register">Registration</a><br>
</c:if>

<c:if test="${userDTO.admin}">
    <a href="/admin/home">Admin profile</a><br>
</c:if>

<c:if test="${not empty userDTO && ! userDTO.admin}">
    <a href="/user/home">User profile</a><br>
</c:if>
<c:if test="${not empty userDTO}">
    <a href="/logout">Logout</a>
</c:if>

<a href="/">Back to home page</a><br>

<h1>${current_place.name}</h1>
<c:if test="${empty current_details}">
    No details provided<br>
    <c:if test="${current_place.userId == userDTO.id}">
        <a href="/place-details/add/${current_place.id}">Add details</a>
    </c:if>
</c:if>

<a href="/place/update/${current_place.id}">Update place</a>

${current_details.country}<br>
${current_details.location}<br>
${current_details.address}<br>
${current_details.activites}<br>
${current_details.description}<br>

<c:if test="${current_place.userId == userDTO.id && not empty current_place.detailsDTO}">
    Update details
</c:if>

<h2>Events</h2>

<c:forEach items="${current_events}" var="event">
  ${event.date} ${event.time} ${event.title} <a href="/event/${event.id}"></a> <br>
</c:forEach>

<c:if test="${empty current_events}">
  No events<br>
</c:if>

<!-- cos co będzie się rozwijać przy kliknięciu -->
<a href="/geoapi/places/${current_place.id}/leisure">Show other places nearby</a>


</body>
</html>
