<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<section id="One" class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>List of users</h2>
    </header>
  </div>
</section>
<br>

<section class="wrapper style2 align-center">
  <div class="inner align-center">

    <%-- Table --%>

    <div class="table-wrapper align-left">
      <table>
        <thead>
        <tr>
          <th>ID</th>
        <th>Username</th>
        <th>Email</th>
          <th>Admin rights</th>
      </tr>
        </thead>
        <tbody>
        <c:forEach items="${all_users}" var="user">
         <c:if test="${user.id != userDTO.id}">
        <tr>
          <td>${user.id}</td>
          <td>${user.username}</td>
          <td>${user.email}</td>
          <c:if test="${user.admin}">
          <td> <a href="/admin/change-admin-rights/${user.id}">Yes</a> </td>
          </c:if>
          <c:if test="${! user.admin}">
          <td> <a href="/admin/change-admin-rights/${user.id}">No</a> </td>
          </c:if>
      </tr>
      </c:if>
        </c:forEach>

        </tbody>
      </table>
    </div>

    <p>${error_message}</p>

  </div>
</section>


<%@ include file="footer.jsp" %>
