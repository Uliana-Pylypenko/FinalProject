<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 09/04/2025
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Approve</title>
</head>
<body>
<c:forEach items="${places}" var="place">
  ${place.id} ${place.name} ${place.categoryDTO.name} ${place.detailsDTO.country} ${place.detailsDTO.location} <a href="/place-details/place-id/${place.id}">Details</a> <br>
</c:forEach>

${error_message}
</body>
</html>
