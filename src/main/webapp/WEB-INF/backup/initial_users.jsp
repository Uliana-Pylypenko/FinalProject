<%--
  Created by IntelliJ IDEA.
  User: upylypen
  Date: 09/04/2025
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:forEach items="${all_users}" var="user">
  <c:if test="${user != userDTO}">
    ${user.id} ${user.username} ${user.email} ${user.admin} <a href="/admin/change-admin-rights/${user.id}">Change admin right</a> <br>
  </c:if>
</c:forEach>

${error_message}

</body>
</html>
