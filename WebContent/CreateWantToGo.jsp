<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Paputka</title>
<link rel="shortcut icon" type="image/x-icon"
	href="style/images/favicon.png" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet" />
<script type="text/javascript" src="style/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


<script type="text/javascript" src="style/js/jquery.leanModal.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="style/js/DateTimePicker.css" />
<script type="text/javascript" src="style/js/DateTimePicker.js"></script>


<script>
	$(document).ready(function() {
		$("#datepicker").DateTimePicker();
	});
</script>

<script type="text/javascript">
	function CheckAlerts() {
		if ((registerform.title.value).length == 0
				|| (registerform.title.value).length > 100) {
			alert("Title must be between 1 to 100 symbols");
			return false;
		}
		var radios = document.getElementsByName('type');
		var Chcount = 0;
		var once = false;
		var everyDay = false;
		for (var i = 0, length = radios.length; i < length; i++) {
			if (radios[i].checked) {
				if (i == 0) {
					everyDay = true;
				} else if (i == 1) {
					once = true;
				}
				Chcount = 1;
				break;
			}
		}
		if (Chcount == 0) {
			alert("Choose daily or one-way");
			return false;
		}
		if (once) {
			if (((registerform.datetimeStart.value).length == 0)
					|| ((registerform.datetimeFinish.value).length == 0)
					|| ((registerform.startTime.value).length == 0)
					|| ((registerform.endTime.value).length == 0)) {
				alert("One of the field for one way is empty");
				return false;
			}
		}
		var array = [];
		var index = 0;
		if (everyDay) {
			var inputElements = document.getElementsByTagName('input');
			for (var i = 0; inputElements[i]; i++) {
				if ((inputElements[i].name == "0" && inputElements[i].checked)
						|| (inputElements[i].name == "1" && inputElements[i].checked)
						|| (inputElements[i].name == "2" && inputElements[i].checked)
						|| (inputElements[i].name == "3" && inputElements[i].checked)
						|| (inputElements[i].name == "4" && inputElements[i].checked)
						|| (inputElements[i].name == "5" && inputElements[i].checked)
						|| (inputElements[i].name == "6" && inputElements[i].checked)) {

					array[index] = inputElements[i].name;
					index++;
				}
			}
			if (index == 0) {
				alert("None of the days are checked");
				return false;
			}
			for (var i = 0; i < index; i++) {
				if (array[i] == "0") {
					if ((registerform.start0.value).length == 0
							|| (registerform.end0.value).length == 0) {
						alert("Monday is checked,but start time or end time is not filled");
						return false;
					}
				} else if (array[i] == "1") {
					if ((registerform.start1.value).length == 0
							|| (registerform.end1.value).length == 0) {
						alert("Tuesday is checked,but start time or end time is not filled");
						return false;
					}
				} else if (array[i] == "2") {
					if ((registerform.start2.value).length == 0
							|| (registerform.end2.value).length == 0) {
						alert("Wednesday is checked,but start time or end time is not filled");
						return false;
					}
				} else if (array[i] == "3") {
					if ((registerform.start3.value).length == 0
							|| (registerform.end3.value).length == 0) {
						alert("Thursday is checked,but start time or end time is not filled");
						return false;
					}
				} else if (array[i] == "4") {
					if ((registerform.start4.value).length == 0
							|| (registerform.end4.value).length == 0) {
						alert("Friday is checked,but start time or end time is not filled");
						return false;
					}
				} else if (array[i] == "5") {
					if ((registerform.start5.value).length == 0
							|| (registerform.end5.value).length == 0) {
						alert("Saturday is checked,but start time or end time is not filled");
						return false;
					}
				} else if (array[i] == "6") {
					if ((registerform.start6.value).length == 0
							|| (registerform.end6.value).length == 0) {
						alert("Sunday is checked,but start time or end time is not filled");
						return false;
					}
				}
			}
		}
		return true;
	}
</script>

