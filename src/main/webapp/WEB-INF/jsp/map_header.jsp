<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>

<head>
  <title>Hielo by TEMPLATED</title>
  <meta charset="utf-8">
  <meta name="robots" content="index, follow, max-image-preview:large, max-snippet:-1, max-video-preview:-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/assets/css/main.css">
  <%--  bez script nie dziala --%>
  <script
          type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"
  ></script>
  <link
          rel="stylesheet"
          type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css"
  />

  <style id="compiled-css" type="text/css">
    .map-container {
      width: 75%; /* Adjust the width as needed */
      height: 580px; /* Adjust the height as needed */
      margin: 20px auto; /* Center the map horizontally */
      border: 2px solid #ccc; /* Optional: Add a border to the container */
    }

    #my-map {
      width: 100%;
      height: 100%;
    }
  </style>
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