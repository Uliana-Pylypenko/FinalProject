<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
<%-- Update event --%>
      <h2>Add event</h2>
    </header>
  </div>
</section>

  <c:if test="${empty userPlaces}">
  <div class="align-center">
    Add a place and come back<br>
    <a href="/place/create" class="button special">Add place</a>
    </div>
  </c:if>


  <c:if test="${not empty userPlaces}">
<section class="wrapper style2 align-center">
  <div class="inner align-center">

    <!-- Form -->
    <form method="post" action="">
      <div class="align-center">

      Place<br>
        <select name="place">
          <option value="">- Place -</option>
        <c:forEach items="${userPlaces}" var="place">
          <option value="${place.id}">${place.name}</option>
        </c:forEach>
        </select>

        <br>

        <input type="text" name="title" placeholder="Title" value="${current_single_event.title}">

        <br>

        <input type="date" name="date" placeholder="Date" value="${current_single_event.date}">

        &nbsp;

        <input type="time" name="time" placeholder="Time" value="${current_single_event.time}">

        <br><br>

        <input type="text" name="description" placeholder="Description" value="${current_single_event.description}">


        <br>
        <div class="12u$">
          <input type="submit" value="Save">
        </div>
      </div>
    </form>

    <br>
    <div class="align-center">
      ${error_message}
    </div>


  </div>
</section>
  </c:if>


<%@ include file="footer.jsp" %>