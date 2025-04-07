<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Leaflet + Raster Map tiles</title>
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
  var map = L.map("my-map").setView([${place.latitude}, ${place.longitude}], 10);

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


  marker = L.marker(new L.LatLng(${place.latitude}, ${place.longitude}));

  <c:forEach items="${placeApiDTOS}" var="placeApiDTO">
  marker = L.marker(new L.LatLng(${placeApiDTO.latitude}, ${placeApiDTO.longitude})).addTo(map);
  </c:forEach>


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