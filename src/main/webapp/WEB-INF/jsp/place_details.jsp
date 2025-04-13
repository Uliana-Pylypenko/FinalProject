<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
    <p>${current_place.categoryDTO.name}</p>
      <h2>${current_place.name}</h2>

    </header>
  </div>
</section>
<br>
<div class="align-center">
<c:if test="${empty current_details}">
    <h3>No details provided</h3><br>
    <c:if test="${current_place.userId == userDTO.id}">
        <a href="/place-details/add/${current_place.id}" class="button special">Add details</a><br>
    </c:if>
</c:if>
<br>
</div>
<c:if test="${not empty current_place.detailsDTO}">
<div class="align-center">
  <h4>Country: ${current_details.country}</h4>
  <h4>City: ${current_details.location}</h4>
  <h4>Address: ${current_details.address}</h4>
  <h4>Activities: ${current_details.activites}</h4>

  <div class="custom_container">
  <h4>Description:</h4>
   <p>${current_details.description}</p>
    </div>
</div>
</c:if>
<br>


<div class="align-center">
<c:if test="${current_place.userId == userDTO.id && not empty current_details}">
    <a href="/place-details/update/${current_place.id}" class="button special">Update details</a>
</c:if>
<br>
<br>
<c:if test="${current_place.userId == userDTO.id}">
<a href="/place/update/${current_place.id}" class="button special">Update place</a>
</c:if>
</div>

<br>

<section class="wrapper style2 align-center">

  <div class="inner align-center">
    <h2>Events</h2>
    <c:if test="${empty current_events}">
    <h4>No events</h4><br>
        <c:if test="${current_place.userId == userDTO.id}">
        <a href="/event/create" class="button special">Add event</a>
        </c:if>
  </c:if>

  <c:if test="${not empty current_events}">
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
    </c:if>
  </div>
</section>

<section class="wrapper align-center">
<div class="align-center">
<a href="/geoapi/places/${current_place.id}/leisure,natural,entertainment" class="button special">Show other tourist places nearby</a><br>
</div>

<c:if test="${userDTO.admin && !current_place.approved}">
<br>
<div class="align-center">
    <a href="/admin/approve/${current_place.id}" class="button special">Approve place</a><br>
    </div>
</c:if>

<c:if test="${current_place.userId == userDTO.id}">
<br>
<div class="align-center">
        <a href="/place/delete/${current_place.id}" class="button">Delete place</a>
        </c:if>
</div>
<br>
</section>





<%@ include file="footer.jsp" %>
