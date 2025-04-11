<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Places to approve</h2>
    </header>
  </div>
</section>
<br>

<section class="wrapper style2 align-center">
  <div class="inner align-center">

    <c:if test="${empty places}">
    <h3>All places are approved</h3>
    <a href="/admin/home" class="button">Back to profile</a>
    </c:if>


    <c:if test="${not empty places}">

    <%-- Table --%>

    <div class="table-wrapper align-left">
      <table>
        <thead>
        <tr>
          <th>ID</th>
        <th>Category</th>
        <th>Name</th>
          <th>Location</th>
          <th>User ID</th>

      </tr>
        </thead>
        <tbody>
        <c:forEach items="${places}" var="place">
        <tr>
          <td>${place.id}</td>
          <td><div class="${place.categoryDTO.name}">${place.categoryDTO.name}</div></td>
          <td><a href="/place-details/place-id/${place.id}">${place.name}</a></td>
          <c:if test="${not empty place.detailsDTO}">
           <td>${place.detailsDTO.country}, ${place.detailsDTO.location}</td>
          </c:if>
         <c:if test="${empty place.detailsDTO}">
           <td> - </td>
          </c:if>
          <td>${place.userId}</td>

      </tr>
        </c:forEach>

        </tbody>
      </table>
    </div>
    </c:if>

    <p>${error_message}</p>

  </div>
</section>


<%@ include file="footer.jsp" %>
