
var map;
var bounds;

var markers = [];
var infoWindowContent = [];




function updatePositionsFor(marker_meta, marker_info) {
    markers.push(marker_meta);
    infoWindowContent.push(marker_info);

    var position = new google.maps.LatLng(marker_meta[1], marker_meta[2]);
    bounds.extend(position);
    marker = new google.maps.Marker({
      position: position,
      map: map,
      title: marker_meta[0]
    });

    // Allow each marker to have an info window
//    google.maps.event.addListener(marker, 'click', (function(marker, i) {
//      return function() {
//          infoWindow.setContent(marker_info[0]);
//          infoWindow.open(map, marker);
//      }
//    })(marker, i));

    // Automatically center the map fitting all markers on the screen
    map.fitBounds(bounds);
}



function initialize() {
    bounds = new google.maps.LatLngBounds();
    var mapOptions = {
      center: new google.maps.LatLng(48.209331, 16.381302),
      zoom: 4,
      mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    map = new google.maps.Map(document.getElementById("map-canvas"),
        mapOptions);
    }



google.maps.event.addDomListener(window, 'load', initialize);