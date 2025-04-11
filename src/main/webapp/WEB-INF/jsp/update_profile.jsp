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

    <!-- Form -->
    <form method="post" action="">
      <div class="align-center">

        <input type="text" value="${userDTO.username}" name="username" placeholder="Username">

        <br>

        <input type="email" value="${userDTO.email}" name="email" placeholder="Email">

        <br>

        <input type="password" name="password" placeholder="Password">
        <br>
        <div class="12u$">
          <input type="submit" value="Save">
        </div>
      </div>
    </form>

    <br>
    <h4>${error_message}</h4>


  </div>
</section>


<%@ include file="footer.jsp" %>