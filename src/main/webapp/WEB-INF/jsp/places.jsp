<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 10/04/2025
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Places</h2>
    </header>
  </div>
</section>

<section class="wrapper style2 align-center">

  <div class="inner align-center">

    <!-- Form -->
    <form method="get" action="">
      <div class="align-center">
        <div class="grid-style">
            <div><input type="text" name="name" value="${filter_name}" placeholder="Place name"></div>
          <div><input type="text" name="activity" value="${filter_activity}" placeholder="Activity"></div>

        </div>
        <div class="grid-style">
          <div><input type="text" name="country" value="${filter_country}" placeholder="Country"></div>
          <div><input type="text" name="city" value="${filter_city}" placeholder="City"></div>
        </div>

        <div class="align-left">
        <h4>Categories</h4>
        </div>
        <c:forEach items="${all_categories}" var="category">
        <div class="align-left">
          <input type="checkbox" name="${category.name}" id="${category.name}"><label for="${category.name}">${category.name}</label>
        </div>
        </c:forEach>

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
          <th>Category</th>
        <th>Name</th>
        <th>City</th>
          <th> </th>
      </tr>
        </thead>
        <tbody>
        <c:forEach items="${places}" var="place">
        <tr>
          <td><div class="${place.categoryDTO.name}">${place.categoryDTO.name}</div></td>
        <td>${place.name}</td>
        <td>
        ${place.detailsDTO.location}
        <c:if test="${empty place.detailsDTO.location}">
          -
        </c:if>
        </td>
        <td>
        <c:if test="${place.approved}">
        <img src="images/approved.png" height="20">
        </c:if>
        </td>
      </tr>
        </c:forEach>

        </tbody>
      </table>
    </div>

  </div>
</section>



<%@ include file="footer.jsp" %>