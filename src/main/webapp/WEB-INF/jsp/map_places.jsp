<%@ include file="map_header.jsp" %>

<section class="wrapper style3">
  <div class="inner">
    <header class="align-center">
      <h2>Places</h2>
    </header>
  </div>
</section>


<section class="wrapper style2 align-center">
  <!-- Form -->
    <form method="get" action="">
      <div class="align-center">
        <div class="grid-style">
            <div><input type="text" name="name" value="${filter_name}" placeholder="Place name"></div>
          <div><input type="text" name="activity" value="${filter_activity}" placeholder="Activity"></div>

        </div>
        <div class="grid-style">
          <div><input type="text" name="country" value="${filter_country}" placeholder="Country"></div>
          <div><input type="text" name="city" value="${filter_city}" placeholder="City"></div>
        </div>

        <div class="align-left">
        <h4>Categories</h4>
        </div>
        <c:forEach items="${all_categories}" var="category">
        <div class="align-left">
          <input type="checkbox" name="${category.name}" id="${category.name}"><label for="${category.name}">${category.name}</label>
        </div>
        </c:forEach>

        <br>
        <div class="12u$">
          <input type="submit" value="Filter">
        </div>
      </div>
    </form>

  <div class="align-center">
    <br>
    <a href="/place" class="button special">Show places in a list</a>
  </div>


<div class="map-container">
    <div id="my-map"></div>
  </div>


</section>

<script type="text/javascript">

  var places = JSON.parse('${places}');
  console.log(places);

  var map = L.map("my-map").setView([places[0].latitude, places[0].longitude], 10);

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
    var marker = L.marker([latitude, longitude]).addTo(map);
    marker.bindPopup("Category: " + place.category + ", Name: " + place.name + ", Country: " + place.country + ", City: " + place.city + ", Address: " + place.address);
    marker.on('click', function (e) {
      marker.openPopup();
    });
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