</head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body>
	<!-- Begin Wrapper -->
	<div id="wrapper">
		<!-- Begin Sidebar -->
		<div id="sidebar">
			<div id="logo">
				<img src="style/images/logo.png" alt="Paputka" />
			</div>
			<!-- Begin Menu -->
			<div id="menu" class="menu-v">
				<ul>
					<li><a href="UserPage.jsp" class="active">Main Page</a></li>
					<li><a id="modaltrigger">Fill Detailed Info</a></li>
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->

			<div id="registermodal" style="display: none;">
				<h2 class="txt">Detailed Info About Filter</h2>
				<h5>
					<b>Please fill every field</b>
				</h5>
				<form action="WantToGoServlet" id="registerform" name="registerform"
					method="post">
					<p>
						<input type="text" name="title" placeholder="Title " tabindex="1"></input>
					</p>
					<p>
						Daily <input type="radio" class="radio1" name="type"
							value="everyday" tabindex="5"></input>
					</p>
					<p>
						Monday: <input type="checkbox" name="0" value="0" tabindex="5"></input> <input
							type="text" data-field='time' name="start0"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="end0"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						Tuesday: <input type="checkbox" name="1" value="1" tabindex="5"></input> <input
							type="text" data-field='time' name="start1"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="end1"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						Wednesday: <input type="checkbox" name="2" value="2" tabindex="5"></input> <input
							type="text" data-field='time' name="start2"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="end2"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						Thursday: <input type="checkbox" name="3" value="3" tabindex="5"></input> <input
							type="text" data-field='time' name="start3"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="end3"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						Friday: <input type="checkbox" name="4" value="4" tabindex="5"></input> <input
							type="text" data-field='time' name="start4"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="end4"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						Saturday: <input type="checkbox" name="5" value="5" tabindex="5"></input> <input
							type="text" data-field='time' name="start5"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="end5"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						Sunday: <input type="checkbox" name="6" value="6" tabindex="5"></input> <input
							type="text" data-field='time' name="start6"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="end6"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						One Way<input type="radio" class="radio2" name="type"
							value="oneway" tabindex="6"></input>
					</p>
					<p>
						<input type="text" data-field='date' name="datetimeStart"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='date' name="datetimeFinish"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<p>
						<input type="text" data-field='time' name="startTime"
							placeholder="Start Time" tabindex="4"></input> <input
							type="text" data-field='time' name="endTime"
							placeholder="End Time" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<input type="submit" name="registerbtn" id="registerbtn"
						class="flatbtn-blu hidemodal" value="Create" tabindex="8"
						onClick="return CheckAlerts();"></input>
					<input type="hidden" id="hiddenField1" name="fromLongitude"/>
					<input type="hidden" id="hiddenField2" name="fromLatitude"/>
					<input type="hidden" id="hiddenField3" name="toLongitude"/>
					<input type="hidden" id="hiddenField4" name="toLatitude"/>
				</form>
			</div>
		</div>
		<!-- End Sidebar -->

		<!-- Begin Content -->
		<div id="content">
			<div class="line"></div>
			<html>
<head>
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
<script>
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
var check;
var fromLongitude;
var fromLatitude;
var toLongitude;
var toLatitude;
var geocoder;

