<%@ include file="map_header.jsp" %>

<section class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Touristic places near ${place.name}</h2>
    </header>
  </div>
</section>


<section>
  <div class="align-center">
    <br>
    <a href="/place-details/place-id/${place.id}" class="button special">Back to details</a>
  </div>
<div class="map-container">
  <div id="my-map"></div>
</div>
</section>

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


<!-- Footer -->
<div class="copyright">
  Made with <a href="https://templated.co/">Templated</a> Distributed by <a href="https://themewagon.com/">ThemeWagon</a>.
</div>
<!-- Scripts -->
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/jquery.scrollex.min.js"></script>
<script src="/assets/js/skel.min.js">

</script><script src="/assets/js/util.js"></script>
<script src="/assets/js/main.js"></script>
</body>
</html>