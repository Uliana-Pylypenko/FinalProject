<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>Hielo by TEMPLATED</title>
  <meta charset="utf-8"><meta name="robots" content="index, follow, max-image-preview:large, max-snippet:-1, max-video-preview:-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/assets/css/main.css">
</head>
<body>

<!-- Header -->
<header id="header">
  <div class="logo">
    <a href="/">Home</a>
  </div>
  <c:if test="${empty userDTO}">
    <a href="/login">Log in</a>
    <a href="/register">Register</a>
  </c:if>

  <c:if test="${userDTO.admin}">
    <a href="/admin/home">Admin profile</a>
  </c:if>

  <c:if test="${not empty userDTO && ! userDTO.admin}">
    <a href="/user/home">User profile</a>
  </c:if>

  <c:if test="${not empty userDTO}">
    <a href="/logout">Logout</a>
</c:if>
</header>
