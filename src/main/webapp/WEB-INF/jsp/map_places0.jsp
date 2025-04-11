<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Map</title>
  <form action="" method="get">
    Place name
    <input type="text" name="name" value="${filter_name}" placeholder="Place name"><br>
    Country
    <input type="text" name="country" value="${filter_country}" placeholder="Country"><br>
    City
    <input type="text" name="city" value="${filter_city}" placeholder="City"><br>
    Activity
    <input type="text" name="activity" value="${filter_activity}" placeholder="Activity"><br>
    Categories<br>
    <c:forEach items="${all_categories}" var="category">
      <input type="checkbox" name="${category.name}">
      ${category.name}<br>
    </c:forEach>

    <button type="submit">Filter</button>
  </form>
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
    html,
    body,
    #my-map {
      width: 70%;
      height: 70%;
      margin: 0;
    }
  </style>
</head>
<body data-new-gr-c-s-check-loaded="14.991.0" data-gr-ext-installed="">
<div id="my-map"></div>
<script type="text/javascript">
  var map = L.map("my-map").setView([52.21, 20.97], 10);

  // Get your own API Key on https://myprojects.geoapify.com
  var myAPIKey = "9f97967260db4356adf1836958f7f9f8"

  // Retina displays require different mat tiles quality
  var isRetina = L.Browser.retina;

  var baseUrl =
          "https://maps.geoapify.com/v1/tile/osm-bright/{z}/{x}/{y}.png?apiKey={apiKey}";
  var retinaUrl =
          "https://maps.geoapify.com/v1/tile/osm-bright/{z}/{x}/{y}@2x.png?apiKey={apiKey}";

  // marker = L.marker(new L.LatLng(48.1500327, 11.5753989)).addTo(map); <!-- PINEZKA -->

  //const markerIcon = L.icon({
  //  iconUrl: `https://api.geoapify.com/v1/icon/?type=material&color=red&iconType=awesome&scaleFactor=2&apiKey=${myAPIKey}`,
  //iconSize: [31, 46], // size of the icon
  //iconAnchor: [15.5, 42], // point of the icon which will correspond to marker's location
  //popupAnchor: [0, -45] // point from which the popup should open relative to the iconAnchor
  // });


  var places = JSON.parse('${places}');
  console.log(places);

  places.forEach(function(place) {
    var latitude = place.latitude;
    var longitude = place.longitude;
    L.marker([latitude, longitude]).addTo(map);
    //console.log(longitude);
  });

  // Add map tiles layer. Set 20 as the maximal zoom and provide map data attribution.
  L.tileLayer(isRetina ? retinaUrl : baseUrl, {
    attribution:
            'Powered by <a href="https://www.geoapify.com/" target="_blank">Geoapify</a> | © OpenStreetMap <a href="https://www.openstreetmap.org/copyright" target="_blank">contributors</a>',
    apiKey: myAPIKey,
    maxZoom: 20,
    id: "osm-bright",
  }).addTo(map);


</script>


</body>
</html>
