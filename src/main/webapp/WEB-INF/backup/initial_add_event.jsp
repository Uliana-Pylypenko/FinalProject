<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add event</title>
</head>
<body>
  <c:if test="${empty userPlaces}">
    Add a place and come back<br>
    <a href="/place/create">Add place</a>
  </c:if>

  <c:if test="${not empty userPlaces}">
  <form method="post" action="">
    <c:forEach items="${userPlaces}" var="place">
      ${place.name}
      <input type="radio" name="place" value="${place.id}"><br>
    </c:forEach>
    Title
    <input type="text" name="title" placeholder="Title" value="${current_single_event.title}"><br>
    Date
    <input type="date" name="date" placeholder="Date" value="${current_single_event.date}"><br>
    Time
    <input type="time" name="time" placeholder="Time" value="${current_single_event.time}"><br>
    Description
    <input type="text" name="description" placeholder="Description" value="${current_single_event.description}"><br>
    <button type="submit">Save</button><br>
  </form>
  </c:if>
${error_message}

</body>
</html>
