<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 10/04/2025
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Log in</h2>
    </header>
  </div>
</section>


<section class="wrapper style2 align-center">
  <div class="inner align-center">

    <!-- Form -->
    <form method="post" action="">
      <div class="align-center">
        <input type="text" id="username" name="username" placeholder="Username">
        <br>
        <input type="password" id="password" name="password" placeholder="Password">
        <br>
        <div class="12u$">
          <input type="submit" value="Log in">
        </div>
      </div>
    </form>

    <br>

    <h4>${login_error}</h4>



  </div>
</section>

<%@ include file="footer.jsp" %>
