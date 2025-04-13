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
    var placeJSON = JSON.parse('${placeJSON}');
    var map = L.map("my-map").setView([placeJSON['latitude'], placeJSON['longitude']], 10);
    console.log(placeJSON);
    // Get your own API Key on https://myprojects.geoapify.com
    var myAPIKey = "9f97967260db4356adf1836958f7f9f8"

    // Retina displays require different mat tiles quality
    var isRetina = L.Browser.retina;

    var baseUrl =
            "https://maps.geoapify.com/v1/tile/osm-bright/{z}/{x}/{y}.png?apiKey={apiKey}";
    var retinaUrl =
            "https://maps.geoapify.com/v1/tile/osm-bright/{z}/{x}/{y}@2x.png?apiKey={apiKey}";


    const markerIcon = L.icon({
      iconUrl:  "https://api.geoapify.com/v1/icon/?type=material&color=red&iconType=awesome&scaleFactor=2&apiKey=" + myAPIKey,
      iconSize: [31, 46],
      iconAnchor: [15.5, 42],
      popupAnchor: [0, -45]
    });

    marker = L.marker(new L.LatLng(placeJSON['latitude'], placeJSON['longitude']),
            {
              icon: markerIcon
            }).addTo(map);
    marker.bindPopup("Name: " + placeJSON['name']);
    marker.on('click', function (e) {
      marker.openPopup();
    });


    var placeApis = JSON.parse('${placeApiDTOS}');

    console.log(placeApis);

    if (placeApis.length>0) {
      placeApis.forEach(function (place) {
        var latitude = place['lat']
        var longitude = place['lon']
        var marker = L.marker([latitude, longitude]).addTo(map);
        var popup = "Name: " + place['name']
        if (place.hasOwnProperty("address")) {
          popup += ", Address: " + place['address'];
        }
        if (place.hasOwnProperty("distance")) {
          popup += ", Distance: " + place['distance'] + " m"
        }
        marker.bindPopup(popup);
        marker.on('click', function (e) {
          marker.openPopup();
        });
      });
    }
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