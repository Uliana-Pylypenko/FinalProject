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
<a href="/user/update/${userDTO.id}">Update profile</a><br>
<a href="/">Home page</a>

<h2>Your places</h2>

<c:set var="counter" value="1" />
<c:forEach items="${userPlaces}" var="place">
    ${counter} ${place.categoryDTO.name} ${place.name} <a href="/place-details/place-id/${place.id}">Details</a><br>
    <c:set var="counter" value="${counter + 1}" />
</c:forEach>

<c:if test="${empty userPlaces}" >
    No places<br>
</c:if>

<a href="/place/create">Add place</a><br>

<h2>Your events</h2>

<c:set var="counter" value="0" />
<c:forEach items="${userPlaces}" var="place">
  <c:forEach items="${place.eventDTOS}" var="event">
    ${event.date} ${event.time} ${event.title} <a href="/event/${event.id}">Details</a><br>
      <c:set var="counter" value="${counter + 1}" />
  </c:forEach>
</c:forEach>

<c:if test="${counter=='0'}">
    No events<br>
</c:if>

<a href="/event/create">Add event</a><br>

<a href="/logout">Logout</a>
</body>
</html>
