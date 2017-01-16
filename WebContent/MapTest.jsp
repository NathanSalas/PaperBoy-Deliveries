<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Google Maps JavaScript API v3 Example: Directions Waypoints</title>
<style>
    #map{
    width: 500px;
    height: 500px;
}
</style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript">
<<<<<<< HEAD
      function yolo() {
		var stops=${cToday};
		var uLoc= "${uAddress}, ${uCity}, ${uState}, ${uZip}";
=======
      jQuery(function() {
		var stops=${cToday};
		var uLoc= "${uAddress}, ${uCity}, ${uState}, ${uZip}";
		stops.push(uLoc);
		stops.unshift(uLoc);
		var ord = [];
		var ordOffset=0;
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
    	var map = new window.google.maps.Map(document.getElementById("map"));

    // new up complex objects before passing them around
    var directionsDisplay = new window.google.maps.DirectionsRenderer({suppressMarkers: true});
    var directionsService = new window.google.maps.DirectionsService();
    Tour_startUp(stops);

    window.tour.loadMap(map, directionsDisplay);
    window.tour.fitBounds(map);

    if (stops.length > 1)
        window.tour.calcRoute(directionsService, directionsDisplay);
}

function Tour_startUp(stops) {
	var info=${cInfo};
    if (!window.tour) window.tour = {
        updateStops: function (newStops) {
            stops = newStops;
        },
        // map: google map object
        // directionsDisplay: google directionsDisplay object (comes in empty)
        loadMap: function (map, directionsDisplay) {
            var myOptions = {
                zoom: 13,
                center: new window.google.maps.LatLng(51.507937, -0.076188), // default to London
                mapTypeId: window.google.maps.MapTypeId.ROADMAP
            };
            map.setOptions(myOptions);
            directionsDisplay.setMap(map);
        },
        fitBounds: function (map) {
            var bounds = new window.google.maps.LatLngBounds();

            // extend bounds for each record
/*
            jQuery.each(stops, function (key, val) {
                var myLatlng = new window.google.maps.LatLng(val.Geometry.Latitude, val.Geometry.Longitude);
                bounds.extend(myLatlng);
            });
            map.fitBounds(bounds);
*/
        },
        calcRoute: function (directionsService, directionsDisplay) {
        	var uLoc= "${uAddress}, ${uCity}, ${uState}, ${uZip}";
        	var perD= "${numD}";
        	var addressList =[];
        	stops.push(uLoc);
    		stops.unshift(uLoc);
//         	for(var i=0;i<stops.length;i++){
//         		if(i%(perD.valueOf())==0&&i>0){
//         			addressList.push({location: uLoc, stopover:true});
//         		}
//         		addressList.push({location: stops[i], stopover:true});
//         	}
            var batches = [];
            var itemsPerBatch = 10; // google API max = 10 - 1 start, 1 stop, and 8 waypoints
            var itemsCounter = 0;
            var wayptsExist = stops.length > 0;
            while (wayptsExist) {
                var subBatch = [];
                var subitemsCounter = 0;
				var addEnd;
                for (var j = itemsCounter; j < stops.length; j++) {
                    subitemsCounter++;
                    subBatch.push({
                        location: stops[j],
                        stopover: true
                    });
// 					subBatch.push(addressList[j])
                    if (subitemsCounter == (itemsPerBatch)){
                        break;
                    }
                }
                itemsCounter += subitemsCounter;
                batches.push(subBatch);
                wayptsExist = itemsCounter < stops.length;
                // If it runs again there are still points. Minus 1 before continuing to
                // start up with end of previous tour leg
                itemsCounter--;
            }

            // now we should have a 2 dimensional array with a list of a list of waypoints
            var combinedResults;
            var unsortedResults = [{}]; // to hold the counter and the results themselves as they come back, to later sort
            var directionsResultsReturned = 0;

            for (var k = 0; k < batches.length; k++) {
                var lastIndex = batches[k].length - 1;
                var start = batches[k][0].location;
                var end = batches[k][lastIndex].location;

                // trim first and last entry from array
                var waypts = [];
                waypts = batches[k];
                waypts.splice(0, 1);
                waypts.splice(waypts.length - 1, 1);

                var request = {
                    origin: start,
                    destination: end,
                    waypoints: waypts,
                    optimizeWaypoints: true,
                    travelMode: window.google.maps.TravelMode.DRIVING
                };
                (function (kk) {
                    directionsService.route(request, function (result, status) {
                        if (status == window.google.maps.DirectionsStatus.OK) {
//                          	ord[k] = result.routes[0].waypoint_order;
                            var unsortedResult = { order: kk, result: result };
                            unsortedResults.push(unsortedResult);

                            directionsResultsReturned++;

                            if (directionsResultsReturned == batches.length) // we've received all the results. put to map
                            {
                                // sort the returned values into their correct order
                                unsortedResults.sort(function (a, b) { return parseFloat(a.order) - parseFloat(b.order); });
                                var count = 0;
                                for (var key in unsortedResults) {
                                    if (unsortedResults[key].result != null) {
                                        if (unsortedResults.hasOwnProperty(key)) {
                                            if (count == 0) // first results. new up the combinedResults object
                                                combinedResults = unsortedResults[key].result;
                                            else {
                                                // only building up legs, overview_path, and bounds in my consolidated object. This is not a complete
                                                // directionResults object, but enough to draw a path on the map, which is all I need
                                                combinedResults.routes[0].legs = combinedResults.routes[0].legs.concat(unsortedResults[key].result.routes[0].legs);
                                                combinedResults.routes[0].overview_path = combinedResults.routes[0].overview_path.concat(unsortedResults[key].result.routes[0].overview_path);
                                                combinedResults.routes[0].waypoint_order = combinedResults.routes[0].waypoint_order.concat(unsortedResults[key].result.routes[0].waypoint_order);
                                                combinedResults.routes[0].bounds = combinedResults.routes[0].bounds.extend(unsortedResults[key].result.routes[0].bounds.getNorthEast());
                                                combinedResults.routes[0].bounds = combinedResults.routes[0].bounds.extend(unsortedResults[key].result.routes[0].bounds.getSouthWest());
                                            }
                                            count++;
                                        }
                                    }
                                }
                                
                                directionsDisplay.setDirections(combinedResults);
                                var legs = combinedResults.routes[0].legs;
<<<<<<< HEAD
                                directionsDisplay.getMap().setCenter(legs[legs.length-1].end_location);
=======
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
                                var oreo = combinedResults.routes[0].waypoint_order;
                               	var toMult = 0;
                                for(var i=0;i<oreo.length;i++){
                                	if(i%8==0&&i>0){
                                		toMult =i/8;
                                	}
                                	oreo[i]=oreo[i]+(toMult*8);
                                }
<<<<<<< HEAD
                                //var info=${cInfo};
=======
                                var info=${cInfo};
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
                                var endPts = [];
                                for(var i=0;i<oreo.length;i++){
                                	if(i%8==0&&i>0){
                                		endPts.push(info.splice((i-1), 1));
                                	}
                                }
                                var endPtCount =0;
                                var wayPtCount =0;
                                var locList = document.getElementById("location_list");
                        		locList.innerHTML = "";
                                // alert(legs.length);
                                for (var i=1; i < legs.length;i++){
									var markerletter = "A".charCodeAt(0);
				  					markerletter += (i-1);
                    	            markerletter = String.fromCharCode(markerletter);
                                 	createMarker(directionsDisplay.getMap(),legs[i].start_location,"Stop "+markerletter,"<br>"+legs[i].start_address,markerletter);
                                 	if(i%8!=0){
				                		locList.innerHTML+="<b>" + markerletter + "</b> " + info[oreo[wayPtCount]] + "<br>";
				                		wayPtCount++;
                                 	}
				                	else{
				                		locList.innerHTML+="<b>" + markerletter + "</b> " + endPts[endPtCount] + "<br>";
				                		endPtCount++;
				                	}
                                }
                                new google.maps.Marker({
                    				position: legs[legs.length-1].end_location,
                    	    		map: directionsDisplay.getMap(),
                    	    		label: {
                           	    		text: 'H',
                           	    		fontWeight: 'bold'
                           	    	},
                    	    		icon: {
                    	    		      path: google.maps.SymbolPath.CIRCLE,
                    	    		      scale: 12,
                    	    		      strokeWeight: 1,
                    	    		      fillOpacity: 1,
                    	    		      fillColor: 'GREEN'
                    	    		    }
                    	  		});
							}
                        }
                    });
                })(k);
            }
        }
    };
}
var infowindow = new google.maps.InfoWindow(
  { 
    size: new google.maps.Size(150,50)
  });

var icons = new Array();
icons["red"] = new google.maps.MarkerImage("mapIcons/marker_red.png",
      // This marker is 20 pixels wide by 34 pixels tall.
      new google.maps.Size(20, 34),
      // The origin for this image is 0,0.
      new google.maps.Point(0,0),
      // The anchor for this image is at 9,34.
      new google.maps.Point(9, 34));



function getMarkerImage(iconStr) {
   if ((typeof(iconStr)=="undefined") || (iconStr==null)) { 
      iconStr = "red"; 
   }
   if (!icons[iconStr]) {
      icons[iconStr] = new google.maps.MarkerImage("http://www.google.com/mapfiles/marker"+ iconStr +".png",
      // This marker is 20 pixels wide by 34 pixels tall.
      new google.maps.Size(20, 34),
      // The origin for this image is 0,0.
      new google.maps.Point(0,0),
      // The anchor for this image is at 6,20.
      new google.maps.Point(9, 34));
   } 
   return icons[iconStr];

}
  // Marker sizes are expressed as a Size of X,Y
  // where the origin of the image (0,0) is located
  // in the top left of the image.
 
  // Origins, anchor positions and coordinates of the marker
  // increase in the X direction to the right and in
  // the Y direction down.

  var iconImage = new google.maps.MarkerImage('mapIcons/marker_red.png',
      // This marker is 20 pixels wide by 34 pixels tall.
      new google.maps.Size(20, 34),
      // The origin for this image is 0,0.
      new google.maps.Point(0,0),
      // The anchor for this image is at 9,34.
      new google.maps.Point(9, 34));
  var iconShadow = new google.maps.MarkerImage('http://www.google.com/mapfiles/shadow50.png',
      // The shadow image is larger in the horizontal dimension
      // while the position and offset are the same as for the main image.
      new google.maps.Size(37, 34),
      new google.maps.Point(0,0),
      new google.maps.Point(9, 34));
      // Shapes define the clickable region of the icon.
      // The type defines an HTML &lt;area&gt; element 'poly' which
      // traces out a polygon as a series of X,Y points. The final
      // coordinate closes the poly by connecting to the first
      // coordinate.
  var iconShape = {
      coord: [9,0,6,1,4,2,2,4,0,8,0,12,1,14,2,16,5,19,7,23,8,26,9,30,9,34,11,34,11,30,12,26,13,24,14,21,16,18,18,16,20,12,20,8,18,4,16,2,15,1,13,0],
      type: 'poly'
  };


function createMarker(map, latlng, label, html, color) {
// alert("createMarker("+latlng+","+label+","+html+","+color+")");
    var contentString = '<b>'+label+'</b><br>'+html;
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        shadow: iconShadow,
        icon: getMarkerImage(color),
        shape: iconShape,
        title: label,
        zIndex: Math.round(latlng.lat()*-100000)<<5
        });
        marker.myname = label;

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.setContent(contentString); 
        infowindow.open(map,marker);
        });
    return marker;
}
    </script>
  </head>
  <body onload="yolo()">
  <div id="map"></div>
  <div id="location_list" style="margin:5px;"></div>
<<<<<<< HEAD
  <div id="map2"></div>
=======
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-162157-1";
urchinTracker();
</script>
</body>
</html>