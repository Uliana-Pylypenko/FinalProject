<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>${current_single_event.title}</h2>
    </header>
  </div>
</section>
<br>

<div class="align-center">
    <h3>${current_single_event.date} ${current_single_event.time}</h3>
</div>

<div class="align-center">
   Place: <a href="/place-details/place-id/${current_place.id}">${current_place.name}</a><br>
  Country: ${current_details.country}<br>
  City: ${current_details.location}<br>
    Description: ${current_single_event.description}<br>
</div>


<br>

<div class="align-center">
<c:if test="${current_place.userId == userDTO.id}">
    <a href="/event/update/${current_single_event.id}" class="button special">Update event</a>
</c:if>
</div>

<br>

<section class="wrapper style2 align-center">

  <div class="inner align-center">
    <h2>Other events in this place</h2>

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
        <c:if test="${event.id != current_single_event.id}">
        <tr>
        <td>${event.date}</td>
        <td>${event.time}</td>
        <td><a href="/event/${event.id}">${event.title}</a></td>
      </tr>
      </c:if>
        </c:forEach>

        </tbody>
      </table>
    </div>
  </div>

    <c:if test="${current_events.size()-1 == 0}">
    <h4>No events</h4><br>
        <c:if test="${current_place.userId == userDTO.id}">
        <a href="/event/create">Add event</a>
        </c:if>
  </c:if>
</section>


<%@ include file="footer.jsp" %>

