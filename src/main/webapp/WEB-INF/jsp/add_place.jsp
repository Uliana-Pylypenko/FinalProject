<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 11/04/2025
  Time: 08:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Add place</h2>
    </header>
  </div>
</section>


<section class="wrapper style2 align-center">
  <div class="inner align-center">

    <!-- Form -->
    <form method="post" action="">
      <div class="align-center">

        <input type="text" name="name" placeholder="Name" value="${current_place.name}">

        <br>

        <input type="text" name="latitude" placeholder="Latitude" value="${current_place.latitude}">

        <br>

        <input type="text" name="longitude" placeholder="Longitude" value="${current_place.longitude}">

        <br>

        Category<br>
        <select name="category">
          <option value="">- Category -</option>
        <c:forEach items="${categories}" var="category">
          <option value="${category.id}">${category.name}</option>
        </c:forEach>
        </select>

        <br>
        <div class="12u$">
          <input type="submit" value="Register">
        </div>
      </div>
    </form>

    <br>
    <div class="align-center">
      ${error_message}
    </div>


  </div>
</section>


<%@ include file="footer.jsp" %>
