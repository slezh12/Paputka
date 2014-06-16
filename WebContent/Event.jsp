<!DOCTYPE html>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%><%@page import="JavaPackage.Route"%>
<%@page import="JavaPackage.Event"%>
<%@page import="JavaPackage.EventParseInfo"%>
<%@page import="JavaPackage.Request"%>
<%@page import="JavaPackage.User"%>
<%@page import="JavaPackage.UserParseInfo"%>
<%@page import="JavaPackage.Route"%>
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
					
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->
			
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
	function initialize() {
		var mapProp = {
			center : new google.maps.LatLng(42.347485, 43.7),
			zoom : 7,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
<body>
	<%
		String id = request.getParameter("id");
		BasicDataSource source = (BasicDataSource) application.getAttribute("connectionPool");
		EventParseInfo eventParse = new EventParseInfo(source);
		UserParseInfo userParse = new UserParseInfo(source);
		Event e = eventParse.getEventByID(id);
		User u = userParse.getUserByID(e.getUserID());
		Route r = e.getRoute();
	%>
	<div id="googleMap" style="width: 800px; height: 400px;"></div>
</body>
			</html>
			<div class="line"></div>
			<h3>გადასახადი : <%= e.getPrice() %></h3>
			<h3>ადგილები : <%= e.getPlaces() %></h3>
			<h3>მძღოლი : ლაშა <%= u.getFirstName()+ " " + u.getLastName() %></h3>
			<h3><%= r.getFromPlace() + " - " + r.getToPlace() %></h3>
			<div class="line"></div>
			<h2>komentarebi</h2>
			<div id="column">
				<ul id="latestnews">
					<li><img src="images/demo/80x80.gif" alt="" />
						<p>
							<strong><a href="#">Tedo Chubinidze</a></strong>
							Magari Eventi iko dzma :)
						</p></li>
					<li><img src="images/demo/80x80.gif" alt="" />
						<p>
							<strong><a href="#">Koko Tagauri</a></strong>
							gaixaree Teduch
						</p></li>
			
				</ul>
			</div>
			<!-- End Content -->
		</div>
	</div>


</body>
</html>