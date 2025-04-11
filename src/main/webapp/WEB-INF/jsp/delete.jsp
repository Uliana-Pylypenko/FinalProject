<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 11/04/2025
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Registration</h2>
    </header>
  </div>
</section>


<section class="wrapper style2 align-center">
  <div class="inner align-center">

    <h3>${delete_message}</h3>

    <!-- Form -->
    <form method="post" action="">
      <div class="align-center">
        <div class="12u$">
          <button value="true" type="submit" name="answer">Yes</button>
          <button value="false" type="submit" name="answer">No</button>
        </div>
      </div>
    </form>


  </div>
</section>


<%@ include file="footer.jsp" %>