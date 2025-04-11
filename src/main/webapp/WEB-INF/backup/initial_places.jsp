<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Places</title>

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

<form action="" method="get">
    Place name
    <input type="text" name="name" value="${filter_name}" placeholder="Place name"><br>
    Country
    <input type="text" name="country" value="${filter_country}" placeholder="Country"><br>
    City
    <input type="text" name="city" value="${filter_city}" placeholder="City"><br>
    Activity
    <input type="text" name="activity" value="${filter_activity}" placeholder="Activity"><br>
    Categories<br>
    <c:forEach items="${all_categories}" var="category">
        <input type="checkbox" name="${category.name}">
        ${category.name}<br>
    </c:forEach>

    <button type="submit">Filter</button>
</form>

<c:if test="${empty userDTO}">
    <a href="/login">Login or register to add a new place</a><br>
</c:if>

<c:if test="${not empty userDTO}">
    <a href="place/create">Add new place</a><br>
</c:if>

<c:forEach items="${places}" var="place">
  ${place.categoryDTO.name} ${place.name} <a href="/place-details/place-id/${place.id}">Details</a><br>
</c:forEach>

<a href="/place/map">Show as map</a>
</body>
</html>