function initialize() {
	check = 0;
	geocoder = new google.maps.Geocoder();
	directionsDisplay = new google.maps.DirectionsRenderer();
	var mapProp = {
		center : new google.maps.LatLng(42.347485, 43.7),
		zoom : 7,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
	google.maps.event.addListener(map, 'click', function(event) {
		placeMarker(event.latLng);
	});
	directionsDisplay.setMap(map);
}

function calcRoute() {
	var fLong = document.getElementById("hiddenField1").value;
	var fLat = document.getElementById("hiddenField2").value;
	var tLong = document.getElementById("hiddenField3").value;
	var tLat = document.getElementById("hiddenField4").value;
	var start = new google.maps.LatLng(fLat,fLong);
	var end = new google.maps.LatLng(tLat,tLong);
	var request = {
			origin: start,
      	    destination: end,          	  	
      	    travelMode: google.maps.TravelMode.DRIVING 	   
    };                
	directionsService.route(request, function(response, status) {
	    	if (status != google.maps.DirectionsStatus.OK) {
	    		document.getElementById("modaltrigger").href = "";
	      		alert("There is no car road between these two points.");          	      	
	    	} else {
	    		document.getElementById("modaltrigger").href = "#registermodal";
	    		fun();
	    	}
  	});    
}

function placeMarker(location) {
	check++;
	if (check == 1) {
		var marker = new google.maps.Marker({
			position : location,
			map : map,
			draggable : true,
			icon :'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
		});
		

		fromLongitude = location.lng();
		document.getElementById("hiddenField1").value=fromLongitude;
		fromLatitude = location.lat();
		document.getElementById("hiddenField2").value=fromLatitude;
		var infowindow = new google.maps.InfoWindow({
			content : 'Departure Point'
		});
		infowindow.open(map, marker);
		google.maps.event.addListener(marker, 'dragend', function() 
				{
				    SavePosition(marker.getPosition(),1);
				});
	} else if (check == 2) {
		var marker = new google.maps.Marker({
			position : location,
			map : map,
			draggable : true,
			icon :'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
		});
		

		toLongitude = location.lng();
		document.getElementById("hiddenField3").value=toLongitude;
		toLatitude = location.lat();
		document.getElementById("hiddenField4").value=toLatitude;
		var infowindow = new google.maps.InfoWindow({
			content : 'Destination'
		});
		infowindow.open(map, marker);
		google.maps.event.addListener(marker, 'dragend', function() 
				{
				    SavePosition(marker.getPosition(),2);
				});
		calcRoute();
	}
}

function codeAddress() {
	 if(check<2){
	  var address = document.getElementById('address').value;
	  geocoder.geocode( { 'address': address}, function(results, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
	      map.setCenter(results[0].geometry.location);
	      if(check == 0){
	    	fromLongitude = results[0].geometry.location.lng();
			document.getElementById("hiddenField1").value=fromLongitude;
			fromLatitude = results[0].geometry.location.lat();
			document.getElementById("hiddenField2").value=fromLatitude;
	      var marker = new google.maps.Marker({
	          map: map,
	          position: results[0].geometry.location,
			  draggable : true,

	          icon :'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
	          
	      });
	     

	      var infowindow = new google.maps.InfoWindow({
				content : 'Departure Point'
			});
			infowindow.open(map, marker);
			 google.maps.event.addListener(marker, 'dragend', function() 
		    		  {
		    		      SavePosition(marker.getPosition(),1);
		    		  });
	      } else {
	    	var marker = new google.maps.Marker({
		          map: map,
				  draggable : true,

		          position: results[0].geometry.location,
		          icon :'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
		    });
	    

	    	toLongitude = results[0].geometry.location.lng();
			document.getElementById("hiddenField3").value=toLongitude;
			toLatitude = results[0].geometry.location.lat();
			document.getElementById("hiddenField4").value=toLatitude;
	    	var infowindow = new google.maps.InfoWindow({
				content : 'Destination'
			});
			infowindow.open(map, marker);
			google.maps.event.addListener(marker, 'dragend', function() 
	    			{
	    			    SavePosition(marker.getPosition(),2);
	    			});
			calcRoute();
	      }
	      check++;
	    } else {
	      alert('Your requested destination has not been found. Please mark a new waypoint on the map');
	    }
	  });
	} else {
		alert('You have already marked departure and destination points');
	}
}

function SavePosition(pos, inti) 
{
	if(inti == 1){
		fromLongitude = pos.lng();
		document.getElementById("hiddenField1").value=fromLongitude;
		fromLatitude = pos.lat();
		document.getElementById("hiddenField2").value=fromLatitude;
		if(check ==2) calcRoute();
	} else {
		toLongitude =pos.lng();
		document.getElementById("hiddenField3").value=toLongitude;
		toLatitude = pos.lat();
		document.getElementById("hiddenField4").value=toLatitude;
		calcRoute();
	}
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<div id="googleMap" style="width: 800px; height: 400px;"></div>
</body>
			</html>
			<div class="line"></div>
			<div id="footer">
				<h3 style="color:#8693EE"><strong>Choose departure and destination points, or fill
						the field to add points</strong></h3>	
 				<input id="address" type="textbox" class="textfield" style="width:140px; height:14px" value="">
 				<button class="mailnumber" style="width:150px;"onclick="codeAddress();" >Submit  <i style="color:#a5cc52; font-size:1.5em;" class="fa fa-map-marker"></i></button>
			</div>
			<!-- End Content -->
		</div>
	</div>
	<script type="text/javascript">
	function fun(){
		$(function() {

			$('#modaltrigger').leanModal({
				top : 5,
				overlay : 0.45,
				closeButton : ".modal_close"
			});
		});
		$(document).ready(function() {
			$('.timepicker').timepicker();
		});
	}
	</script>

</body>
</html>