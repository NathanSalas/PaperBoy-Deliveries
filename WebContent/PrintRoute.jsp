<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<title>Google Maps TEST</title>
<link rel="stylesheet" type="text/css" href="header.css">
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
  var directionDisplay;
  var directionsService = new google.maps.DirectionsService();
  var map;
  var uLoc= "${uAddress}, ${uCity}, ${uState}, ${uZip}";
  var cList = ${cToday};
  var pinColor = "FE7569";
  
  function initialize() {
    directionsDisplay = new google.maps.DirectionsRenderer({suppressMarkers: true});
    var chicago = new google.maps.LatLng(41.850033, -87.6500523);
    var myOptions = {
      zoom: 6,
      scrollwheel: false,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      center: uLoc
    }
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    directionsDisplay.setMap(map);
    calcRoute();
  }
  
  function calcRoute() {
	var addressList = [];
	var infoList = [];
	for(var i=0;i<cList.length;i++){
		addressList.push({location: cList[i], stopover:true});
 		cList.splice(i, 1);
	}
    var request = {
        origin: uLoc, 
        destination: uLoc, 
        waypoints: addressList,
        optimizeWaypoints: true,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
      if (status == google.maps.DirectionsStatus.OK) {
        directionsDisplay.setDirections(response);
        var route = response.routes[0];
        var order = response.routes[0].waypoint_order;
        var tempMkr;
        var summaryPanel = document.getElementById("directions_panel");
        summaryPanel.innerHTML = "";
        // For each route, display summary information.
        for (var i = 0; i < order.length; i++) {
        	var stopNum = i + 1;
      		new google.maps.Marker({
       			position: route.legs[stopNum].start_location,
       	    	map: map, 
       	    	label: {
       	    		text: String(stopNum),
       	    		fontWeight: 'bold'
       	    	}
       	  	});	
          	summaryPanel.innerHTML += "<b>" + stopNum + ":</b>" + cList[order[i]] + "<br /><br />";
        }
        new google.maps.Marker({
				position: route.legs[0].start_location,
	    		map: map,
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
        computeTotalDistance(response);
      } else {
        alert("directions response "+status);
      }
    });
  }
      function computeTotalDistance(result) {
      var totalDist = 0;
      var totalTime = 0;
      var myroute = result.routes[0];
      for (i = 0; i < myroute.legs.length; i++) {
        totalDist += myroute.legs[i].distance.value;
        totalTime += myroute.legs[i].duration.value;      
      }
      totalDist = totalDist / 1000.
      document.getElementById("total").innerHTML = "total distance is: "+ totalDist + " km<br>total time is: " + (totalTime / 60).toFixed(2) + " minutes";
	}
      
      function printDiv(divName) {
    	     var printContents = document.getElementById(divName).innerHTML;
    	     var originalContents = document.body.innerHTML;
    	     document.body.innerHTML = printContents;
    	     window.print();
    	     document.body.innerHTML = originalContents;
    	}
</script>
</head>
<body onload="initialize()">
<div id="header"></div>

<div id="nav">
<a href="EmployeeHome.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Home</b></font></a><br><br>
<a href="CustomerCenter.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Customer Center</b></font></a><br><br>
<a href="PublicationCenter.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Publications</b></font></a><br><br>
<a href="Printing.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Printing</b></font></a><br><br>
<a href="Login.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Log Out</b></font></a><br><br>
</div>

<div id="section">
<h2>Route Printer</h2>

<div id="map_canvas" style="float:left;width:500px;height:500px;"></div>
<div id="control_panel" style="float:right;width:250px;text-align:left;">
<div id="directions_panel" style="margin:5px;"></div>
<div id="total"></div>
</div>
</div>
<input type="button" onclick="printDiv('section')" value="Print" />
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript"> 
</script> 
<script type="text/javascript"> 
_uacct = "UA-162157-1";
urchinTracker();
</script> 
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>