<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
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
		if (check != 2) {
			alert("მონიშნეთ ორი წერტილი რუკაზე");
			return false;
		}
		if ((registerform.from.value).length == 0
				|| (registerform.from.value).length > 30) {
			alert("გასვლის ადგილი უნდა იყოს 1-დან 30 სიმბოლომდე ზომის");
			return false;
		}
		if ((registerform.to.value).length == 0
				|| (registerform.to.value).length > 30) {
			alert("დანიშნულების ადგილი უნდა იყოს 1-დან 30 სიმბოლომდე ზომის");
			return false;
		}
		if((registerform.fee.value).length==0){
			alert("შეიყვანეთ გადასახადი");
			return false;
		}
		if((registerform.places.value).length==0){
			alert("შეიყვანეთ ადგილების რაოდენობა");
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
			alert("აირჩიეთ ყოველდღიური ან ერთჯერადი");
			return false;
		}
		if (once) {
			if (((registerform.date.value).length == 0)
					|| ((registerform.time.value).length == 0)) {
				alert("ერთი ველი მაინც ერთჯერადისთვის ცარიელია");
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
				alert("არც ერთი დღე არ არის მონიშნული");
				return false;
			}
			for (var i = 0; i < index; i++) {
				if (array[i] == "0") {
					if ((registerform.time0.value).length == 0) {
						alert("ორშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "1") {
					if ((registerform.time1.value).length == 0) {
						alert("სამშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "2") {
					if ((registerform.time2.value).length == 0) {
						alert("ოთხშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "3") {
					if ((registerform.time3.value).length == 0) {
						alert("ხუთშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "4") {
					if ((registerform.time4.value).length == 0) {
						alert("პარასკევი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "5") {
					if ((registerform.time5.value).length == 0) {
						alert("შაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "6") {
					if ((registerform.time6.value).length == 0) {
						alert("კვირა დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				}
			}
		}
		return true;
	}
</script>

</head>
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
					<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
					<li><a id="modaltrigger">შეავსეთ
							დეტალური ინფორმაცია</a></li>
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->
			<div id="registermodal" style="display: none;">
				<h2 class="txt">დეტალური ინფორმაცია მგზავრობაზე</h2>
				<h5>
					<b>გთხოვთ შეავსოთ ყველა ველი</b>
				</h5>
				<form action="EventServlet" id="registerform" name="registerform"
					method="post">
					<div class="txtfield">
						<input type="text" name="from" placeholder="გასვლის ადგილი"
							class="textfield" id="from" tabindex="1"></input>
					</div>
					<div class="txtfield">
						<input type="text" name="to" placeholder="დანიშნულების ადგილი"
							class="textfield" id="to" tabindex="2"></input>
					</div>
					<div class="txtfield">
						<input type="number" min="0" step="0.01" name="fee"
							placeholder="გადასახადი (ლარებში)" class="textfield" tabindex="3"></input>
					</div>
					<div class="txtfield">
						<input type="number" min="1" name="places"
							placeholder="ადგილების რაოდენობა" class="textfield" tabindex="3"></input>
					</div>
					<p>
						ყოველდღიური <input type="radio" class="radio1" name="type"
							value="everyday" tabindex="5"></input>
					</p>
					<p>
						ორშაბათი: <input type="checkbox" name="0" value="0" tabindex="5"></input> <input
							type="text" data-field='time' name="time0" placeholder="დრო"
							tabindex="4"></input> სამშაბათი: <input type="checkbox" name="1" value="1"
							tabindex="5"></input> <input type="text" data-field='time'
							name="time1" placeholder="დრო" tabindex="4"></input> ოთხშაბათი: <input
							type="checkbox" name="2" value="2" tabindex="5"></input> <input type="text"
							data-field='time' name="time2" placeholder="დრო" tabindex="4"></input>
					</p>
					<p>
						ხუთშაბათი: <input type="checkbox" name="3" value="3"tabindex="5"></input> <input
							type="text" data-field='time' name="time3" placeholder="დრო"
							tabindex="4"></input> პარასკევი: <input type="checkbox" name="4" value="4"
							tabindex="5"></input> <input type="text" data-field='time'
							name="time4" placeholder="დრო" tabindex="4"></input> შაბათი: <input
							type="checkbox" name="5" value="5" tabindex="5"></input> <input type="text"
							data-field='time' name="time5" placeholder="დრო" tabindex="4"></input>
					</p>
					<p>
						კვირა: <input type="checkbox" name="6" value="6" tabindex="5"></input> <input
							type="text" data-field='time' name="time6" placeholder="დრო"
							tabindex="4"></input>
					</p>
					<p>
						ერთჯერადი <input type="radio" class="radio2" name="type"
							value="oneway" tabindex="6"></input>
					</p>
					<p>
						<input type="text" data-field='date' name="date"
							placeholder="თარიღი" tabindex="4"></input> <input type="text"
							data-field='time' name="time" placeholder="დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<div id="timepicker"></div>
					<input type="submit" name="registerbtn" id="registerbtn"
						class="flatbtn-blu hidemodal" value="შექმნა" tabindex="8"
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
	      		alert("მოცემულ ორ პუნქტს შორის საავტომობილო გზა არ არსებობს.");          	      	
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
				content : 'საწყისი პუნქტი'
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
				content : 'საბოლოო პუნქტი'
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
					content : 'საწყისი პუნქტი'
				});
				infowindow.open(map, marker);
				 google.maps.event.addListener(marker, 'dragend', function() 
			    		  {
			    		      SavePosition(marker.getPosition(),1);
			    		  });
		        document.getElementById('from').value = document.getElementById('address').value;
                document.getElementById('from').readOnly  = true;
		        document.getElementById('address').value = "";
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
					content : 'საბოლოო პუნქტი'
				});
				infowindow.open(map, marker);
				google.maps.event.addListener(marker, 'dragend', function() 
		    			{
		    			    SavePosition(marker.getPosition(),2);
		    			});
				document.getElementById('to').value = document.getElementById('address').value;
                document.getElementById('to').readOnly  = true;
		        document.getElementById('address').value = "";
		        calcRoute();
		      }
		      check++;
		      
		    } else {
		      alert('თქვენს მიერ მითითებული ადგილი არ მოიძებნა. გთხოვთ რუქაზე მონიშნოთ ან შეიყვანოთ სხვა პუნქტი');
		    }
		  });
		} else {
			alert('თქვენ უკვე შეყვანილი გყავთ საწყისი და საბოლოო პუნქტები');
		}
	}
	
	function SavePosition(pos, inti) 
	{
		if(inti == 1){
			fromLongitude = pos.lng();
			document.getElementById("hiddenField1").value=fromLongitude;
			fromLatitude = pos.lat();
			document.getElementById("hiddenField2").value=fromLatitude;
			document.getElementById('from').readOnly  = false;
			if(check==2) calcRoute();
		} else {
			toLongitude =pos.lng();
			document.getElementById("hiddenField3").value=toLongitude;
			toLatitude = pos.lat();
			document.getElementById("hiddenField4").value=toLatitude;
			document.getElementById('to').readOnly  = false;
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
				<h3 style="color:#8693EE"><strong>აირჩიეთ მარშრუტის საწყისი და საბოლოო პუნქტები. ან შეიყვანეთ მათი დასახელებები (ქართულად)</strong></h3>	
 				<input id="address" type="textbox" class="textfield" style="width:140px; height:14px"value="">
 				<button class="mailnumber" style="width:150px;"onclick="codeAddress();" >შეიყვანეთ  <i style="color:#a5cc52; font-size:1.5em;" class="fa fa-map-marker"></i></button>
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