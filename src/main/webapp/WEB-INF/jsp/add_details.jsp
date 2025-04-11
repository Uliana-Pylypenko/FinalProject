<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 10/04/2025
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
    <div class="inner">
        <header class="align-center">
            <h2>Add details for place: ${current_place.name}</h2>
        </header>
    </div>
</section>


<section class="wrapper style2 align-center">
    <div class="inner align-center">

        <!-- Form -->
        <form method="post" action="">
            <div class="align-center">

                <input type="text" name="country" placeholder="Country" value="${current_details.country}">

                <br>

                <input type="text" name="city" placeholder="City" value="${current_details.location}">

                <br>

                <input type="text" name="address" placeholder="Address" value="${current_details.address}">

                <br>

                <input type="text" name="activities" placeholder="Activities" value="${current_details.activites}">

                <br>

                <input type="text" name="description" placeholder="Description" value="${current_details.description}">

                <br>
                <div class="12u$">
                    <input type="submit" value="Save">
                </div>
            </div>
        </form>


    </div>
</section>


<%@ include file="footer.jsp" %>