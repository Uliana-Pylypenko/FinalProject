<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>${current_place.name}</h2>
    </header>
  </div>
</section>
<br>
<div class="align-center">
<c:if test="${empty current_details}">
    <h3>No details provided</h3><br>
    <c:if test="${current_place.userId == userDTO.id}">
        <a href="/place-details/add/${current_place.id}" class="button special">Add details</a>
    </c:if>
</c:if>
</div>
<div class="align-center">
  Country: ${current_details.country}<br>
  City: ${current_details.location}<br>
  Address: ${current_details.address}<br>
  Activities: ${current_details.activites}<br>
  Description: ${current_details.description}<br>
</div>

<br>

<div class="align-center">
<c:if test="${current_place.userId == userDTO.id && not empty current_details}">
    <a href="/place-details/update/${current_place.id}" class="button special">Update details</a>
</c:if>
</div>

<br>

<section class="wrapper style2 align-center">

  <div class="inner align-center">
    <h2>Events</h2>
    <c:if test="${empty current_events}">
    <h4>No events</h4><br>
  </c:if>
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
        <c:forEach items="${current_events}" var="event">
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

<section class="wrapper align-center">
<div class="align-center">
<a href="/geoapi/places/${current_place.id}/leisure" class="button special">Show other places nearby</a><br>
</div>

<c:if test="${userDTO.admin && !current_place.approved}">
<div class="align-center">
    <a href="/admin/approve/${current_place.id}" class="button special">Approve place</a><br>
    </div>
</c:if>
</section>
<%@ include file="footer.jsp" %>
