<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 10/04/2025
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Events</h2>
    </header>
  </div>
</section>

<section class="wrapper style2 align-center">

  <div class="inner align-center">

    <!-- Form -->
    <form method="get" action="">
      <div class="align-center">
        <div class="grid-style">
            <div>
              <h4>Start date</h4> <input type="date" name="start_date" value="${filter_start_date}">
            </div>
          <div><h4>End date</h4><input type="date" name="end_date" value="${filter_end_date}"></div>

        </div>
        <div class="grid-style">
          <div><input type="text" name="country" value="${filter_country}" placeholder="Country"></div>
          <div><input type="text" name="city" value="${filter_city}" placeholder="City"></div>
        </div>

        <br>
        <div class="12u$">
          <input type="submit" value="Filter">
        </div>
      </div>
    </form>

    <br>


<%-- Table --%>

    <div class="table-wrapper align-left">
      <table>
        <thead>
        <tr>
          <th>Date</th>
        <th>Time</th>
        <th>Title</th>
      </tr>
        </thead>
        <tbody>
        <c:forEach items="${events}" var="event">
        <tr>
        <td>${event.date}</td>
        <td>${event.time}</td>
        <td><a href="/event/${event.id}">${event.title}</a></td>
      </tr>
        </c:forEach>

        </tbody>
      </table>
    </div>

  </div>
</section>



<%@ include file="footer.jsp" %>
