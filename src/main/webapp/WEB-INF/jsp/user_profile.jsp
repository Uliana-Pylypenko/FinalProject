<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Your profile</h2>
    </header>
  </div>
</section>
<br>

<div class="align-center">
 <h3> Username: ${userDTO.username}</h3>
  <h3> Email: ${userDTO.email}<br></h3>
  <a href="/user/update/${userDTO.id}" class="button special">Update profile</a>
</div>
<br>

<section class="wrapper style2 align-center">
  <div class="inner align-center">

    <h3>Your places</h3>
    <c:if test="${empty userPlaces}">
    <p>You haven't added any places yet</p><br>
    </c:if>


<c:if test="${not empty userPlaces}">
<%-- Table --%>
    <div class="table-wrapper align-left">
      <table>
        <thead>
        <tr>
          <th>Category</th>
        <th>Name</th>
        <th>City</th>
          <th> </th>
      </tr>
        </thead>
        <tbody>
        <c:forEach items="${userPlaces}" var="place">
        <tr>
          <td><div class="${place.categoryDTO.name}">${place.categoryDTO.name}</div></td>
        <td><a href="/place-details/place-id/${place.id}">${place.name}</a></td>
        <td>
        ${place.detailsDTO.location}
        <c:if test="${empty place.detailsDTO.location}">
          -
        </c:if>
        </td>
        <td>
        <c:if test="${place.approved}">
        <img src="/images/approved.png" height="20">
        </c:if>
        </td>
      </tr>
        </c:forEach>

        </tbody>
      </table>
    </div>

     </c:if>


<a href="/place/create" class="button special">Add place</a><br>

  </div>
</section>


<section class="wrapper style2 align-center">
  <div class="inner align-center">
    <h3>Your events</h3>

    <c:set var="counter" value="0" />
<c:forEach items="${userPlaces}" var="place">
  <c:forEach items="${place.eventDTOS}" var="event">
      <c:set var="counter" value="${counter + 1}" />
  </c:forEach>
</c:forEach>

<c:if test="${counter=='0'}">
    <p>You haven't added any events yet</p>
</c:if>

<c:if test="${counter!='0'}">
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
        <c:forEach items="${userPlaces}" var="place">
        <c:forEach items="${place.eventDTOS}" var="event">
        <tr>
        <td>${event.date}</td>
        <td>${event.time}</td>
        <td><a href="/event/${event.id}">${event.title}</a></td>
        </tr>
        </c:forEach>
        </c:forEach>

        </tbody>
      </table>
    </div>
    </c:if>

    <a href="/event/create" class="button special">Add event</a><br>

  </div>
</section>


<%@ include file="footer.jsp" %>
