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
		if (check != 2) {
			alert("მონიშნეთ ორი წერტილი რუკაზე");
			return false;
		}
		if ((registerform.title.value).length == 0
				|| (registerform.title.value).length > 100) {
			alert("სახელი უნდა იყოს 1-დან 100 სიმბოლომდე ზომის");
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
			if (((registerform.datetimeStart.value).length == 0)
					|| ((registerform.datetimeFinish.value).length == 0)
					|| ((registerform.startTime.value).length == 0)
					|| ((registerform.endTime.value).length == 0)) {
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
					if ((registerform.start0.value).length == 0
							|| (registerform.end0.value).length == 0) {
						alert("ორშაბათი დღე მონიშნულია,მაგრამ საწყისი ან საბოლოო დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "1") {
					if ((registerform.start1.value).length == 0
							|| (registerform.end1.value).length == 0) {
						alert("სამშაბათი დღე მონიშნულია,მაგრამ საწყისი ან საბოლოო დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "2") {
					if ((registerform.start2.value).length == 0
							|| (registerform.end2.value).length == 0) {
						alert("ოთხშაბათი დღე მონიშნულია,მაგრამ საწყისი ან საბოლოო დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "3") {
					if ((registerform.start3.value).length == 0
							|| (registerform.end3.value).length == 0) {
						alert("ხუთშაბათი დღე მონიშნულია,მაგრამ საწყისი ან საბოლოო დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "4") {
					if ((registerform.start4.value).length == 0
							|| (registerform.end4.value).length == 0) {
						alert("პარასკევი დღე მონიშნულია,მაგრამ საწყისი ან საბოლოო დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "5") {
					if ((registerform.start5.value).length == 0
							|| (registerform.end5.value).length == 0) {
						alert("შაბათი დღე მონიშნულია,მაგრამ საწყისი ან საბოლოო დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "6") {
					if ((registerform.start6.value).length == 0
							|| (registerform.end6.value).length == 0) {
						alert("კვირა დღე მონიშნულია,მაგრამ საწყისი ან საბოლოო დრო არ არის მითითებული");
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
					<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
					<li><a href="#registermodal" id="modaltrigger">შეავსეთ დეტალური ინფრომაცია</a></li>
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->

			<div id="registermodal" style="display: none;">
				<h3 class="txt">გთხოვთ შეავსოთ ან ერთჯერადი ან ყოველდღიური
					ნაწილი</h3>
				<form action="WantToGoServlet" id="registerform" name="registerform"
					method="post">
					<p>
						<input type="text" name="title" placeholder="სახელი " tabindex="1"></input>
					</p>
					<p>
						ყოველდღიური <input type="radio" class="radio1" name="type"
							value="everyday" tabindex="5"></input>
					</p>
					<p>
						ორშაბათი: <input type="checkbox" name="0" value="0" tabindex="5"></input> <input
							type="text" data-field='time' name="start0"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="end0"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						სამშაბათი: <input type="checkbox" name="1" value="1" tabindex="5"></input> <input
							type="text" data-field='time' name="start1"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="end1"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						ოთხშაბათი: <input type="checkbox" name="2" value="2" tabindex="5"></input> <input
							type="text" data-field='time' name="start2"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="end2"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						ხუთშაბათი: <input type="checkbox" name="3" value="3" tabindex="5"></input> <input
							type="text" data-field='time' name="start3"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="end3"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						პარასკევი: <input type="checkbox" name="4" value="4" tabindex="5"></input> <input
							type="text" data-field='time' name="start4"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="end4"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						შაბათი: <input type="checkbox" name="5" value="5" tabindex="5"></input> <input
							type="text" data-field='time' name="start5"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="end5"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						კვირა: <input type="checkbox" name="6" value="6" tabindex="5"></input> <input
							type="text" data-field='time' name="start6"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="end6"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						ერთჯერადი <input type="radio" class="radio2" name="type"
							value="oneway" tabindex="6"></input>
					</p>
					<p>
						<input type="text" data-field='date' name="datetimeStart"
							placeholder="საწყისი თარიღი" tabindex="4"></input> <input
							type="text" data-field='date' name="datetimeFinish"
							placeholder="საბოლოო თარიღი" tabindex="4"></input>
					</p>
					<p>
						<input type="text" data-field='time' name="startTime"
							placeholder="საწყისი დრო" tabindex="4"></input> <input
							type="text" data-field='time' name="endTime"
							placeholder="საბოლოო დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
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
	var map;
	var check;
	var fromLongitude;
	var fromLatitude;
	var toLongitude;
	var toLatitude;
	function initialize() {
		check = 0;
		var mapProp = {
			center : new google.maps.LatLng(42.347485, 43.7),
			zoom : 7,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
		google.maps.event.addListener(map, 'click', function(event) {
			placeMarker(event.latLng);
		});
	}
	function placeMarker(location) {
		check++;
		if (check == 1) {
			var marker = new google.maps.Marker({
				position : location,
				map : map,
			});
			fromLongitude = location.lng();
			document.getElementById("hiddenField1").value=fromLongitude;
			fromLatitude = location.lat();
			document.getElementById("hiddenField2").value=fromLatitude;
			var infowindow = new google.maps.InfoWindow({
				content : 'საწყისი პუნქტი'
			});
			infowindow.open(map, marker);
		} else if (check == 2) {
			var marker = new google.maps.Marker({
				position : location,
				map : map,
			});
			toLongitude = location.lng();
			document.getElementById("hiddenField3").value=toLongitude;
			toLatitude = location.lat();
			document.getElementById("hiddenField4").value=toLatitude;
			var infowindow = new google.maps.InfoWindow({
				content : 'საბოლოო პუნქტი'
			});
			infowindow.open(map, marker);
		}
	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<h3>წარმატებით დაემატა საძებნი მიმართულება. დაამატეთ ახალი ძებნის პარამეტრები ან დაბრუნდით მთავარ გვერდზე.</h3>
	<div class="line"></div>
	<div id="googleMap" style="width: 800px; height: 400px;"></div>
</body>
			</html>
			<div class="line"></div>
			<div id="footer">
				<h2>აირჩიეთ მარშრუტის საწყისი და საბოლოო პუნქტები</h2>
				<p>
					<span class="lite1">ტელ:</span> +555 636 646 62 <span class="lite1">მეილი:</span>
					name@domain.com
				</p>
			</div>
			<!-- End Content -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {

			$('#modaltrigger').leanModal({
				top : 5,
				overlay : 0.45,
				closeButton : ".modal_close"
			});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.timepicker').timepicker();
		});
	</script>
</body>
</html>