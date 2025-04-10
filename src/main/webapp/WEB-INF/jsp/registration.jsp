<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 10/04/2025
  Time: 16:06
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

    <!-- Form -->
    <form method="post" action="">
      <div class="align-center">

          <input type="text" name="username" placeholder="Username" width="50%">

        <br>

          <input type="email" name="email" placeholder="Email">

        <br>

          <input type="password" name="password" placeholder="Password">

        <br>

          <input type="password" name="password_check" placeholder="Repeat password">

        <br>
        <div class="12u$">
          <input type="submit" value="Register">
        </div>
      </div>
    </form>


  </div>
</section>


<%@ include file="footer.jsp" %>