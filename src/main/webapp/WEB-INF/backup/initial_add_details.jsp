<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 09/04/2025
  Time: 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add details</title>
</head>
<body>
<h1>Add details for place ${current_place.name}</h1>
<form action="" method="post">
  Country
  <input type="text" name="country" placeholder="Country" value="${current_details.country}"><br>
  City
  <input type="text" name="city" placeholder="City" value="${current_details.location}"><br>
  Address
  <input type="text" name="address" placeholder="Address" value="${current_details.address}"><br>
  Activities
  <input type="text" name="activities" placeholder="Activities" value="${current_details.activites}"><br>
  Description
  <input type="text" name="description" placeholder="Description" value="${current_details.description}"><br>
  <button type="submit">Save</button><br>
</form>
</body>
</html>
